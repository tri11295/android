package com.ssv.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edt_firstName,edt_lastName;
    TextView tv_test;
    Button btn_add;
    List<User> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_test = findViewById(R.id.tv_test);

        edt_firstName = findViewById(R.id.edt_firstName);
        edt_lastName = findViewById(R.id.edt_lastName);
        btn_add = findViewById(R.id.btn_add);
        listUser= new ArrayList<>();

        listUser = UserDatabase.getInstance(this).userDao().getAll();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser(){
        String firstName = edt_firstName.getText().toString();
        String lastName = edt_lastName.getText().toString();

        User user = new User(firstName,lastName);

        UserDatabase.getInstance(this).userDao().insertUser(user);

        listUser = UserDatabase.getInstance(this).userDao().getAll();

        String test = "";

        if (listUser.size() > 0){
            for (User user1 : listUser){
                test = String.valueOf(user1.getUid()) + user1.getFirstName() + user1.getLastName() + "   ";
            }
            tv_test.setText(test);
        }
    }
}