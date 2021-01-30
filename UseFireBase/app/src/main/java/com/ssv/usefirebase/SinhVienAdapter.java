package com.ssv.usefirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {

    private MainActivity activity;
    private int resource;
    private List<SinhVien> objects;

    public SinhVienAdapter(@NonNull MainActivity activity, int resource, @NonNull List<SinhVien> objects) {
        super(activity, resource, objects);

        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = this.activity.getLayoutInflater();
            View view = inflater.inflate(this.resource,null);

            TextView tvEmail = view.findViewById(R.id.tv_email);
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvSdt = view.findViewById(R.id.tv_sdt);
            TextView tvTuoi = view.findViewById(R.id.tv_tuoi);
            Button btnSvUpdate = view.findViewById(R.id.btn_sv_upd);
            Button btnSvDelete = view.findViewById(R.id.btn_sv_del);

            SinhVien sinhVien = this.objects.get(position);

            tvEmail.setText(sinhVien.getEmail().toString());
            tvName.setText(sinhVien.getHoTen().toString());
            tvSdt.setText(sinhVien.getSdt().toString());

            tvTuoi.setText(String.valueOf(sinhVien.getTuoi()));

            btnSvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.DeleteSinhVien(sinhVien.getId());
                }
            });

            btnSvUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.DialogUpdate(sinhVien);
                }
            });

        return view;
    }
}
