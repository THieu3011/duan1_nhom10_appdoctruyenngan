package com.example.duan1_nhom10.screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.dao.TruyenDAO;
import com.example.duan1_nhom10.model.Truyen;

public class ComicScreen extends AppCompatActivity {

    private TruyenDAO truyenDAO;
    private ImageView btnTroLai;
    private TextView tvTenTruyen,tvTacGia,tvNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_screen);

        truyenDAO=new TruyenDAO(ComicScreen.this);

        btnTroLai=findViewById(R.id.comicScreen_btnTroLai);
        tvTenTruyen=findViewById(R.id.comicScreen_tvTenTruyen);
        tvTacGia=findViewById(R.id.comicScreen_tvTacGia);
        tvNoiDung=findViewById(R.id.comicScreen_tvNoiDung);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        Truyen truyen=truyenDAO.truyenCuThe(bundle.getString("tenTruyen"));

        tvTenTruyen.setText(truyen.getTenTruyen());
        tvTacGia.setText(truyen.getTacGia());
        tvNoiDung.setText(truyen.getNoiDung());
        tvNoiDung.setMovementMethod(new ScrollingMovementMethod());
        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}