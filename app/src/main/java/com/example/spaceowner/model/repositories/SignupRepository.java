package com.example.spaceowner.model.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.spaceowner.model.RetrofitAPI;
import com.example.spaceowner.model.RetrofitClient;
import com.example.spaceowner.model.data.auth.SignupRequest;
import com.example.spaceowner.model.data.auth.SignupResponse;
import com.example.spaceowner.view.auth.SignupResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupRepository {

    private static final String TAG = "SIGNUP_REPOSITORY";
    private static SignupRepository signupRepository;
    private SignupRepository(){}
    public static SignupRepository getInstance(){
        if(signupRepository == null) signupRepository = new SignupRepository();
        return signupRepository;
    }
    public void signup(String name, String email, String phone, String password, MutableLiveData<SignupResult> result){
        try{
            RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);

            SignupRequest request = new SignupRequest(name, email, phone, password);
            Call<SignupResponse> call = api.signup(request);

            call.enqueue(new Callback<SignupResponse>() {
                @Override
                public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG, "on successful response: " + response.body().toString());
                        result.postValue(new SignupResult(response.body()));
                    }else{
                        Log.d(TAG, "on failed response: " + response.body().toString());
                        result.postValue(new SignupResult(response.body()));
                    }
                }

                @Override
                public void onFailure(Call<SignupResponse> call, Throwable t) {
                    Log.d(TAG, "on failure: " + t.getMessage());
                    result.postValue(new SignupResult(t));
                }
            });
        }catch (Exception e){

        }
    }
}
