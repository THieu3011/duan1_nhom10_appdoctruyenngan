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
import com.example.duan1_nhom10.adapter.NguoiDungAdapter;
import com.example.duan1_nhom10.dao.NguoiDungDAO;
import com.example.duan1_nhom10.features.BasicFeatures;
import com.example.duan1_nhom10.model.NguoiDung;
import java.util.ArrayList;

public class SearchUserScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private NguoiDungDAO nguoiDungDAO;
    private ImageView btnTroLai;
    private EditText edTenNguoiDung;
    private RecyclerView rcNguoiDung;
    private NguoiDungAdapter adapter;
    private ArrayList<NguoiDung> nguoiDungArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_screen);

        basicFeatures=new BasicFeatures(SearchUserScreen.this);
        nguoiDungDAO=new NguoiDungDAO(SearchUserScreen.this);

        btnTroLai=findViewById(R.id.searchUser_btnTroLai);
        edTenNguoiDung=findViewById(R.id.searchUser_edTenNguoiDung);
        rcNguoiDung=findViewById(R.id.searchUser_rcNguoiDung);
        nguoiDungArrayList=nguoiDungDAO.toanBoNguoiDung(1);
        adapter=new NguoiDungAdapter(SearchUserScreen.this,nguoiDungArrayList);
        rcNguoiDung.setAdapter(adapter);
        basicFeatures.caiDatRecycleView(rcNguoiDung);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        NguoiDung nguoiDung=nguoiDungDAO.traVeNguoiDung(bundle.getString("tenNguoiDung"));

        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenManNangCao(nguoiDung.getTenNguoiDung(), ManagerUserScreen.class);
            }
        });
        edTenNguoiDung.addTextChangedListener(new TextWatcher() {
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
        ArrayList<NguoiDung> ketQua = new ArrayList<>();
        for (NguoiDung nguoiDung : nguoiDungArrayList) {
            if (nguoiDung.getTenNguoiDung().toLowerCase().contains(text.toLowerCase())) {
                ketQua.add(nguoiDung);
            }
        }
        adapter.setArrayList(ketQua);
        adapter.notifyDataSetChanged();
    }
}