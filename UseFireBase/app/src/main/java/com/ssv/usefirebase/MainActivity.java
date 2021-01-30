package com.ssv.usefirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvSinhVien;
    private SinhVienAdapter sinhVienAdapter;
    private ArrayList<SinhVien> listData;
    private EditText edtEmail,edtName,edtSdt,edtTuoi;
    private Button btnAdd;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSinhVien = findViewById(R.id.lv_sinhVien);
        edtEmail = findViewById(R.id.ed_email);
        edtName = findViewById(R.id.ed_name);
        edtSdt = findViewById(R.id.ed_sdt);
        edtTuoi = findViewById(R.id.ed_tuoi);
        btnAdd = findViewById(R.id.btn_add);
        listData = new ArrayList<>();

        GetData();

        sinhVienAdapter = new SinhVienAdapter(this,R.layout.singel_sinh_vien,listData);
        lvSinhVien.setAdapter(sinhVienAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String name = edtName.getText().toString();
                String sdt = edtSdt.getText().toString();


                int tuoi =  Integer.parseInt(edtTuoi.getText().toString()) ;

                SinhVien sinhVien = new SinhVien(email,name,sdt,tuoi);

                DatabaseReference myRef = database.getReference("DbSinhVien");
                String id = myRef.push().getKey();
                myRef.child(id).setValue(sinhVien).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void DeleteSinhVien(String id){
        DatabaseReference myRef = database.getReference("DbSinhVien");
        myRef.child(id).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(getApplicationContext(),"Xóa thành công",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void DialogUpdate(SinhVien sv){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update);

        EditText edt_sv_Email = (EditText) dialog.findViewById(R.id.edt_sv_email);
        EditText edt_sv_Name = (EditText) dialog.findViewById(R.id.edt_sv_name);
        EditText edt_sv_Sdt = (EditText) dialog.findViewById(R.id.edt_sv_sdt);
        EditText edt_sv_Tuoi = (EditText) dialog.findViewById(R.id.edt_sv_tuoi);

        Button btnUpd = (Button)dialog.findViewById(R.id.btnUpd);
        Button btnCal = (Button)dialog.findViewById(R.id.btnCal);

        edt_sv_Email.setText(sv.getEmail());
        edt_sv_Name.setText(sv.getHoTen());
        edt_sv_Sdt.setText(sv.getSdt());
        edt_sv_Tuoi.setText(sv.getTuoi());

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference("DbSinhVien");
                myRef.child(sv.getId()).child("email").setValue(edt_sv_Email.getText()+"");
                myRef.child(sv.getId()).child("hoTen").setValue(edt_sv_Name.getText()+"");
                myRef.child(sv.getId()).child("sdt").setValue(edt_sv_Sdt.getText()+"");
                myRef.child(sv.getId()).child("tuoi").setValue(Integer.parseInt(edt_sv_Tuoi.getText().toString()));
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();

            }
        });
        dialog.show();
    }

    private void GetData(){
        DatabaseReference myRef = database.getReference("DbSinhVien");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sinhVienAdapter.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    SinhVien sinhVien = data.getValue(SinhVien.class);
                    sinhVien.setId(data.getKey());

                    listData.add(sinhVien);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Load Data Fail"
                +databaseError.toString(),Toast.LENGTH_LONG).show();
                Log.d("MYTAG","onCancelled"+ databaseError.toString());
            }
        });

//        myRef.orderByChild("hoTen").equalTo("yenhai").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                sinhVienAdapter.clear();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//
//                    SinhVien sinhVien = dataSnapshot1.getValue(SinhVien.class);
//                    sinhVien.setId(dataSnapshot1.getKey());
//                    listData.add(sinhVien);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                throw databaseError.toException();
//            }
//        });
    }
}