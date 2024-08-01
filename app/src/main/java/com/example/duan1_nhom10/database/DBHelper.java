package com.example.duan1_nhom10.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String NAME_DB = "MANAGER.db";
    public static final int VERSION_DB = 1;
    public DBHelper(@Nullable Context context) {
        super(context,NAME_DB,null,VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NGUOIDUNG (" +
                "TENNGUOIDUNG TEXT PRIMARY KEY," +
                "MATKHAU TEXT NOT NULL," +
                "VAITRO INTEGER NOT NULL)");

        db.execSQL("INSERT INTO NGUOIDUNG VALUES('ADMIN','ADMIN',0)");

        db.execSQL("CREATE TABLE TRUYEN(" +
                "TENTRUYEN TEXT PRIMARY KEY," +
                "TACGIA TEXT NOT NULL," +
                "NOIDUNG TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
