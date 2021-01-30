package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Banh> arrayList;
    BanhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gv);

        arrayList = new ArrayList<>();
        arrayList.add(new Banh("Bánh ngọt","https://cdn.tgdd.vn/Products/Images/42/230867/samsung-galaxy-note-20-ultra-5g-trang-600x600-2-400x400.jpg"));
        arrayList.add(new Banh("Bánh mặn","https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-hopmoi-den-600x600-400x400.jpg"));
        arrayList.add(new Banh("Bánh kem","https://cdn.tgdd.vn/Products/Images/42/214925/TimerThumb/xiaomi-redmi-note-9-(4).jpg"));
        arrayList.add(new Banh("Bánh dâu","https://cdn.tgdd.vn/Products/Images/42/226226/realme-c15-600-400x400.jpg"));
        arrayList.add(new Banh("Bánh đào","https://cdn.tgdd.vn/Products/Images/42/229056/TimerThumb/oppo-a93.jpg"));
        arrayList.add(new Banh("Bánh vải","https://cdn.tgdd.vn/Products/Images/42/230407/iphone-11-64gb-hop-moi-292420-102454-400x400.jpg"));
        arrayList.add(new Banh("Bánh ngọt","https://cdn.tgdd.vn/Products/Images/42/230867/samsung-galaxy-note-20-ultra-5g-trang-600x600-2-400x400.jpg"));
        arrayList.add(new Banh("Bánh mặn","https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-hopmoi-den-600x600-400x400.jpg"));
        arrayList.add(new Banh("Bánh kem","https://cdn.tgdd.vn/Products/Images/42/214925/TimerThumb/xiaomi-redmi-note-9-(4).jpg"));
        arrayList.add(new Banh("Bánh dâu","https://cdn.tgdd.vn/Products/Images/42/226226/realme-c15-600-400x400.jpg"));
        arrayList.add(new Banh("Bánh đào","https://cdn.tgdd.vn/Products/Images/42/229056/TimerThumb/oppo-a93.jpg"));
        arrayList.add(new Banh("Bánh vải","https://cdn.tgdd.vn/Products/Images/42/230407/iphone-11-64gb-hop-moi-292420-102454-400x400.jpg"));
        arrayList.add(new Banh("Bánh ngọt","https://cdn.tgdd.vn/Products/Images/42/230867/samsung-galaxy-note-20-ultra-5g-trang-600x600-2-400x400.jpg"));
        arrayList.add(new Banh("Bánh mặn","https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-hopmoi-den-600x600-400x400.jpg"));
        arrayList.add(new Banh("Bánh kem","https://cdn.tgdd.vn/Products/Images/42/214925/TimerThumb/xiaomi-redmi-note-9-(4).jpg"));
        arrayList.add(new Banh("Bánh dâu","https://cdn.tgdd.vn/Products/Images/42/226226/realme-c15-600-400x400.jpg"));
        arrayList.add(new Banh("Bánh đào","https://cdn.tgdd.vn/Products/Images/42/229056/TimerThumb/oppo-a93.jpg"));
        arrayList.add(new Banh("Bánh vải","https://cdn.tgdd.vn/Products/Images/42/230407/iphone-11-64gb-hop-moi-292420-102454-400x400.jpg"));
        arrayList.add(new Banh("Bánh ngọt","https://cdn.tgdd.vn/Products/Images/42/230867/samsung-galaxy-note-20-ultra-5g-trang-600x600-2-400x400.jpg"));
        arrayList.add(new Banh("Bánh mặn","https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-hopmoi-den-600x600-400x400.jpg"));
        arrayList.add(new Banh("Bánh kem","https://cdn.tgdd.vn/Products/Images/42/214925/TimerThumb/xiaomi-redmi-note-9-(4).jpg"));
        arrayList.add(new Banh("Bánh dâu","https://cdn.tgdd.vn/Products/Images/42/226226/realme-c15-600-400x400.jpg"));
        arrayList.add(new Banh("Bánh đào","https://cdn.tgdd.vn/Products/Images/42/229056/TimerThumb/oppo-a93.jpg"));
        arrayList.add(new Banh("Bánh vải","https://cdn.tgdd.vn/Products/Images/42/230407/iphone-11-64gb-hop-moi-292420-102454-400x400.jpg"));


        adapter = new BanhAdapter(this, R.layout.dong_banh, arrayList);
        gridView.setAdapter( adapter );

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,arrayList.get(position).tenBanh.toString().trim(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}