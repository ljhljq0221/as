package com.example.androidthreadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
class DownloadTask extends AsyncTask<Void,Integer,Boolean>{
        //第一个参数为Void,表示执行AsyncTask期间不需要传入参数给后台
        //第二个参数为 Integer 表示使用整形数据作为进度显示单位
        //第三个参数为 Boolean  表示使用布尔型数据反馈执行结果

        //在后台任务开始执行之前调用，用于进行界面上的初始化操作，如在界面上显示一个进度条
        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        // 该方法在子线程中运行，我们应该在这里去处理所有的耗时任务，任务完成就可通过return
        //将任务的执行结果返回
        //如果AsyncTask的第三个参数是 Void，可以不返回任务执行结果
        //这个方法不可以进行UI操作，如果需要更新UI，可以调用publishProgress(Progress)完成
        @Override
        protected boolean doInBackground(Void... voids) {
            try{
                while(true){
                    int downloadPercent=doDownload;
                    publishProgress(downloadPercent);
                    if(downloadPercent>=100) break;
                }
            }catch (Exception e){
                return false;
            }
            return true;
        }


            当在后台中调用了publishProgress(Progress) 方法后，该方法很快被调用，方法中的参数就是
                后台任务中传递过来的。在这个方法中可对UI进行操作，利用参数的数组进行更新UI


        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新下载进度
            progressDialog.setMessage("Downloadede "+ values[0]+"%");

        }


            后台执行完毕并通过retuen返回时，这个方法会被调用。返回的数据作为参数传递到该方法
            可利用返回的数据进行更新UI操作

       ///* @Override*/
      /*  protected  void onPostExecute(Boolean result) {
            progressDialog.dismiss();//关闭对话框
            //在这里提示下载进度
            if(result ){
                Toast.makeText(context,"Download Succeed",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context,"Download failed",Toast.LENGTH_SHORT).show();
            }
        }
    }*/
public class MainActivity extends AppCompatActivity {
    public static final int UPDATE_TEXT=1;
    private TextView text;
   private Handler handler=new Handler(Looper.getMainLooper()){

        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    //这这里可进行UI操作
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };

    ExecutorService executorService = Executors.newScheduledThreadPool(4);
        executorService.execute(new Runnable() {
        @Override
        public void run() {

            //调用发送到UI线程中
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //这里是在UI线程中执行的 使用这里是可以更新UI控件的
                }
            });
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView) findViewById(R.id.text);
        Button changeText =(Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.change_text:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message message =new Message();
                                message.what=UPDATE_TEXT;
                                handler.sendMessage(message);//将message对象发出去
                            }
                        }).start();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}