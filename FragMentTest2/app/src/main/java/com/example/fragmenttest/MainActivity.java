package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button:
                       // replaceFragment(new AnotherRightFragment());
                        break;
                    default:
                        break;
                }
            }
        });
        RightFragment rightFragment=(RightFragment) getSupportFragmentManager()
                .findFragmentById(R.id.right_fragment);
        //replaceFragment(new RightFragment());

    }
    /*private void replaceFragment(Fragment fragment){
       //获取FragmentManager，在活动中可以直接调用getSupportFragmentManager（）方法
        FragmentManager fragmentManager=getSupportFragmentManager();
        //开启一个事务，通过beginTransaction（）方法开启
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        //向容器内添加或交替碎片，一般使用replace()方法实现，需要传入容器的id和待添加的碎片实例
        transaction.replace(R.id.,fragment);
        //调用addToBackStack（）方法，可以接受一个名字用于描述返回栈的状态，一般传入null即可
        transaction.addToBackStack(null);
        // 提交事务，调用transaction.commit()完成
        transaction.commit();
    }*/
}