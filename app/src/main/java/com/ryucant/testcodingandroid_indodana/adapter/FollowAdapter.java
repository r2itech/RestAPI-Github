package com.ryucant.testcodingandroid_indodana.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ryucant.testcodingandroid_indodana.R;
import com.ryucant.testcodingandroid_indodana.model.Follow;
import com.ryucant.testcodingandroid_indodana.view.activity.DetailUserActivity;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.FolowersViewHolder> {

    private ArrayList<Follow> modelFollowArrayList = new ArrayList<>();
    private Context context;

    public FollowAdapter(Context context) {
        this.context = context;
    }

    public void setFollowList(ArrayList<Follow> items) {
        modelFollowArrayList.clear();
        modelFollowArrayList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public FollowAdapter.FolowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new FolowersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowAdapter.FolowersViewHolder holder, int position) {
        Follow row = modelFollowArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(row.getAvatarUrl())
                .into(holder.imageUser);

        holder.tvUsername.setText(row.getLogin());
        holder.tvUrl.setText(row.getHtmlUrl());
        holder.cvListUser.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailUserActivity.class);
            intent.putExtra("username", row.getLogin());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return modelFollowArrayList.size();
    }

    public static class FolowersViewHolder extends RecyclerView.ViewHolder {

        CardView cvListUser;
        TextView tvUsername, tvUrl;
        ImageView imageUser, imageArrow;

        public FolowersViewHolder(View itemView) {
            super(itemView);
            cvListUser = itemView.findViewById(R.id.cv_list_user);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvUrl = itemView.findViewById(R.id.tv_url);
            imageUser = itemView.findViewById(R.id.img_user);
            imageArrow = itemView.findViewById(R.id.iv_detail);

        }
    }

}
