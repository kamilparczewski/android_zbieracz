package com.example.para.zbieracz.database;

/**
 * Created by Para on 25.01.2017.
 */

public class ReportsTable {

    public static final String TABLE_ITEMS = "reports";
    public static final String COLUMN_ID = "reportId";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_IMAGE = "image";

    public static final String[] ALL_COLUMNS =
            {COLUMN_ID,COLUMN_FIRSTNAME,COLUMN_LASTNAME,COLUMN_DATE,COLUMN_IMAGE};

    public static String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + "TEXT PRIMARY KEY," +
                    COLUMN_FIRSTNAME + "TEXT," +
                    COLUMN_LASTNAME + "TEXT," +
                    COLUMN_DATE + "TEXT," +
                    COLUMN_IMAGE + "TEXT" + ")";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;


}
