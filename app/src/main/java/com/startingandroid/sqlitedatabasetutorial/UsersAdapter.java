package com.startingandroid.sqlitedatabasetutorial;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zeeshan Ahmed on 1/25/2016.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private ArrayList<User> mDataSet;

    public UsersAdapter(ArrayList<User> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(v);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.name_entry.setText(mDataSet.get(position).getName());
        holder.email_entry.setText(mDataSet.get(position).getEmail());
        holder.icon_entry.setText(""+mDataSet.get(position).getName().charAt(0));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name_entry, email_entry, icon_entry;

        UserViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.user_layout);
            name_entry = (TextView) itemView.findViewById(R.id.name_entry);
            email_entry = (TextView) itemView.findViewById(R.id.email_entry);
            icon_entry = (TextView) itemView.findViewById(R.id.icon_entry);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}