package com.example.para.zbieracz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;

/**
 * Created by Para on 25.01.2017.
 */

public class DataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataSource(Context mContext) {
        this.mContext = mContext;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();

    }
    public void open(){
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }


    public long getDataReportsCount(){
        return DatabaseUtils.queryNumEntries(mDatabase,ReportsTable.TABLE_ITEMS);
    }


    //zapisywanie
    public void saveReport(String firstName,String lastName,String date,String image){
        ContentValues values = new ContentValues(7);

        String reportId = UUID.randomUUID().toString();

        values.put(ReportsTable.COLUMN_ID, reportId);
        values.put(ReportsTable.COLUMN_FIRSTNAME, firstName);
        values.put(ReportsTable.COLUMN_LASTNAME, lastName);
        values.put(ReportsTable.COLUMN_DATE, date);
        values.put(ReportsTable.COLUMN_IMAGE, image);

        mDatabase.insert(ReportsTable.TABLE_ITEMS, null, values);
    }

    //wyswietlanie
    public String getReports(){
        String reports = "";
        Cursor cursor = mDatabase.query(ReportsTable.TABLE_ITEMS, ReportsTable.ALL_COLUMNS, null, null, null, null, null);
        while (cursor.moveToNext()){
            reports = "" + cursor.getColumnIndex(ReportsTable.COLUMN_FIRSTNAME) + " " + cursor.getColumnIndex(ReportsTable.COLUMN_LASTNAME);
        }
        return reports;
    }


}
