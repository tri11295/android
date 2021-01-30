package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvUser;
    private UserAdapter mUserAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvUser = findViewById(R.id.rcv_user);
        mUserAdapter = new UserAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rcvUser.setLayoutManager(gridLayoutManager);

        mUserAdapter.setData(getListUser());
        rcvUser.setAdapter(mUserAdapter);

    }

    private List<Users> getListUser(){
        List<Users> list = new ArrayList<>();
        list.add(new Users("https://cdn.tgdd.vn/Products/Images/42/230867/samsung-galaxy-note-20-ultra-5g-trang-600x600-2-400x400.jpg","User Name 1"));
        list.add(new Users("https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-hopmoi-den-600x600-400x400.jpg","User Name 2"));
        list.add(new Users("https://cdn.tgdd.vn/Products/Images/42/214925/TimerThumb/xiaomi-redmi-note-9-(4).jpg","User Name 3"));
        list.add(new Users("https://cdn.tgdd.vn/Products/Images/42/226226/realme-c15-600-400x400.jpg","User Name 3"));
        list.add(new Users("https://cdn.tgdd.vn/Products/Images/42/229056/TimerThumb/oppo-a93.jpg","User Name 4"));
        list.add(new Users("https://cdn.tgdd.vn/Products/Images/42/230407/iphone-11-64gb-hop-moi-292420-102454-400x400.jpg","User Name 5"));
        return list;
    }
}