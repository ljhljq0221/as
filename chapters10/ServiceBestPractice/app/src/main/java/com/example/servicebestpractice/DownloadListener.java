package com.example.servicebestpractice;

public interface DownloadListener {
   // 通知当前下载进度
    void onProgress(int progress);
    // 通知下载成功
    void onSuccess();
    //通知下载失败
    void onFailed();
    // 通知下载暂停
    void onPaused();
    //下载取消
    void onCanceled();

}
