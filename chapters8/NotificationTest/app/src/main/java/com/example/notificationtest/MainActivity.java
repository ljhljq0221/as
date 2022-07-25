package com.example.notificationtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button GetNotification;
    private static final int ID = 1;
    private static final String CHANNELID ="1";
    private static final String CHANNELNAME = "channel1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetNotification = (Button) findViewById(R.id.send_notice);
        GetNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // manager.cancel(1);
                //安卓8.0以上弹出通知需要添加渠道NotificationChannel

                
                    Notification notification = new Notification.Builder(MainActivity.this)
                            .setContentTitle("Title")
                            .setContentText("ContentText")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.big_image)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.big_image))
                            .build();
                    manager.notify(1,notification);



            }
        });
    }
}

