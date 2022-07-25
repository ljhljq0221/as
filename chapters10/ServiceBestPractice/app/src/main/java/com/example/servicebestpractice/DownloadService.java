package com.example.servicebestpractice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.io.File;

public class DownloadService extends Service {
    private DownloadTask downloadTask;
    private String downloadUri;
    private DownloadListener listener =new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,
                    getNotification("Downloading...",progress) );
        }

        @Override
        public void onSuccess() {
            downloadTask=null;
            //下载成功时将前台服务关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,
                    getNotification("Download Success",-1));
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailed() {
            downloadTask=null;
            //下载成功时将前台服务关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1,
                    getNotification("Download Failed",-1));
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPaused() {
            downloadTask=null;
            Toast.makeText(DownloadService.this, "Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask=null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder mBinder=new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUri = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUri);
                startForeground(1, getNotification("Downloading", 0));
                Toast.makeText(DownloadService.this, "Downloading", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            }
            if (downloadUri != null) {
                //取消下载时需将文件删除，并关闭通知
                String fileName = downloadUri.substring(downloadUri.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                if (file.exists()) {
                    file.delete();
                }
                getNotificationManager().cancel(1);
                stopForeground(true);
                Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

        private NotificationManager getNotificationManager(){
            return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        private Notification getNotification(String title,int progress){
            Intent intent=new Intent(this,MainActivity.class);
            PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
            NotificationCompat.Builder notification;
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel("xxx", "xxx", NotificationManager.IMPORTANCE_LOW);
                channel.enableLights(true);
                channel.setShowBadge(true);
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);
                notification = new NotificationCompat.Builder(DownloadService.this).setChannelId("xxx");
            }else {
                notification =new NotificationCompat.Builder(DownloadService.this);
            }

                notification.setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher)).setContentIntent(pi).setContentTitle(title);
                if(progress>=0){
                    //当Progress 大于或等于0的时候，显示下载进度
                    notification.setContentText(progress+"%");
                    notification.setProgress(100,progress,false);
            }
            return notification.build();


        }
}