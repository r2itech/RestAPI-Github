package com.ryucant.testcodingandroid_indodana.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.ryucant.testcodingandroid_indodana.R;
import com.ryucant.testcodingandroid_indodana.adapter.ViewPagerAdapter;
import com.ryucant.testcodingandroid_indodana.helper.BaseActivity;
import com.ryucant.testcodingandroid_indodana.viewmodel.UserViewModel;


public class DetailUserActivity extends BaseActivity {

    UserViewModel userViewModel;
    String username;
    ImageView img_user;
    TextView tv_username, tv_bio, tv_url, tv_follower, tv_following, tv_repo;
    TabLayout tabsLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        FindViewById();
        SetListener();
    }

    private void FindViewById()
    {
        img_user = findViewById(R.id.img_user);
        tv_username = findViewById(R.id.tv_username);
        tv_bio = findViewById(R.id.tv_bio);
        tv_url = findViewById(R.id.tv_url);
        tv_follower = findViewById(R.id.tv_follower);
        tv_following = findViewById(R.id.tv_following);
        tv_repo = findViewById(R.id.tv_repo);
        tabsLayout = findViewById(R.id.tabsLayout);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        collapsingToolbarLayout = findViewById(R.id.ctl);
    }

    private void SetListener()
    {
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        username = getIntent().getStringExtra("username");

        //Set Fragment
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), username));
        viewPager.setOffscreenPageLimit(2);
        tabsLayout.setupWithViewPager(viewPager);

        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        ShowLoading();
        userViewModel.setUserDetail(username);
        userViewModel.getUserDetail().observe(this, detailUser -> {
            HideLoading();
            Glide.with(getApplicationContext())
                    .load(detailUser.getAvatarUrl())
                    .into(img_user);

            collapsingToolbarLayout.setTitle(detailUser.getName());
            tv_username.setText(detailUser.getLogin() + " \u2022 " + detailUser.getLocation());
            tv_bio.setText("("+detailUser.getBio()+")");
            tv_url.setText(detailUser.getHtmlUrl());
            tv_follower.setText(detailUser.getFollowers());
            tv_following.setText(detailUser.getFollowing());
            tv_repo.setText(detailUser.getPublicRepos());

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
