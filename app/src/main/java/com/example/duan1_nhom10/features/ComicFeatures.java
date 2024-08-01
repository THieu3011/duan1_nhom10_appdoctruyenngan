package com.example.duan1_nhom10.features;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.adapter.TruyenAdapter;
import com.example.duan1_nhom10.dao.TruyenDAO;
import com.example.duan1_nhom10.model.Truyen;
import com.example.duan1_nhom10.screen.ComicScreen;

import java.util.ArrayList;

public class ComicFeatures {

    private Context context;
    private TruyenDAO truyenDAO;

    public ComicFeatures(Context context) {
        this.context = context;
        truyenDAO=new TruyenDAO(context);
    }

    public void themTruyen(ArrayList<Truyen> arrayList, TruyenAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_truyen,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        Button btnHuy=view.findViewById(R.id.truyenDialog_btnTuChoi);
        Button btnThem=view.findViewById(R.id.truyenDialog_btnDongY);
        EditText edTenTruyen=view.findViewById(R.id.truyenDialog_edTenTruyen);
        EditText edTacGia=view.findViewById(R.id.truyenDialog_edTacGia);
        EditText edNoiDung=view.findViewById(R.id.truyenDialog_edNoiDung);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTruyen=edTenTruyen.getText().toString();
                String tacGia=edTacGia.getText().toString();
                String noiDung=edNoiDung.getText().toString();

                if (tenTruyen.isEmpty()||tacGia.isEmpty()||noiDung.isEmpty()){
                    Toast.makeText(context, "Truyện không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    Truyen truyen=new Truyen(tenTruyen,tacGia,noiDung);
                    boolean check= truyenDAO.themTruyen(truyen);
                    if (check){
                        arrayList.add(truyen);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Thêm truyện thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Tên truyện đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void suaTruyen(Truyen truyen,TruyenAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_truyen,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        Button btnHuy=view.findViewById(R.id.truyenDialog_btnTuChoi);
        Button btnLuu=view.findViewById(R.id.truyenDialog_btnDongY);
        TextView tvTenChucNang=view.findViewById(R.id.truyenDialog_tvTenDialog);
        EditText edTenTruyen=view.findViewById(R.id.truyenDialog_edTenTruyen);
        EditText edTacGia=view.findViewById(R.id.truyenDialog_edTacGia);
        EditText edNoiDung=view.findViewById(R.id.truyenDialog_edNoiDung);
        tvTenChucNang.setText("Sửa truyện");
        btnLuu.setText("Lưu");
        edTenTruyen.setText(truyen.getTenTruyen());
        edTacGia.setText(truyen.getTacGia());
        edNoiDung.setText(truyen.getNoiDung());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTruyen=edTenTruyen.getText().toString();
                String tacGia=edTacGia.getText().toString();
                String noiDung=edNoiDung.getText().toString();

                if (tenTruyen.isEmpty()||tacGia.isEmpty()||noiDung.isEmpty()){
                    Toast.makeText(context, "Truyện không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        boolean check= truyenDAO.suaTruyen(truyen.getTenTruyen(),tenTruyen,tacGia,noiDung);
                        if (check){
                            truyen.setTenTruyen(tenTruyen);
                            truyen.setTacGia(tacGia);
                            truyen.setNoiDung(noiDung);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(context, "Sửa truyện thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }catch (Exception e){
                        Toast.makeText(context, "Tên truyện đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void xoaTruyen(Truyen truyen,ArrayList<Truyen> arrayList,TruyenAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Xóa truyện");
        builder.setMessage("Bạn xác nhận xóa cuốn truyện này?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check= truyenDAO.xoaTruyen(truyen);
                if (check){
                    arrayList.remove(truyen);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(context, "Xóa truyện thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }

    public void docTruyen(String tenTruyen){
        Intent intent=new Intent(context, ComicScreen.class);
        Bundle bundle=new Bundle();
        bundle.putString("tenTruyen",tenTruyen);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
