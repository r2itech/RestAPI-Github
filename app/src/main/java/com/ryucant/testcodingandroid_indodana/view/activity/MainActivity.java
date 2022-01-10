package com.ryucant.testcodingandroid_indodana.view.activity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ryucant.testcodingandroid_indodana.R;
import com.ryucant.testcodingandroid_indodana.adapter.SearchAdapter;
import com.ryucant.testcodingandroid_indodana.helper.BaseActivity;
import com.ryucant.testcodingandroid_indodana.viewmodel.UserViewModel;

public class MainActivity extends BaseActivity {

    SearchAdapter searchAdapter;
    UserViewModel userViewModel;
    RecyclerView rv_list_user;
    EditText et_search;
    ImageView img_x;
    LinearLayout icon_no_data;
    TextView tv_no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViewById();
        SetListener();
    }

    private void FindViewById()
    {
        rv_list_user = findViewById(R.id.rv_list_user);
        et_search = findViewById(R.id.et_search);
        img_x = findViewById(R.id.img_x);
        icon_no_data = findViewById(R.id.icon_no_data);
        tv_no_data = findViewById(R.id.tv_no_data);
    }

    private void SetListener()
    {
        img_x.setOnClickListener(v -> {
            et_search.getText().clear();
            img_x.setVisibility(View.GONE);
            tv_no_data.setTextColor(getResources().getColor(R.color.textBlack));
            tv_no_data.setText(getResources().getString(R.string.no_data));
            icon_no_data.setVisibility(View.VISIBLE);
            rv_list_user.setVisibility(View.GONE);
        });

        et_search.setOnEditorActionListener((v, actionId, event) -> {
            String username = et_search.getText().toString();
            if(username.isEmpty()){
                et_search.setError("Username cannot be empty!");
                return false;
            }else{
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    ShowLoading();
                    userViewModel.setSearchUser(username);
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    img_x.setVisibility(View.VISIBLE);
                    icon_no_data.setVisibility(View.GONE);
                    rv_list_user.setVisibility(View.VISIBLE);
                    return true;
                }
            }
            return false;
        });

        searchAdapter = new SearchAdapter(this);
        rv_list_user.setLayoutManager(new LinearLayoutManager(this));
        rv_list_user.setAdapter(searchAdapter);
        rv_list_user.setHasFixedSize(true);

        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        userViewModel.getResultListUser().observe(this, searchUsersData -> {
            HideLoading();
            if(searchUsersData.size() != 0){
                searchAdapter.setSearchUserList(searchUsersData);
            }else{
                rv_list_user.setVisibility(View.GONE);
                icon_no_data.setVisibility(View.VISIBLE);
                tv_no_data.setTextColor(getResources().getColor(R.color.red));
                tv_no_data.setText(getResources().getString(R.string.no_data_user));
            }
        });
    }



    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit!", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);
        return;
    }

}
