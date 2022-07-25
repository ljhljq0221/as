package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase= (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击一下，数据库自动创建
                LitePal.getDatabase();
            }
        });
        Button addData=(Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setAuthor("DAN");
                book.setPags(456);
                book.setNames("The love");
                book.setPrice(46);
                book.setPress("Unknow");
                book.save();
            }
        });
        Button updataData=(Button) findViewById(R.id.updata_data);
        updataData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setToDefault("pags");
                //updateAll 可以指定一个约束条件，如果不指定的话，默认更新所有
                book.updateAll();
            }
        });
        Button deleteData=(Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price < ?","53");
            }
        });
        Button queryData =(Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books=LitePal.order("price desc").find(Book.class);
                Book books=LitePal.findLast(Book.class);
                for(Book book:books){
                    Log.d("MainActivity", "book name is "+book.getNames());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book press is "+book.getPress());
                    Log.d("MainActivity", "book pags is "+book.getPags());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                }
            }
        });
    }
}