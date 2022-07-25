package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //Book 的建表语句
    public static final String CREATE_BOOK ="create table Book("
            +"id integer primary key autoincrement ,"
            +"author text,"
            +"price real, "
            +"pages integer, "
            +"name text)";
    // Caregory 分类
    public static final String CREATE_CATEGORY="create table Category("
            +"id integer primary key autoincrement ,"
            +"category_name text, "
            +"category_code integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory,
                            int version){
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //利用execSQL 方法执行建表语句
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        //Toast.makeText(mContext, "Created succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
