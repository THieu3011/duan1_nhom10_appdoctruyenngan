package com.example.duan1_nhom10.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.dao.NguoiDungDAO;
import com.example.duan1_nhom10.features.BasicFeatures;
import com.example.duan1_nhom10.features.UserFeatures;
import com.example.duan1_nhom10.model.NguoiDung;

public class UserSettingScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private UserFeatures userFeatures;
    private NguoiDungDAO nguoiDungDAO;
    private ImageView btnMenu,btnAnHien;
    private TextView tvTenNguoiDung,tvMatKhau;
    private Button btnDoiMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        basicFeatures=new BasicFeatures(UserSettingScreen.this);
        userFeatures=new UserFeatures(UserSettingScreen.this);
        nguoiDungDAO=new NguoiDungDAO(UserSettingScreen.this);

        btnMenu=findViewById(R.id.setting_btnMenu);
        btnAnHien=findViewById(R.id.setting_btnAnHien);
        btnDoiMatKhau=findViewById(R.id.setting_btnDoiMatKhau);
        tvTenNguoiDung=findViewById(R.id.setting_tvTenNguoiDung);
        tvMatKhau=findViewById(R.id.setting_tvMatKhau);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        NguoiDung nguoiDung=nguoiDungDAO.traVeNguoiDung(bundle.getString("tenNguoiDung"));

        PopupMenu popupMenu=basicFeatures.caiDatMenu(btnMenu,nguoiDung.getTenNguoiDung());
        userFeatures.kiemTraVaiTro(popupMenu,nguoiDung);

        tvTenNguoiDung.setText("Tên người dùng: "+nguoiDung.getTenNguoiDung());
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
        btnAnHien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userFeatures.hienThiMatKhau(btnAnHien,tvMatKhau,nguoiDung.getMatKhau());
            }
        });
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userFeatures.doiMatKhau(nguoiDung);
            }
        });
    }
}