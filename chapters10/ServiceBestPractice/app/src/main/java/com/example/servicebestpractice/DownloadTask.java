package com.example.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
    第一个泛型参数为String  需要传入一个字符串给后台任务
    第二个参数为Integer 表示使用整形数据作为进度显示单位
    第三个参数为 Integer 使用整形数据来反馈执行结果
 */
public class DownloadTask extends AsyncTask<String,Integer,Integer> {
    public static final int TYPE_SUCCESS=0;
    public static final int TYPE_FAILED=1;
    public static final int TYPE_PAUSED=2;
    public static final int TYPE_CANCELED=3;

    private DownloadListener listener;
    private boolean isCanceled=false;
    private boolean isPaused=false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener){
        this.listener=listener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream is=null;
        RandomAccessFile savedFile=null;
        File file= null;
        try {
            long downloadedLength=0;//记录已下载的文件长度
            String downloadUri=params[0];// 获取下载地址
            String fileName=downloadUri.substring(downloadUri.lastIndexOf("/"));
            //指定文件下载地址
            String directory= Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory+fileName);
            if(file.exists()){
                downloadedLength=file.length();
            }
            long contentLength =getContentLength(downloadUri);
            if(contentLength==0) {
                return TYPE_FAILED;
            }else  if(contentLength==downloadedLength){
                //已下载字节和文件总字节相等，下载完成
                return TYPE_SUCCESS;
            }
            OkHttpClient client =new OkHttpClient();
            Request request= new Request.Builder()
                    //断电下载，指定从哪个字节开始下载
                    .addHeader("RANGE","bytes="+downloadedLength+"-")
                    .url(downloadUri)
                    .build();
            Response response=client.newCall(request).execute();
            if(response!=null) {
                is= response.body().byteStream();
                savedFile =new RandomAccessFile(file,"rw");
                savedFile.seek(downloadedLength);//跳过已经下载的字节
                byte[]b=new byte[1024];
                int total=0;
                int len;
                while((len=is.read(b))!=-1){
                    if(isCanceled){
                        return TYPE_CANCELED;
                    }else if(isPaused) {
                        return TYPE_PAUSED;
                    }else {
                        total+=len;
                        savedFile.write(b,0,len);
                        //计算已下载的百分比
                        int progress =(int)((total+downloadedLength)*100/contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return  TYPE_SUCCESS;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(is!=null){
                    is.close();
                }
                if (savedFile!=null){
                    savedFile.close();
                }
                if(isCanceled&&file!=null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress=values[0];
        if(progress>lastProgress){
            listener.onProgress(progress);
            lastProgress=progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch (status){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
            case TYPE_PAUSED:
                listener.onPaused();
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPaused=true;
    }
    public void cancelDownload(){
        isCanceled=true;
    }

    private long getContentLength(String downloadUri) throws IOException {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(downloadUri)
                .build();
        Response response=client.newCall(request).execute();
        if(response!=null&& response.isSuccessful()){
            long contentLength =response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }
}
