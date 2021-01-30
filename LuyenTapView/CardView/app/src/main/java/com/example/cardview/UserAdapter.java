package com.example.cardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mcontext;
    private List<Users> mListUser;

    public UserAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setData(List<Users> list){
        this.mListUser = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users users = mListUser.get(position);
        if (users == null){
            return;
        }
//        holder.imageView.setImageResource(users.getResourceImage());
        Glide.with(mcontext).load(users.getUrlImage()).into(holder.imageView);
        holder.textView.setText(users.getName());
    }

    @Override
    public int getItemCount() {
        if (mListUser != null){
            return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.img_user);
            textView = (TextView)itemView.findViewById(R.id.tv_name);
        }
    }
}
