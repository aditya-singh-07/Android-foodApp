package com.aditya.foodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.shapes.OvalShape;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aditya.foodapp.Models.OrderModels;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    static SQLiteDatabase sqllitedatabase;
    final static String DBNAME="food.db";
    final static int DBVERSION=1;
    String CREATE_TABLE="CREATE TABLE Foods(id integer primary key autoincrement," +
            " name text not null," +
            "f_email text not null," +
            "f_phone text not null," +
            "f_image int not null," +
            " f_name text not null," +
            " f_description text not null," +
            " f_price int not null," +
            "f_quantity int not null )";
    public DBhelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists Foods");
        onCreate(db);
    }

//    public ArrayList<OrderModels> getorders(){
//        SQLiteDatabase database=this.getWritableDatabase();
//        ArrayList<OrderModels> orderlist=new ArrayList<OrderModels>();
//        String order_sql="SELECT id,f_name,f_image,f_price FROM Foods";
//        Cursor cursor=database.rawQuery(order_sql,null);
//        if(cursor.moveToNext()){
//            while(cursor.moveToNext()){
//                OrderModels order=new OrderModels();
//                order.setOrdernumber(cursor.getInt(0) + "");
//                order.setOrdername(cursor.getString(1));
//                order.setOrderimage(cursor.getInt(2));
//                order.setPrice(cursor.getInt(3) + "");
//                orderlist.add(order);
//            }
//        }
//        cursor.close();
//        database.close();
//        return  orderlist;
//    }



}
