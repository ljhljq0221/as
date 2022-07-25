package com.example.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int BOOK_DIR=0;
    public static final int BOOK_ITEM=1;
    public static final int CATEGORY_DIR=2;
    public static final int CATEGORY_ITEM=3;
    public static final String AUTHORITY ="com.example.databasetest.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dpHelper;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }
    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //删除数据
        SQLiteDatabase db=dpHelper.getWritableDatabase();
        int deleteRows=0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteRows =db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                deleteRows =db.delete("Book","id = ?" ,
                        new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows=db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId =uri.getPathSegments().get(1);
                deleteRows=db.delete("Category","id = ?",
                        new String[]{categoryId});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book";

            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.book";

            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category";

            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.category";

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //添加数据
        SQLiteDatabase db=dpHelper.getWritableDatabase();
        Uri uriReturn=null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId =db.insert("Book",null,values);
                uriReturn=Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId =db.insert("Category",null,values);
                //Uri.parse（）可将一个内容解析成Uri对象
                uriReturn=Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dpHelper =new MyDatabaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
       //查询数据、
        //获取SQLiteDatabase实例
        SQLiteDatabase db=dpHelper.getWritableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor=db.query("Book",projection,selection,selectionArgs,null
                ,null,sortOrder);
                break;
            case BOOK_ITEM:
                //uri.getPathSegments() 会将内容URI的权限之后的部门以“/”符合进行分割，并把分割过后的放在一个字符串中
                // 这个列表的第0个位置存放的就是路径，第一个位置就是id
                String bookId=uri.getPathSegments().get(1);
                cursor=db.query("Book",projection,"id=?",
                        new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor=db.query("Category",projection,selection,selectionArgs,null
                        ,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId=uri.getPathSegments().get(1);
                cursor=db.query("Category",projection,"id=?",
                        new String[]{categoryId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        //更新数据
        SQLiteDatabase db=dpHelper.getWritableDatabase();
        int updataedRows=0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updataedRows= db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                updataedRows=db.update("Book",values,"id = ?",
                        new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updataedRows=db.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId=uri.getPathSegments().get(1);
                updataedRows=db.update("Category",values,"id = ?",
                        new String[]{categoryId});
                break;
            default:
                break;
        }
        return updataedRows;
    }
}