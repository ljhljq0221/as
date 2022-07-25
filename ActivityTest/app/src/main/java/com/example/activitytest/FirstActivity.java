package com.example.activitytest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {
    // 向上一个活动传输数据
    ActivityResultLauncher<Intent> myResult=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== RESULT_OK){
                        Intent intent=result.getData();
                        String res=intent.getStringExtra("res");
                        Log.d("FirstActivity",res);

                    }
                }
            }

    );

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"You clicked Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"You clicked Remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fisrst_layout);
        Log.d("FirstActivity","Task id is"+getTaskId());
        Button button1=(Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1 intent 显式引用
              /*Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
              startActivity(intent);*/

               // 2 intent 隐式应用
                /*
                Intent intent=new Intent("com.example.activitytest.ACTION_START");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);*/
                // 3 利用系统浏览器打开网页 隐式应用
              /*  Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);*/
                // 4 如果在程序中调用系统拨号界面
               /* Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);*/
                //5 向下一个活动传入字符串
                /*String data= "Hello SecondActivity";
                Intent intent =new Intent(FirstActivity.this,SecondActivity.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);*/
                //6 向第三个活动输入数字
               /*int data= 12;
                Intent intent=new Intent(FirstActivity.this,ThirdActivity.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);*/
                //7 给第二传输数据 并且如果传输正确，返回给第一个活动一个值
                /*Intent intent= new Intent(FirstActivity.this,SecondActivity.class);
                String data="hello SecondActivity";
                intent.putExtra("extra_data",data);
                intent.putExtra("data2",1);
                myResult.launch(intent);
                //startActivity(intent);*/
                //8 启动获得最佳写法
                String data1="1";
                String data2="2";
                SecondActivity.actionStart(FirstActivity.this,"data1","data2");

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("FirstActivity",this.toString());
    }
}