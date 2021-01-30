package com.example.mvc_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edt_Email, edt_PassWord;
    private TextView tv_Message;
    private Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitControl();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickLogin();
            }
        });

    }

    private void InitControl(){
        edt_Email = findViewById(R.id.edt_email);
        edt_PassWord = findViewById(R.id.edt_password);
        tv_Message = findViewById(R.id.tv_message);
        btn_Login = findViewById(R.id.btn_login);
    }

    private void ClickLogin(){
        String email = edt_Email.getText().toString().trim();
        String passWord = edt_PassWord.getText().toString().trim();
        User user = new User(email,passWord);
        tv_Message.setVisibility(View.VISIBLE);
        if (user.IsEmail() && user.IsPassWord()){
            tv_Message.setText("Login Success");
            tv_Message.setTextColor(getResources().getColor(R.color.design_default_color_primary));
        }else {
            tv_Message.setText("Email or PassWord is not Valid");
            tv_Message.setTextColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
}