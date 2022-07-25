package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Context;

public class SecondActivity extends BaseActivity {
    public static void actionStart(Context context,String data1,String data2){
        Intent intent =new Intent(context,SecondActivity.class);
        intent.putExtra("para_num1",data1);
        intent.putExtra("para_num2",data2);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Log.d("SecondActivity","Task id is"+getTaskId());
       /* Intent intent=getIntent();
        String data=intent.getStringExtra("extra_data");
        int data1=intent.getIntExtra("data2",0);
        Log.d("SecondActivity",data);
        System.out.println(data1);*/
       Button button2=(Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    /*intent.putExtra("res","hell0,FirstActivity");
                    setResult(Activity.RESULT_OK,intent);
                    finish();*/
                /*Intent intent =new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);*/
                ActivityCollector.finishAll();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity",this.toString());
    }
}