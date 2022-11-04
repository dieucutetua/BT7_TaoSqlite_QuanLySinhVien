package com.example.sql_quanlysinhvien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private MainActivity context_118;
    private int layout_118;
    private List<SinhVien> sinhVienList_118;

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhVienList) {
        this.context_118 = context;
        this.layout_118 = layout;
        this.sinhVienList_118 = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList_118.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView tvTen_118;
        ImageView imgSua_118, imgXoa_118;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null ){
            viewHolder  = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)  context_118.getSystemService(context_118.LAYOUT_INFLATER_SERVICE);
            view  = layoutInflater.inflate(layout_118,null);

            viewHolder.tvTen_118 = (TextView) view.findViewById(R.id.tvTen_118);
            viewHolder.imgSua_118 = (ImageView) view.findViewById(R.id.btnSua_118);
            viewHolder.imgXoa_118 = (ImageView) view.findViewById(R.id.btnXoa_118);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();



        }
        final SinhVien sinhVien = sinhVienList_118.get(i);
        viewHolder.tvTen_118.setText(sinhVien.getTenSV());


        viewHolder.imgSua_118.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context_118.DiaglogUpdate(sinhVien.getTenSV(), sinhVien.getId());

            }
        });
        viewHolder.imgXoa_118.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context_118.DiaglogDelete(sinhVien.getTenSV(), sinhVien.getId());

            }
        });

        return view;
    }
}
