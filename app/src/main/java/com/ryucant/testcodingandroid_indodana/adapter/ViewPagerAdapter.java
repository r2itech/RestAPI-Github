package com.ryucant.testcodingandroid_indodana.adapter;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ryucant.testcodingandroid_indodana.model.SearchUsersData;
import com.ryucant.testcodingandroid_indodana.view.fragment.FollowerFragment;
import com.ryucant.testcodingandroid_indodana.view.fragment.FollowingFragment;

public class ViewPagerAdapter  extends FragmentStatePagerAdapter {

    String username;

    public ViewPagerAdapter(FragmentManager fragmentManager, String username) {
        super(fragmentManager);
        this.username = username;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FollowerFragment();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FollowingFragment();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Follower";
                break;
            case 1:
                title = "Following";
                break;
        }
        return title;
    }

}
