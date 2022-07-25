package com.example.uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化聊天界面
        initMsgs();
        inputText=(EditText) findViewById(R.id.input_text);
        send=(Button) findViewById(R.id.send);
        msgRecyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取EditText内容，即输入内容。
                String content= inputText.getText().toString();
                //内容不为空，新建一个Msg对象，添加到msgList中
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有信息时，刷新RecyclerView中的显示
                    // adapter.notifyItemInserted（） 通知有新的数据插入
                    adapter.notifyItemInserted(msgList.size()-1);
                    //将RecyclerView定位到最后一行
                    //RecyclerView.scrollToPosition（） 将数据定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    //清空输入框的内容
                    inputText.setText("");//清空输入框的内容

                }
            }
        });
    }
    //舒适化数据用于显示
    private void initMsgs(){
        Msg msg1=new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("Hello, who is that",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("This is Tom.Nice talking to you. ",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}