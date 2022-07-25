package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dpHelper =new MyDatabaseHelper(this,"BookStore.db",null,2);
        Button createDatabase= (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpHelper.getWritableDatabase();
            }
        });
        Button addData=(Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db =dpHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //开始组装第一条数据
                values.put("author","Dan");
                values.put("price",1654);
                values.put("pages",454);
                values.put("name","The Da Vinci Code");
                db.insert("Book",null,values);
                values.clear();
                //第二组
                values.put("name","The  Vinci Code");
                values.put("author","Dn");
                values.put("pages",45);
                values.put("price",654);
                db.insert("Book",null,values);
            }
        });
        Button updataData=(Button) findViewById(R.id.updata_data);
        updataData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dpHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name=?"
                        ,new String[]{"The Da Vinci Code"});
            }
        });
        Button deleteData=(Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dpHelper.getWritableDatabase();
                db.delete("Book","pages>?",new String[]{"30"});
            }
        });
        Button queryData=(Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dpHelper.getWritableDatabase();
                //查询Book表中的所有数据
                Cursor cursor= db.query("Book",null,
                        null,null,null,
                        null,null);
                //cursor.moveToFirst() 指针移动到第一行的位置
                if(cursor.moveToFirst()){
                    do{
                        //遍历Cursor 对象，取出数据并打印
                        //cursor.getColumnIndex 获取某一列在表中对应的位置索引
                        @SuppressLint("Range") String name= cursor.getString
                                (cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String author=cursor.getString
                                (cursor.getColumnIndex("author"));
                        @SuppressLint("Range") int pages=cursor.getInt
                                (cursor.getColumnIndex("pages"));
                        @SuppressLint("Range") double price=cursor.getDouble
                                (cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is "+name);
                        Log.d("MainActivity", "book author is "+author);
                        Log.d("MainActivity", "book pages is "+pages);
                        Log.d("MainActivity", "book price is "+price);
                    }while(cursor.moveToNext());
                }
                //关闭cursor
                cursor.close();
            }
        });
    }
}