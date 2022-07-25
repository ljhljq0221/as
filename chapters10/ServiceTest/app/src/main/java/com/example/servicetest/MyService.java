package com.example.servicetest;

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
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private static String TAG= "MyService ";
    private DownloadBinder mBinder=new DownloadBinder();
    class  DownloadBinder extends Binder{
        public void startDownload(){
            Log.d(TAG, "startDownload: executed");
        }
        public int getProgress(){
            Log.d(TAG, "getProgress: executed");
            return 0;
        }
    }
    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: exexuted");
        Notification notification=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("xxx", "xxx", NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent=new Intent(this,MainActivity.class);
            PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
            if (manager == null)
                return;
            manager.createNotificationChannel(channel);
            notification = new NotificationCompat.Builder(this, "xxx")
                    .setAutoCancel(true)
                    .setCategory(Notification.CATEGORY_SERVICE).setOngoing(true)
                    .setPriority(NotificationManager.IMPORTANCE_LOW)
                    . setContentTitle("THis is content title")
                    .setContentText("this is content text")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                            R.mipmap.ic_launcher))
                    .setContentIntent(pi)
                    .build();
        }
        startForeground(1,notification);
    }

    //每次服务启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               //处理具体逻辑
               stopSelf();
           }
       }).start();
        Log.d(TAG, "onStartCommand:  exexuted");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:  exexuted");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
}