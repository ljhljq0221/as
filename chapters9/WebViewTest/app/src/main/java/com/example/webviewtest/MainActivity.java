package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView =(WebView) findViewById(R.id.web_view);
        //.getSettings().设置一些属性
        //setJavaScriptEnabled 让WebView支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //setWebViewClient 但一个网页跳转到另一个网页时，我们希望目标网页仍在当前WebView中显示，而不打开系统浏览器
        webView.setWebViewClient(new WebViewClient());
        //loadUrl 调入一个Uri  可展示相应的网页内容
        webView.loadUrl("http://www.baidu.com");

        //
    }
}