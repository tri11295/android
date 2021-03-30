package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Rcv extends AppCompatActivity {

    private RecyclerView rcvUser;
    private Button btnAdd;
    private EditText edtEmail,edtAddress;

    private User1Adapter user1Adapter;
    private User1ViewModel user1ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcv);

        edtEmail = findViewById(R.id.edt_email1);
        edtAddress = findViewById(R.id.edt_address1);

        rcvUser = findViewById(R.id.rcv_user);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);

        user1ViewModel = new ViewModelProvider(this).get(User1ViewModel.class);

        user1ViewModel.getListUser1LiveData().observe(this, new Observer<List<User1>>() {
            @Override
            public void onChanged(List<User1> user1s) {
                user1Adapter = new User1Adapter(user1s);
                rcvUser.setAdapter(user1Adapter);
            }
        });

    }

    private void addUser(){
        String email = edtEmail.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        User1 user1 = new User1(R.drawable.img_avatar2,email,address);
        user1ViewModel.addUser(user1);
    }
}