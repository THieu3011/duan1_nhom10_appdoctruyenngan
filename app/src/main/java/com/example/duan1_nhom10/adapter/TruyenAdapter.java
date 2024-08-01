package com.example.duan1_nhom10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.features.ComicFeatures;
import com.example.duan1_nhom10.model.Truyen;

import java.util.ArrayList;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.TruyenHolder>{

    private Context context;
    private ArrayList<Truyen> arrayList;
    private int vaiTro;

    public TruyenAdapter(Context context, ArrayList<Truyen> arrayList, int vaiTro) {
        this.context = context;
        this.arrayList = arrayList;
        this.vaiTro = vaiTro;
    }

    public ArrayList<Truyen> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Truyen> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TruyenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_truyen,parent,false);
        return new TruyenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenHolder holder, int position) {
        Truyen truyen=arrayList.get(position);
        ComicFeatures comicFeatures=new ComicFeatures(context);

        holder.tvTenTruyen.setText(truyen.getTenTruyen());
        holder.tvTacGia.setText(truyen.getTacGia());
        if (vaiTro!=0){
            holder.layoutNutBam.setVisibility(View.GONE);
        }

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comicFeatures.xoaTruyen(truyen,arrayList,TruyenAdapter.this);
            }
        });
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comicFeatures.suaTruyen(truyen,TruyenAdapter.this);
            }
        });
        holder.tvTenTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comicFeatures.docTruyen(truyen.getTenTruyen());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comicFeatures.docTruyen(truyen.getTenTruyen());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class TruyenHolder extends RecyclerView.ViewHolder {

        private TextView tvTenTruyen,tvTacGia;
        private ImageView btnSua,btnXoa;
        private LinearLayout layoutNutBam;

        public TruyenHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTruyen=itemView.findViewById(R.id.khoTruyen_tvTenTruyen);
            tvTacGia=itemView.findViewById(R.id.khoTruyen_tvTacGia);
            btnSua=itemView.findViewById(R.id.khoTruyen_btnSuaTruyen);
            btnXoa=itemView.findViewById(R.id.khoTruyen_btnXoatruyen);
            layoutNutBam=itemView.findViewById(R.id.khoTruyen_layoutNutBam);
        }
    }
}
