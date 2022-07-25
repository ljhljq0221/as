package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection=new ServiceConnection() {
       // 活动与服务绑定的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
        //活动与服务连接断开的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService =(Button) findViewById(R.id.start_service);
        Button stopService=(Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        Button bindService =(Button) findViewById(R.id.bind_service);
        Button unbindService =(Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        Button startIntentService =(Button) findViewById(R.id.start_intentservice);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent =new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent =new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent=new Intent(this,MyService.class);
                // bindService 三个参数 第一个是 Intent 对象
                //                     第二个是 ServiceConnection实例
                //                     第三个是 标志位 BIND_AUTO_CREATE
                //                      表示在活动和服务进行绑定后自动创建服务
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection);//解除服务
                break;
            case R.id.start_intentservice:
                //打印主线程的id
                Log.d("Mainactivity","Thread id is "+Thread.currentThread().getId());
                Intent intentService=new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}