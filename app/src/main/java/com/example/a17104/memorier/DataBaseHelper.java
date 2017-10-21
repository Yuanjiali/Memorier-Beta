package com.example.a17104.memorier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 17104 on 2017/10/19.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String COST_MONEY = "cost_money";
    public static final String COST_DATE = "cost_date";
    public static final String COST_TITLE = "cost_title";
    public static final String MEMORIER_COST = "memorier_cost";

    public DataBaseHelper(Context context) {
        super(context, "memorier_daily", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists MEMORIER_COST(" +
                "id integer primary key," +
                "cost_title varchar," +
                "cost_date varchar," +
                "cost_money varchar)");
    }
    //插入数据
    public void insertCost(DailyBean dailyBean){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COST_TITLE,dailyBean.addAccount);
        cv.put(COST_DATE,dailyBean.costDate);
        cv.put(COST_MONEY,dailyBean.addPassword);
        database.insert(MEMORIER_COST,null,cv);
    }

    public Cursor getAllCostData(){
        SQLiteDatabase database=getWritableDatabase();
        return  database.query("MEMORIER_COST",null,null,null,null,null,COST_DATE+" ASC");
    }
    //清空数据
    public void deleteAllData(){
        SQLiteDatabase database=getWritableDatabase();
        database.delete("MEMORIER_COST",null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
