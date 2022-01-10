package com.ryucant.testcodingandroid_indodana.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetClient {

    private static final String BASE_URL = "https://api.github.com/";

    public static OkHttpClient okHttpClient;
    public static NetClient instance;
    private static Retrofit retrofit;
    private static Gson gson;

    public static NetClient getInstance(Class<NetInterface> networkServiceClass){
        if(instance == null){
            instance = new NetClient();
        }
        return instance;
    }

    public static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(NetClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetInterface getNetworkService(Context context){
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .connectTimeout(60000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NetInterface netInterface = retrofit.create(NetInterface.class);
        return netInterface;
    }
}
