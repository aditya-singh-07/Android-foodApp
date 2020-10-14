package com.aditya.foodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aditya.foodapp.Models.OrderModels;

import java.util.ArrayList;

public class foodmodel {
    static DBhelper dBhelper;
    static SQLiteDatabase sqllitedatabase;

    public static DBhelper getinstance(Context context){
        if(dBhelper == null){
            dBhelper=new DBhelper(context);
        }
        return  dBhelper;
    }
    public static void opendb(){
        sqllitedatabase=dBhelper.getWritableDatabase();
    }
    public static void closedb(){
        sqllitedatabase.close();
    }

    public static boolean insertorder(String name, String f_email, String f_phone, int f_image, String f_name, String f_description, int f_price, int f_quantity){
       opendb();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("f_email",f_email);
        cv.put("f_phone",f_phone);
        cv.put("f_image",f_image);
        cv.put("f_name",f_name);
        cv.put("f_description",f_description);
        cv.put("f_price",f_price);
        cv.put("f_quantity",f_quantity);
        long id=sqllitedatabase.insert("Foods",null,cv);
        if(id<= 0){
            return  false;
        }else {
            return true;
        }

    }
    public ArrayList<OrderModels> getorders(){
        foodmodel.opendb();
        ArrayList<OrderModels> orderlist=new ArrayList<OrderModels>();
        String order_sql="SELECT id,f_name,f_image,f_price FROM Foods";
        Cursor cursor=sqllitedatabase.rawQuery(order_sql,null);
        if(cursor.moveToNext()){
            while(cursor.moveToNext()){
                OrderModels order=new OrderModels();
                order.setOrdernumber(cursor.getInt(0) + "");
                order.setOrdername(cursor.getString(1));
                order.setOrderimage(cursor.getInt(2));
                order.setPrice(cursor.getInt(3) + "");
                orderlist.add(order);
            }
        }
        cursor.close();
        foodmodel.closedb();
        return  orderlist;
    }

    public Cursor getordersbyid(int id){
        foodmodel.opendb();
        String order_sql="SELECT * FROM Foods where id=" +  id;
        Cursor cursor=sqllitedatabase.rawQuery(order_sql,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        foodmodel.closedb();
        return  cursor;
    }

    public static boolean updateorder(String name, String f_email, String f_phone, int f_image, String f_name, String f_description, int f_price, int f_quantity,int id){
        foodmodel.opendb();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("f_email",f_email);
        cv.put("f_phone",f_phone);
        cv.put("f_image",f_image);
        cv.put("f_name",f_name);
        cv.put("f_description",f_description);
        cv.put("f_price",f_price);
        cv.put("f_quantity",f_quantity);
        long idupdate=sqllitedatabase.update("Foods",cv, "id=" + id,null);
        if(idupdate<= 0){
            return  false;
        }else {
            return true;
        }

    }
    public int deleteorder(int id){
        foodmodel.opendb();
        return sqllitedatabase.delete("Foods", "id="+ id,null);



    }



}
