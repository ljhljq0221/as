package com.example.uibestpractice;

public class Msg {
   //这是一条收到的消息
    public static final int TYPE_RECEIVED=0;
    //这是一天发出的消息
    public static final int TYPE_SENT=1;
    //消息的内容
    private String content;
    //消息的类型
    private int type;
    public Msg(String content,int type){
        this.content=content;
        this.type=type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
