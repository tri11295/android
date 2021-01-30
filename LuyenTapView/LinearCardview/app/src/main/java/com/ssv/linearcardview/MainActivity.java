package com.ssv.linearcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvUser;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvUser = findViewById(R.id.rcv_User);
        userAdapter = new UserAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvUser.setLayoutManager(linearLayoutManager);

        userAdapter.setData(getListUser());
        rcvUser.setAdapter(userAdapter);
    }

    private List<Users> getListUser(){
        List<Users> mListUser = new ArrayList<>();
        mListUser.add(new Users(R.drawable.a,"Banh 1"));
        mListUser.add(new Users(R.drawable.d,"Banh 2"));
        mListUser.add(new Users(R.drawable.j,"Banh 3"));
        mListUser.add(new Users(R.drawable.m,"Banh 4"));
        mListUser.add(new Users(R.drawable.u,"Banh 5"));
        mListUser.add(new Users(R.drawable.v,"Banh 6"));
        return mListUser;
    }
}