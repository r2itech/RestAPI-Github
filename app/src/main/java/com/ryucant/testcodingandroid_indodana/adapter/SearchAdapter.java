package com.ryucant.testcodingandroid_indodana.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ryucant.testcodingandroid_indodana.R;
import com.ryucant.testcodingandroid_indodana.model.SearchUsersData;
import com.ryucant.testcodingandroid_indodana.view.activity.DetailUserActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<SearchUsersData> searchUsersData= new ArrayList<>();
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setSearchUserList(ArrayList<SearchUsersData> items) {
        searchUsersData.clear();
        searchUsersData.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        SearchUsersData row = searchUsersData.get(position);

        Glide.with(holder.itemView.getContext())
                .load(row.getAvatarUrl())
                .into(holder.img_user);

        holder.tv_usrename.setText(row.getLogin());
        holder.tv_url.setText(row.getHtmlUrl());
        holder.cv_list_user.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailUserActivity.class);
            intent.putExtra("username", row.getLogin());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return searchUsersData.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        CardView cv_list_user;
        TextView tv_usrename, tv_url;
        ImageView img_user;

        public SearchViewHolder(View itemView) {
            super(itemView);
            cv_list_user = itemView.findViewById(R.id.cv_list_user);
            tv_usrename = itemView.findViewById(R.id.tv_username);
            tv_url = itemView.findViewById(R.id.tv_url);
            img_user = itemView.findViewById(R.id.img_user);
        }
    }

}
