package com.ryucant.testcodingandroid_indodana.net;

import com.ryucant.testcodingandroid_indodana.model.DetailUser;
import com.ryucant.testcodingandroid_indodana.model.Follow;
import com.ryucant.testcodingandroid_indodana.response.SearchUsers;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetInterface {

    //Search Users
    @GET("search/users")
    Call<SearchUsers> searchUser(
            @Header("Authorization") String authorization,
            @Query("q") String username
    );

    //Detail User
    @GET("users/{username}")
    Call<DetailUser> userDetail(
            @Header("Authorization") String authorization,
            @Path("username") String username
    );

    //Follower
    @GET("users/{username}/followers")
    Call<ArrayList<Follow>> followerUser(
            @Header("Authorization") String authorization,
            @Path("username") String username
    );

    //Following
    @GET("users/{username}/following")
    Call<ArrayList<Follow>> followingUser(
            @Header("Authorization") String authorization,
            @Path("username") String username
    );
}
