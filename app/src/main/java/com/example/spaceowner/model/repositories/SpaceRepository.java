package com.example.spaceowner.model.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.spaceowner.model.RetrofitAPI;
import com.example.spaceowner.model.RetrofitClient;
import com.example.spaceowner.model.data.BookingRequest;
import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.data.SpaceBookings;
import com.example.spaceowner.model.data.SpaceListResponse;
import com.example.spaceowner.model.data.SpaceStatusUpdateRequest;
import com.example.spaceowner.model.data.requests.SpaceRequest;

import java.util.ArrayList;
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

    public void fetchGenericSpaces(Call<SpaceListResponse> call, MutableLiveData<List<Space>> spaceList){
        call.enqueue(new Callback<SpaceListResponse>() {
            @Override
            public void onResponse(Call<SpaceListResponse> call, Response<SpaceListResponse> response) {
                if(response.isSuccessful()){
                    Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    if(response.body() != null){
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response.body().getSpaces());
                        spaceList.setValue(response.body().getSpaces());
                    }
                }else{
                    Log.d("SPACE_REPOSITORY", "on failed response: " + response);
                    if(response.code() == 401){
                        spaceList.setValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<SpaceListResponse> call, Throwable t) {
                Log.d("SPACE_REPOSITORY", "on failure: " + t.getMessage());
                if(t.getMessage().contains("timeout")){
                    Log.d("SPACE_REPOSITORY", "on failure - timeout: " + t.getMessage());
                    spaceList.setValue(Space.getTimedOutSpace());
                }
            }
        });
    }

    public void fetchActiveSpaces(MutableLiveData<List<Space>> spaceList){
        Log.d("SPACE_REPOSITORY", "fetchSpaces: active");
        RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);
        Call<SpaceListResponse> call = api.getActiveSpaces();
        fetchGenericSpaces(call, spaceList);
    }

    public void fetchDisabledSpaces(MutableLiveData<List<Space>> disabledSpaceList) {
        Log.d("SPACE_REPOSITORY", "fetchSpaces: disabled");
        RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);
        Call<SpaceListResponse> call = api.getDisabledSpaces();
        fetchGenericSpaces(call, disabledSpaceList);
    }

    public void fetchRequestedSpaces(MutableLiveData<List<Space>> requestedSpaceList) {
        Log.d("SPACE_REPOSITORY", "fetchSpaces: requested");
        RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);
        Call<SpaceListResponse> call = api.getRequestedSpaces();
        fetchGenericSpaces(call, requestedSpaceList);
    }

    public void addNewSpace(Space space, MutableLiveData<GenericResponse> result){
        Log.d("SPACE_REPOSITORY", "addNewSpace: " + space);
        RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);
        Call<GenericResponse> call = api.addNewSpace(space);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    if (response.body() != null) {
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response.body());
                        if(response.body().isSuccess()) {
                            result.setValue(response.body());
                        } else {
                            result.setValue(null);
                        }
                    } else {
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    }
                } else {
                    Log.d("SPACE_REPOSITORY", "on failed response: " + response);
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.d("SPACE_REPOSITORY", "on failure: " + t.getMessage());
            }
        });
    }


    public void updateStatus(int spaceId, String status, MutableLiveData<GenericResponse> result) {
        RetrofitAPI api = RetrofitClient.getInstance().create(RetrofitAPI.class);
        Call<GenericResponse> call = api.updateStatus(new SpaceStatusUpdateRequest(spaceId, status));
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                Log.d("SPACE_REPOSITORY", "onResponse: " + response.body());
                if(response.isSuccessful()) {
                    Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    if (response.body() != null) {
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response.body());
                        result.setValue(response.body());
                    } else {
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    }
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.d("SPACE_REPOSITORY", "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateSpace(Space space, MutableLiveData<Boolean> spaceUpdateResult, MutableLiveData<Space> currentSpace) {
        Call<GenericResponse> call = RetrofitClient.getInstance().create(RetrofitAPI.class).updateSpace(space);
        Log.d("SPACE_REPOSITORY", "updateSpace: " + space);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                Log.d("SPACE_REPOSITORY", "onResponse: " + response.body());
                if(response.isSuccessful()) {
                    Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    if (response.body() != null) {
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response.body());
                        spaceUpdateResult.setValue(response.body().isSuccess());
                        if(response.body().isSuccess()){
                            currentSpace.setValue(space);
                        }
                    } else {
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    }
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.d("SPACE_REPOSITORY", "onFailure: " + t.getMessage());
            }
        });
    }

//    public void fetchSpaceRequests(MutableLiveData<List<SpaceRequest>> spaceRequests, int spaceId, String status) {
//        RetrofitClient.getInstance().create(RetrofitAPI.class).getSpaceBookingRequests(new BookingRequest(spaceId, status)).enqueue(new Callback<List<SpaceRequest>>() {
//            @Override
//            public void onResponse(Call<List<SpaceRequest>> call, Response<List<SpaceRequest>> response) {
//                if(response.isSuccessful()){
//                    Log.d("SPACE_REPOSITORY", "on successful response: " + response);
//                    if(response.body() != null){
//                        Log.d("SPACE_REPOSITORY", "on successful response: " + response.body());
//                        spaceRequests.setValue(response.body());
//                    }
//                }else{
//                    Log.d("SPACE_REPOSITORY", "on failed response: " + response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<SpaceRequest>> call, Throwable t) {
//                Log.d("SPACE_REPOSITORY", "on failure: " + t.getMessage());
//            }
//        });
//    }
    public void fetchSpaceRequests(MutableLiveData<SpaceBookings> spaceBookings, int spaceId, String status){
        Log.d("SPACE_REPOSITORY", "fetchSpaceRequests: " + spaceId + " " + status);
        RetrofitClient.getInstance().create(RetrofitAPI.class).getSpaceBookings(new BookingRequest(spaceId, status)).enqueue(new Callback<SpaceBookings>() {
            @Override
            public void onResponse(Call<SpaceBookings> call, Response<SpaceBookings> response) {
                if(response.isSuccessful()){
                    Log.d("SPACE_REPOSITORY", "on successful response: " + response);
                    Log.d("SPACE_REPOSITORY", "on successful response: " + response.body());
                    if(response.body() != null){
                        Log.d("SPACE_REPOSITORY", "on successful response: " + response.body());
                        spaceBookings.setValue(response.body());
                    }
                }else{
                    Log.d("SPACE_REPOSITORY", "on failed response: " + response);
                }
            }

            @Override
            public void onFailure(Call<SpaceBookings> call, Throwable t) {
                Log.d("SPACE_REPOSITORY", "on failure: " + t.getMessage());
            }
        });
    }
}
