package com.ryucant.testcodingandroid_indodana.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ryucant.testcodingandroid_indodana.model.DetailUser;
import com.ryucant.testcodingandroid_indodana.model.Follow;
import com.ryucant.testcodingandroid_indodana.response.SearchUsers;
import com.ryucant.testcodingandroid_indodana.model.SearchUsersData;
import com.ryucant.testcodingandroid_indodana.net.NetClient;
import com.ryucant.testcodingandroid_indodana.net.NetInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    public static String api_key = "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";

    private MutableLiveData<ArrayList<SearchUsersData>> modelSearchUsersMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<DetailUser> modelDetailUserMutableLiveData = new MutableLiveData<>();
    private  MutableLiveData<ArrayList<Follow>> modelFollowerMutableLiveData = new MutableLiveData<>();
    private  MutableLiveData<ArrayList<Follow>> modelFollowingMutableLiveData = new MutableLiveData<>();

    //Search Users
    public void setSearchUser(String query)
    {
        NetInterface netInterface = NetClient.getRetrofit().create(NetInterface.class);

        Call<SearchUsers> call = netInterface.searchUser(api_key, query);
        call.enqueue(new Callback<SearchUsers>() {
            @Override
            public void onResponse(Call<SearchUsers> call, Response<SearchUsers> response) {
                if(response.isSuccessful()){
                    ArrayList<SearchUsersData> data = new ArrayList<>(response.body().getSearchUsersData());
                    modelSearchUsersMutableLiveData.setValue(data);
                }
            }

            @Override
            public void onFailure(Call<SearchUsers> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<SearchUsersData>> getResultListUser(){
        return modelSearchUsersMutableLiveData;
    }

    //User Detail
    public void setUserDetail(String username){
        NetInterface netInterface = NetClient.getRetrofit().create(NetInterface.class);
        Call<DetailUser> call = netInterface.userDetail(api_key, username);
        call.enqueue(new Callback<DetailUser>() {
            @Override
            public void onResponse(Call<DetailUser> call, Response<DetailUser> response) {
                if(response.isSuccessful()){
                    modelDetailUserMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DetailUser> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<DetailUser> getUserDetail(){
        return modelDetailUserMutableLiveData;
    }

    //User Follower
    public void setFollowerUser(String username){
        NetInterface netInterface = NetClient.getRetrofit().create(NetInterface.class);

        Call<ArrayList<Follow>> call = netInterface.followerUser(api_key, username);
        call.enqueue(new Callback<ArrayList<Follow>>() {
            @Override
            public void onResponse(Call<ArrayList<Follow>> call, Response<ArrayList<Follow>> response) {
                if(response.isSuccessful()){
                    modelFollowerMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Follow>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<Follow>> getFollowerUser(){ return modelFollowerMutableLiveData; }


    //User Following
    public void setFollowingUser(String username){
        NetInterface netInterface = NetClient.getRetrofit().create(NetInterface.class);

        Call<ArrayList<Follow>> call = netInterface.followingUser(api_key, username);
        call.enqueue(new Callback<ArrayList<Follow>>() {
            @Override
            public void onResponse(Call<ArrayList<Follow>> call, Response<ArrayList<Follow>> response) {
                if(response.isSuccessful()){
                    modelFollowingMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Follow>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<Follow>> getFollowingUser(){ return modelFollowingMutableLiveData; }

}
