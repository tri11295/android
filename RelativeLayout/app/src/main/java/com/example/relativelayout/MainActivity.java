package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtSo1, txtSo2;
    Button btnTinhTong;
    TextView ketQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSo1 = findViewById(R.id.txSo1);
        txtSo2 = findViewById(R.id.txSo2);
        btnTinhTong = findViewById(R.id.btnTinhTong);
        ketQua = findViewById(R.id.ketQua);

    }

    public void getSum(View view) {
        int so1 = Integer.parseInt(txtSo1.getText().toString().trim());
        int so2 = Integer.parseInt(txtSo2.getText().toString().trim());
        int sum = so1 + so2;
        ketQua.setText(String.valueOf(sum));
    }


}