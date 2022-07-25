package com.example.fragmentbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle=getIntent().getStringExtra("news_title");//新闻标题
        String newsContent=getIntent().getStringExtra("news_content");//新闻内容
        //调用FragmentManager的 findFragmentById（）方法，在活动中获取碎片实例
        NewsContentFragment newsContentFragment=(NewsContentFragment)
                getSupportFragmentManager().
                        findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
    }
    //启动活动 可以看到启动该活动需要传入的参数
    public static void actionStart(Context context,String newsTitle,
                                   String newsContent){
        Intent intent= new Intent(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }
}