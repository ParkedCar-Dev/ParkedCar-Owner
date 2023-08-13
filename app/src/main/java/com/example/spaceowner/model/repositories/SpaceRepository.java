package com.example.spaceowner.model.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.spaceowner.model.RetrofitAPI;
import com.example.spaceowner.model.RetrofitClient;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.data.SpaceListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpaceRepository {
    private static SpaceRepository spaceRepository;
    private SpaceRepository(){}
    public static SpaceRepository getInstance(){
        if(spaceRepository == null) spaceRepository = new SpaceRepository();
        return spaceRepository;
    }
    public void fetchSpaces(MutableLiveData<List<Space>> spaceList){
        Log.d("SPACE_REPOSITORY", "fetchSpaces: ");
        RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);
        Call<SpaceListResponse> call = api.getSpaces();
        call.enqueue(new Callback<SpaceListResponse>() {
            @Override
            public void onResponse(Call<SpaceListResponse> call, Response<SpaceListResponse> response) {
                if(response.isSuccessful()){
                    Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    if(response.body() != null){
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response.body().getSpaces());
                        spaceList.setValue(response.body().getSpaces());
                    }
//                    List<Space> spaces = response.body().getSpaces();
//                    for(Space space : spaces){
//                        System.out.println(space.toString());
//                    }

                }else{
                    Log.d("SPACE_REPOSITORY", "on failed response: " + response);
                }
            }

            @Override
            public void onFailure(Call<SpaceListResponse> call, Throwable t) {
                Log.d("SPACE_REPOSITORY", "on failure: " + t.getMessage());
            }
        });
    }

    public void addNewSpace(Space space, MutableLiveData<Space> result){
        RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);
        Call<Space> call = api.addNewSpace(space);
        call.enqueue(new Callback<Space>() {
            @Override
            public void onResponse(Call<Space> call, Response<Space> response) {
                if(response.isSuccessful()){
                    Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    if(response.body() != null){
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response.body());
//                        result.setValue(response.body());
                        result.setValue(space);
                    }
                }else{
                    Log.d("SPACE_REPOSITORY", "on failed response: " + response);
                    result.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Space> call, Throwable t) {
                Log.d("SPACE_REPOSITORY", "on failure: " + t.getMessage());
            }
        });
    }
}
