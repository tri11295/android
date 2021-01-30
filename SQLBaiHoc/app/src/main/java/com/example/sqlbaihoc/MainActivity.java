package com.example.sqlbaihoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BaiHocHelper baiHocHelper;
    ListView listView;
    ArrayList<BaiHoc> arrayList;
    BaiHocAdapter baiHocAdapter;
    Button btnAdd;
    Button btnSearch;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baiHoc = editText.getText().toString().trim();
                baiHocHelper.QueryData("INSERT INTO NoiDung VALUES (null,'"+baiHoc+"')");
                actionGetData();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baiHocTimKiem = editText.getText().toString().trim();
                arrayList.clear();
                Cursor dataBaiHoc = baiHocHelper.GetData("SELECT * FROM NoiDung WHERE TenNoiDung LIKE '%"+baiHocTimKiem+"%'");
                while (dataBaiHoc.moveToNext()){
                    String ten = dataBaiHoc.getString(1);
                    int id = dataBaiHoc.getInt(0);
                    arrayList.add(new BaiHoc(id,ten));
                }
                baiHocAdapter.notifyDataSetChanged();
            }
        });

//        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS NoiDung(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenNoiDung VARCHAR(200))");

        actionGetData();
    }

    public void DiaLogUpdate(int id, String ten){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        EditText editTextSua = (EditText) dialog.findViewById(R.id.edt_Sua);
        Button btnUpd = (Button)dialog.findViewById(R.id.btnUpd);
        Button btnCal = (Button)dialog.findViewById(R.id.btnCal);
        editTextSua.setText(ten);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = editTextSua.getText().toString();
                baiHocHelper.QueryData("UPDATE NoiDung SET TenNoiDung = '"+tenMoi+"' WHERE Id = '"+id+"' ");
                actionGetData();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void DiaLogDel(int id, String ten){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn xóa '"+ten+"' không ?");

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                baiHocHelper.QueryData("DELETE FROM NoiDung WHERE Id = '"+id+"'");
                actionGetData();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void actionGetData(){
        arrayList.clear();
        Cursor dataBaiHoc = baiHocHelper.GetData("SELECT * FROM NoiDung");
        while (dataBaiHoc.moveToNext()){
            String ten = dataBaiHoc.getString(1);
            int id = dataBaiHoc.getInt(0);
            arrayList.add(new BaiHoc(id,ten));
        }
        baiHocAdapter.notifyDataSetChanged();
    }

    private void InitView(){
        listView = (ListView)findViewById(R.id.lvNoiDung);
        arrayList = new ArrayList<>();
        baiHocAdapter = new BaiHocAdapter(this,R.layout.dong_bai_hoc,arrayList);
        listView.setAdapter(baiHocAdapter);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        editText = (EditText)findViewById(R.id.etdBaiHoc);
        baiHocHelper = new BaiHocHelper(this,"BaiHoc.sqlite",null,1);
    }
}