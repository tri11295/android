package com.example.listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> listData;
//    private String[] ListData;
    private ListView listView1;
    private ArrayAdapter<String> adapter1;
    private Context context;
    private EditText editText;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private int possionListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        arrData = context.getResources().getStringArray(R.array.ListData);

        context = this;
        listView1 = (ListView)findViewById(R.id.listView1);
        editText = (EditText)findViewById(R.id.edit_text);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        listData = new ArrayList<>();
        listData.add("Hello");
        adapter1 = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,listData);
        listView1.setAdapter(adapter1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editText.getText().toString().trim();
                if(TextUtils.isEmpty(item)) {
                    Toast.makeText(context, "Bạn chưa nhập đủ dữ liệu", Toast.LENGTH_LONG).show();
                    return;
                }
                listData.add(item);
                adapter1.notifyDataSetChanged();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData.set(possionListData,editText.getText().toString().trim());
                adapter1.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Xác nhận");
                dialog.setMessage("Bạn có đồng ý xóa");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listData.remove(possionListData);
                        adapter1.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                possionListData = position;
                editText.setText(listData.get(position));
            }
        });
    }
}