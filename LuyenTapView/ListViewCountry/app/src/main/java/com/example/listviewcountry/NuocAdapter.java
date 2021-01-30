package com.example.listviewcountry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NuocAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Nuoc> nuocList;

    public NuocAdapter(Context context, int layout, List<Nuoc> nuocList) {
        this.context = context;
        this.layout = layout;
        this.nuocList = nuocList;
    }

    @Override
    public int getCount() {
        return nuocList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvTenNuoc,tvThuDo;
        ImageView imgCo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            // anh xa
            viewHolder.tvTenNuoc = (TextView) convertView.findViewById(R.id.tvTenNuoc);
            viewHolder.tvThuDo = (TextView) convertView.findViewById(R.id.tvThuDo);
            viewHolder.imgCo = (ImageView) convertView.findViewById(R.id.imgCo);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // gan gia tri
        viewHolder.tvTenNuoc.setText(nuocList.get(position).tenNuoc);
        viewHolder.tvThuDo.setText(nuocList.get(position).thuDo);
        viewHolder.imgCo.setImageResource(nuocList.get(position).hinhAnh);
        return convertView;
    }
}
