package com.example.duan1_nhom10.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.features.BasicFeatures;
import com.example.duan1_nhom10.features.UserFeatures;

public class LoginScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private UserFeatures userFeatures;
    private EditText edtUser, edtPassword;
    private Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        basicFeatures=new BasicFeatures(LoginScreen.this);
        userFeatures=new UserFeatures(LoginScreen.this);

        edtUser = findViewById(R.id.edtuser);
        edtPassword = findViewById(R.id.edtpassword);
        btnRegister = findViewById(R.id.btnregister);
        btnLogin = findViewById(R.id.btnlogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(RegisterScreen.class);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenNguoiDung=edtUser.getText().toString();
                String matKhau=edtPassword.getText().toString();

                userFeatures.kiemTraDangNhap(tenNguoiDung,matKhau);
            }
        });

    }
}