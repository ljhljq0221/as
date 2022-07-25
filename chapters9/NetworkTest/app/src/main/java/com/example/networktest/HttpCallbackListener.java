package com.example.networktest;

public interface HttpCallbackListener {
    // 表示当服务器成功响应我们请求的时候调用，参数为服务器返回的数据
    void onFinish(String response);
    // 表示网络操作出现错误的时候调用，参数记录着详细的错误信息
    void onError(Exception e);
}
