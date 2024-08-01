package com.example.duan1_nhom10.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.adapter.NguoiDungAdapter;
import com.example.duan1_nhom10.dao.NguoiDungDAO;
import com.example.duan1_nhom10.features.BasicFeatures;
import com.example.duan1_nhom10.features.UserFeatures;
import com.example.duan1_nhom10.model.NguoiDung;

public class ManagerUserScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private UserFeatures userFeatures;
    private NguoiDungDAO nguoiDungDAO;
    private ImageView btnMenu,btnTimKiem, btnThemUser;
    private RecyclerView rcUser;
    private NguoiDungAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user_screen);

        basicFeatures = new BasicFeatures(ManagerUserScreen.this);
        userFeatures = new UserFeatures(ManagerUserScreen.this);
        nguoiDungDAO = new NguoiDungDAO(ManagerUserScreen.this);
        adapter = new NguoiDungAdapter(ManagerUserScreen.this,nguoiDungDAO.toanBoNguoiDung(1));

        btnMenu = findViewById(R.id.managerUser_btnMenu);
        btnTimKiem = findViewById(R.id.managerUser_btnTimKiem);
        btnThemUser = findViewById(R.id.managerUser_btnThemUser);
        rcUser = findViewById(R.id.managerUser_rcUser);

        rcUser.setAdapter(adapter);
        basicFeatures.caiDatRecycleView(rcUser);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        NguoiDung nguoiDung = nguoiDungDAO.traVeNguoiDung(bundle.getString("tenNguoiDung"));

        PopupMenu popupMenu = basicFeatures.caiDatMenu(btnMenu,nguoiDung.getTenNguoiDung());
        userFeatures.kiemTraVaiTro(popupMenu,nguoiDung);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenManNangCao(nguoiDung.getTenNguoiDung(), SearchUserScreen.class);
            }
        });
        btnThemUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFeatures.themNguoiDung(adapter.getArrayList(),adapter);
            }
        });

    }
}