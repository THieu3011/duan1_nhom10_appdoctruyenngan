package com.example.duan1_nhom10.screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.adapter.TruyenAdapter;
import com.example.duan1_nhom10.dao.NguoiDungDAO;
import com.example.duan1_nhom10.dao.TruyenDAO;
import com.example.duan1_nhom10.features.BasicFeatures;
import com.example.duan1_nhom10.model.NguoiDung;
import com.example.duan1_nhom10.model.Truyen;
import java.util.ArrayList;

public class SearchComicScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private NguoiDungDAO nguoiDungDAO;
    private TruyenDAO truyenDAO;
    private ImageView btnTroLai;
    private EditText edTenTruyen;
    private RecyclerView rcTruyen;
    private ArrayList<Truyen> truyenArrayList;
    private TruyenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_screen);

        basicFeatures=new BasicFeatures(SearchComicScreen.this);
        nguoiDungDAO=new NguoiDungDAO(SearchComicScreen.this);
        truyenDAO=new TruyenDAO(SearchComicScreen.this);

        btnTroLai=findViewById(R.id.searchUser_btnTroLai);
        edTenTruyen=findViewById(R.id.searchUser_edTenNguoiDung);
        rcTruyen=findViewById(R.id.searchUser_rcNguoiDung);
        truyenArrayList=truyenDAO.toanBoTruyen();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        NguoiDung nguoiDung=nguoiDungDAO.traVeNguoiDung(bundle.getString("tenNguoiDung"));

        adapter=new TruyenAdapter(SearchComicScreen.this,truyenArrayList,nguoiDung.getVaiTro());
        rcTruyen.setAdapter(adapter);
        basicFeatures.caiDatRecycleView(rcTruyen);

        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenManNangCao(nguoiDung.getTenNguoiDung(), ComicStoreScreen.class);
            }
        });
        edTenTruyen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void filter(String text) {
        ArrayList<Truyen> ketQua = new ArrayList<>();
        for (Truyen truyen : truyenArrayList) {
            if (truyen.getTenTruyen().toLowerCase().contains(text.toLowerCase())) {
                ketQua.add(truyen);
            }
        }
        adapter.setArrayList(ketQua);
        adapter.notifyDataSetChanged();
    }
}