package com.example.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class User1Adapter extends RecyclerView.Adapter<User1Adapter.User1ViewHolder> {

    private List<User1> listUser1;

    public User1Adapter(List<User1> listUser1) {
        this.listUser1 = listUser1;
    }

    @NonNull
    @Override
    public User1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new User1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User1ViewHolder holder, int position) {
        User1 user1 = listUser1.get(position);
        if (listUser1 == null){
            return;
        }

        holder.imgAvater.setImageResource(user1.getImgAvatar());
        holder.tvEmail.setText(user1.getEmail());
        holder.tvAddress.setText(user1.getAddress());
    }

    @Override
    public int getItemCount() {
        if (listUser1 != null){
            return listUser1.size();
        }
        return 0;
    }

    public class User1ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgAvater;
        private TextView tvEmail,tvAddress;

        public User1ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvater = itemView.findViewById(R.id.img_avatar);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }
}
