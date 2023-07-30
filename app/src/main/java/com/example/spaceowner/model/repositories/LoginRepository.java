package com.example.spaceowner.model.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.spaceowner.model.RetrofitAPI;
import com.example.spaceowner.model.RetrofitClient;
import com.example.spaceowner.model.data.LoginRequest;
import com.example.spaceowner.model.data.LoginResponse;
import com.example.spaceowner.view.auth.LoggedInUser;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private static final String TAG = "LOGIN_REPOSITORY";
    private static LoginRepository loginRepository;
    private LoginRepository(){}
    public static LoginRepository getInstance(){
        if(loginRepository == null) loginRepository = new LoginRepository();
        return loginRepository;
    }
    public void login(String email, String password, MutableLiveData<LoggedInUser> result){
        try{
//            email = "user1@test.com";
//            password = "123456";
            RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);

            LoginRequest request = new LoginRequest(email, password);
            Call<LoginResponse> call = api.login(request);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG, "on successful response: " + response.body().toString());
                    }else{
                        Log.d(TAG, "on failed response: " + response.body().toString());
                    }
                    result.postValue(new LoggedInUser(response.body()));
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d(TAG, "on failure: " + t.getMessage());
                    result.postValue(new LoggedInUser());
                }
            });
        }catch (Exception e){

        }
    }
}
