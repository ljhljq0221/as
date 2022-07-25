package com.example.toolbartest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        // 获取传入的水果名和水果图片
        Intent intent=getIntent();
        String fruitName= intent.getStringExtra(FRUIT_NAME);
        int fruitImageId= intent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toobal);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ImageView fruitImagedView=(ImageView) findViewById(R.id.fruit_image_view);
        TextView fruitContentText=(TextView) findViewById(R.id.fruit_content_text);
        ////Toolbar 标准用法  做为ActionBar显示 并启动HomeAsUp 按钮
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //调用collapsingToolbar.setTitle 将水果名设置成当前界面标题
        collapsingToolbar.setTitle(fruitName);
        //glide 加载传入的图片 并设置到标题栏上
        Glide.with(this).load(fruitImageId).into(fruitImagedView);
        String fruitContent=generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent =new StringBuilder();
        for(int i=0;i<500;i++){
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}