package com.example.listviewcountry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Nuoc> arrayList;
    NuocAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lvNuoc);
        arrayList = new ArrayList<>();
        arrayList.add(new Nuoc("Việt Nam","Hà Nội",R.drawable.fl2));
        arrayList.add(new Nuoc("Pháp","Pari",R.drawable.td3));
        arrayList.add(new Nuoc("Nhật","tokyo",R.drawable.eng));
        arrayList.add(new Nuoc("Hàn Quốc","Hà Nội",R.drawable.tl));
        adapter = new NuocAdapter(this,R.layout.dong_nuoc,arrayList);
        lv.setAdapter(adapter);
    }
}