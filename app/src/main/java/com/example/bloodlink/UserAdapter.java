package com.example.bloodlink;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private final Context context;
    private final ArrayList<Users> userList;

    public UserAdapter(Context context, ArrayList<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = userList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTxt, bloodTypeTxt, medTxt;
        ImageButton chatBtn,callBtn;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTxt = itemView.findViewById(R.id.usernametxt);
            bloodTypeTxt = itemView.findViewById(R.id.bloodtypetxt);
            medTxt = itemView.findViewById(R.id.medDiseasetxt);
        }

        public void bind(Users user) {
            usernameTxt.setText(user.getUser());
            String bt="Blood Type : "+user.getBloodType();
            bloodTypeTxt.setText(bt);
            String md="Medical Disease : "+user.getmedicalDisease();
            medTxt.setText(String.valueOf(md));


            callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform call action
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + user.getConatactNum()));
                    context.startActivity(callIntent);
                }
            });

            // Set click listener for chat button
            chatBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  /*  Intent chatIntent = new Intent(context, chatActivity.class);

                    context.startActivity(chatIntent);*/
                }
            });
        }
    }
}
