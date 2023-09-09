package com.example.spaceowner.model;

import androidx.annotation.NonNull;

import com.example.spaceowner.utils.TokenManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://parked-car-owner-dev-api.onrender.com/";

    public static Retrofit getInstance(){
        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + TokenManager.getInstance().getToken())
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
