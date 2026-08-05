package com.example.belajarpostman_11pplg3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.belajarpostman_11pplg3.R;
import com.example.belajarpostman_11pplg3.UserModel;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserModel> userList;

    public UserAdapter(Context context, ArrayList<UserModel> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel user = userList.get(position);

        holder.txtName.setText(user.getName());
        holder.txtUsername.setText(user.getUsername());
        holder.txtEmail.setText(user.getEmail());
        holder.txtAddress.setText(user.getAddress());
        holder.txtPhone.setText(user.getPhone());
        holder.txtWebsite.setText(user.getWebsite());
        holder.txtCompany.setText(user.getCompany());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtUsername, txtEmail, txtAddress, txtPhone, txtWebsite, txtCompany;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtWebsite = itemView.findViewById(R.id.txtWebsite);
            txtCompany = itemView.findViewById(R.id.txtCompany);
        }
    }
}