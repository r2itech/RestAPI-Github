package com.ryucant.testcodingandroid_indodana.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ryucant.testcodingandroid_indodana.R;
import com.ryucant.testcodingandroid_indodana.adapter.FollowAdapter;
import com.ryucant.testcodingandroid_indodana.viewmodel.UserViewModel;

public class FollowingFragment extends Fragment {

    UserViewModel userViewModel;
    FollowAdapter followAdapter;
    RecyclerView rv_list_follower;
    LinearLayout icon_no_data;
    TextView tv_no_data;
    String username;

    public FollowingFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow, container, false);

        rv_list_follower = view.findViewById(R.id.rv_list_follow);
        icon_no_data = view.findViewById(R.id.icon_no_data);
        tv_no_data = view.findViewById(R.id.tv_no_data);

        SetListener();

        return view;
    }

    private void SetListener()
    {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            username = bundle.getString("username");
        }

        followAdapter = new FollowAdapter(getContext());
        rv_list_follower.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list_follower.setAdapter(followAdapter);
        rv_list_follower.setHasFixedSize(true);

        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        userViewModel.setFollowingUser(username);
        userViewModel.getFollowingUser().observe(getViewLifecycleOwner(), follower -> {
            if (follower.size() != 0) {
                icon_no_data.setVisibility(View.GONE);
                followAdapter.setFollowList(follower);
            } else {
                rv_list_follower.setVisibility(View.GONE);
                icon_no_data.setVisibility(View.VISIBLE);
                tv_no_data.setTextColor(getResources().getColor(R.color.red));
            }
        });
    }

}
