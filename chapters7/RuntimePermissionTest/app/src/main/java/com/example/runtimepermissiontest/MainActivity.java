package com.example.runtimepermissiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeCall=(Button) findViewById(R.id.make_call);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断用户是不是已经授权给我们了
                // ContextCompat.checkSelfPermission 第一个参数 context
                //                                    第二个参数 具体权限名
                //返回值与PackageManager.PERMISSION_GRANTED对比，相同已经授权，否则没有授权
                if(ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED){
                    //没有授权的话。需要调用ActivityCompat.requestPermissions 向用户申请授权
                    //第一个参数 Activity的实例 第二个参数是一个String 数组 第三个参数 请求码 为一组就可以
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},1);
                }else{
                    call();
                }
            }
        });
    }
    private void call(){
        try {
            //隐式Intent intent的action指定为 Intent.ACTION_CALL
            //             系统内置的打电话动作
            Intent intent= new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }
    /*
        需要调用ActivityCompat.requestPermissions后，系统会弹出一个对话框，无论用户同意还是拒绝，都会
         回调onRequestPermissionsResult（）方法，授权的结果在grantResults中，只需要判断下授权结果，同意
         就会调用call（）拨打，不同意就说，没有权限。
    */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "You denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}