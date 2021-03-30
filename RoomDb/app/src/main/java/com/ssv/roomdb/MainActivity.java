package com.ssv.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edt_firstName,edt_lastName,edt_id;
    TextView tv_test;
    Button btn_add,btn_update,btn_delete;
    List<User> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_test = findViewById(R.id.tv_test);

        edt_firstName = findViewById(R.id.edt_firstName);
        edt_lastName = findViewById(R.id.edt_lastName);
        edt_id = findViewById(R.id.edt_id);

        btn_add = findViewById(R.id.btn_add);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        listUser= new ArrayList<>();

        showUser();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = edt_firstName.getText().toString();
                String lastName = edt_lastName.getText().toString();
                int id = Integer.parseInt(edt_id.getText().toString());

                UpdateUser(firstName,lastName,id);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });
    }

    private void showUser(){
        listUser = UserDatabase.getInstance(this).userDao().getAll();
        if (listUser.size() > 0){
            String test = "";
            for (User user1 : listUser){
                test = test + String.valueOf(user1.getUid()) + user1.getFirstName() + user1.getLastName() + "   ";
            }
            tv_test.setText(test);
        }
    }

    private void addUser(){
        String firstName = edt_firstName.getText().toString();
        String lastName = edt_lastName.getText().toString();
        User user = new User(firstName,lastName);

        if (checkFirstUserExsit(user)){
            Toast.makeText(this,"First Name bị trùng",Toast.LENGTH_SHORT).show();
            return;
        }

        UserDatabase.getInstance(this).userDao().insertUser(user);
        showUser();
    }

    private boolean checkFirstUserExsit(User user){
        List<User> list = UserDatabase.getInstance(this).userDao().checkUser(user.getFirstName());
        if (list.size() > 0){
            return true;
        }
        return false;
    }

    private void UpdateUser(String firstName, String lastName,int id){
        int check = UserDatabase.getInstance(this).userDao().updateUser(firstName,lastName,id);
        if (check > 0){
            showUser();
        }
    }

    private void deleteUser(){
        int id = Integer.parseInt(edt_id.getText().toString());
        int check = UserDatabase.getInstance(this).userDao().deleteUser(id);
        if (check > 0 ){
            showUser();
        }
    }
}