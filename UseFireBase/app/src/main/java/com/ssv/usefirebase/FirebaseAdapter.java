package com.ssv.usefirebase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseAdapter extends FirebaseRecyclerAdapter<User,FirebaseAdapter.myViewHolder> {

    public FirebaseAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull User model) {

        holder.tvName.setText(model.getHoTen());
        holder.tvLop.setText(model.getLop());

        Glide.with(holder.imgUser.getContext()).load(model.getUrlImg()).into(holder.imgUser);

        holder.btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(holder.imgUser.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_edit_user);

                EditText edtUserUrl = (EditText) dialog.findViewById(R.id.edt_user_url);
                EditText edtUserName = (EditText) dialog.findViewById(R.id.edt_user_name);
                EditText edtUserLop = (EditText) dialog.findViewById(R.id.edt_user_lop);

                Button btnUserUpd = (Button)dialog.findViewById(R.id.btn_dialog_update);
                Button btnUserCal = (Button)dialog.findViewById(R.id.btn_dialog_cal);

                edtUserUrl.setText(model.getUrlImg());
                edtUserName.setText(model.getHoTen());
                edtUserLop.setText(model.getLop());

                dialog.show();

                btnUserUpd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();
                        map.put("urlImg",edtUserUrl.getText().toString());
                        map.put("hoTen",edtUserName.getText().toString());
                        map.put("lop",edtUserLop.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("DbUser")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialog.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss();
                                    }
                                });
                    }
                });

                btnUserCal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        holder.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.imgUser.getContext());
                builder.setTitle("Delete User");
                builder.setMessage("Delete...");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("DbUser")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_user,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUser;
        TextView tvName,tvLop;
        Button btnEditUser,btnDeleteUser;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser= (ImageView)itemView.findViewById(R.id.img_user);
            tvName = (TextView)itemView.findViewById(R.id.tv_name_user);
            tvLop = (TextView)itemView.findViewById(R.id.tv_lop_user);
            btnEditUser = (Button)itemView.findViewById(R.id.btn_edit_user);
            btnDeleteUser = (Button)itemView.findViewById(R.id.btn_delete_user);
        }
    }
}
