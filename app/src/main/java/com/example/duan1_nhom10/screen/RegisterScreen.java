package com.example.duan1_nhom10.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.features.BasicFeatures;
import com.example.duan1_nhom10.features.UserFeatures;

public class RegisterScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private UserFeatures userFeatures;
    private EditText edtreUser,edtrePassword,edtConfirmPassword;
    private Button btnRegis,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        basicFeatures=new BasicFeatures(RegisterScreen.this);
        userFeatures=new UserFeatures(RegisterScreen.this);

        edtreUser = findViewById(R.id.edtreuser);
        edtrePassword = findViewById(R.id.edtrepassword);
        edtConfirmPassword = findViewById(R.id.edtconfirm);
        btnRegis = findViewById(R.id.btnregis);
        btnCancel = findViewById(R.id.btnrecancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(LoginScreen.class);
            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenNguoiDung=edtreUser.getText().toString();
                String matKhau=edtrePassword.getText().toString();
                String matKhau2=edtConfirmPassword.getText().toString();

                userFeatures.dangKy(tenNguoiDung,matKhau,matKhau2);
            }
        });

    }
}
