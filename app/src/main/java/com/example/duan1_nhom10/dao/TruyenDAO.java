package com.example.duan1_nhom10.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duan1_nhom10.database.DBHelper;
import com.example.duan1_nhom10.model.Truyen;
import java.util.ArrayList;

public class TruyenDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public TruyenDAO(Context context) {
        dbHelper=new DBHelper(context);
        database=dbHelper.getWritableDatabase();
    }

    public ArrayList<Truyen> toanBoTruyen(){
        ArrayList<Truyen> arrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM TRUYEN", null);
        while (cursor.moveToNext()){
            Truyen truyen=new Truyen();
            truyen.setTenTruyen(cursor.getString(0));
            truyen.setTacGia(cursor.getString(1));
            truyen.setNoiDung(cursor.getString(2));
            arrayList.add(truyen);
        }
        return arrayList;
    }

    public Truyen truyenCuThe(String tenTruyen){
        Truyen truyen=new Truyen();
        Cursor cursor=database.rawQuery("SELECT * FROM TRUYEN WHERE TENTRUYEN=?",
                new String[]{tenTruyen});
        while (cursor.moveToNext()){
            truyen.setTenTruyen(cursor.getString(0));
            truyen.setTacGia(cursor.getString(1));
            truyen.setNoiDung(cursor.getString(2));
        }
        return truyen;
    }

    public boolean themTruyen(Truyen truyen){
        ContentValues values=new ContentValues();
        values.put("TENTRUYEN",truyen.getTenTruyen());
        values.put("TACGIA",truyen.getTacGia());
        values.put("NOIDUNG",truyen.getNoiDung());
        long check=database.insert("TRUYEN",null,values);
        return check>0;
    }

    public boolean suaTruyen(String tenTruyenCu,String tenTruyenMoi,String tacGiaMoi,String noiDungMoi){
        ContentValues values=new ContentValues();
        values.put("TENTRUYEN",tenTruyenMoi);
        values.put("TACGIA",tacGiaMoi);
        values.put("NOIDUNG",noiDungMoi);
        int check=database.update("TRUYEN",values,"TENTRUYEN=?",
                new String[]{tenTruyenCu});
        return check>0;
    }

    public boolean xoaTruyen(Truyen truyen){
        int check= database.delete("TRUYEN","TENTRUYEN=?",
                new String[]{truyen.getTenTruyen()});
        return check>0;
    }
}
