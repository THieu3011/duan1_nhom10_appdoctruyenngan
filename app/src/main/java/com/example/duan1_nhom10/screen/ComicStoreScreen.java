package com.example.duan1_nhom10.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.adapter.TruyenAdapter;
import com.example.duan1_nhom10.dao.NguoiDungDAO;
import com.example.duan1_nhom10.dao.TruyenDAO;
import com.example.duan1_nhom10.features.BasicFeatures;
import com.example.duan1_nhom10.features.ComicFeatures;
import com.example.duan1_nhom10.features.UserFeatures;
import com.example.duan1_nhom10.model.NguoiDung;

public class ComicStoreScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private ComicFeatures comicFeatures;
    private UserFeatures userFeatures;
    private NguoiDungDAO nguoiDungDAO;
    private TruyenDAO truyenDAO;
    private TruyenAdapter truyenAdapter;
    private ImageView btnMenu, btnTimKiem, btnThemTruyen;
    private RecyclerView rcTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_store_screen);

        basicFeatures=new BasicFeatures(ComicStoreScreen.this);
        comicFeatures=new ComicFeatures(ComicStoreScreen.this);
        userFeatures=new UserFeatures(ComicStoreScreen.this);
        nguoiDungDAO=new NguoiDungDAO(ComicStoreScreen.this);
        truyenDAO=new TruyenDAO(ComicStoreScreen.this);

        btnMenu=findViewById(R.id.khoTruyen_btnMenu);
        btnTimKiem=findViewById(R.id.khoTruyen_btnTimKiem);
        btnThemTruyen=findViewById(R.id.khoTruyen_btnThemTruyen);
        rcTruyen=findViewById(R.id.khoTruyen_rcTruyen);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        NguoiDung nguoiDung=nguoiDungDAO.traVeNguoiDung(bundle.getString("tenNguoiDung"));
        if (nguoiDung.getVaiTro()==0){
            btnThemTruyen.setVisibility(View.VISIBLE);
        }

        truyenAdapter=new TruyenAdapter(ComicStoreScreen.this,truyenDAO.toanBoTruyen(),nguoiDung.getVaiTro());
        basicFeatures.caiDatRecycleView(rcTruyen);
        rcTruyen.setAdapter(truyenAdapter);

        PopupMenu popupMenu=basicFeatures.caiDatMenu(btnMenu,nguoiDung.getTenNguoiDung());
        userFeatures.kiemTraVaiTro(popupMenu,nguoiDung);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
        btnThemTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comicFeatures.themTruyen(truyenAdapter.getArrayList(),truyenAdapter);
            }
        });
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenManNangCao(nguoiDung.getTenNguoiDung(),SearchComicScreen.class);
            }
        });
    }
}
