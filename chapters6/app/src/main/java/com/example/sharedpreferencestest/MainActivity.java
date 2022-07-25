package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData =(Button) findViewById(R.id.button);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putInt("age",20);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });
        Button restoreData=(Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                String name= pref.getString("name","");
                int age=pref.getInt("age",0);
                Boolean married=pref.getBoolean("married",false);
                Log.d("Mactivity","name is "+name);
                Log.d("Mactivity","age is "+age);
                Log.d("Mactivity","married is "+married);
            }
        });
    }
}