package com.example.sqlbaihoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BaiHocAdapter extends BaseAdapter {

    MainActivity context;
    int layout;
    List<BaiHoc> arrList;

    public BaiHocAdapter(MainActivity context, int layout, List<BaiHoc> arrList) {
        this.context = context;
        this.layout = layout;
        this.arrList = arrList;
    }

    @Override
    public int getCount() {
        return arrList.size();
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
        TextView tvTen ;
        ImageView imgSua, imgXoa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder ;

        if( convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();

            viewHolder.tvTen = (TextView)convertView.findViewById(R.id.tvTen);
            viewHolder.imgSua = (ImageView)convertView.findViewById(R.id.imgSua);
            viewHolder.imgXoa = (ImageView)convertView.findViewById(R.id.imgXoa);

            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder)convertView.getTag();
        }

        final BaiHoc baiHoc = arrList.get(position);
        viewHolder.tvTen.setText(arrList.get(position).getTenBai());

        viewHolder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DiaLogUpdate(baiHoc.getId(),baiHoc.getTenBai());
            }
        });

        viewHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DiaLogDel(baiHoc.getId(),baiHoc.getTenBai());
            }
        });

        return convertView;

    }
}
