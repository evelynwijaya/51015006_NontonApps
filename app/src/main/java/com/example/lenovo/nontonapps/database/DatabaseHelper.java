package com.example.lenovo.nontonapps.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenovo on 20/02/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String Database_Name = "Nonton.db";

    public static final String Table_User ="User";
    public static final String UserCol_1 = "Email";
    public static final String UserCol_2 = "Password";

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + Table_User + "(Email Text Primary Key, Password Text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_User);
        onCreate(db);
    }
}
