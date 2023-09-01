package com.example.spaceowner.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.repositories.SpaceRepository;

import java.util.List;

public class SpaceListViewModel extends ViewModel {
    private MutableLiveData<List<Space>> activeSpaceList, disabledSpaceList, requestedSpaceList;
    private SpaceRepository spaceRepository;
    private MutableLiveData<GenericResponse> updateStatusResponse;
    public SpaceListViewModel(SpaceRepository spaceRepository){
        this.spaceRepository = spaceRepository;
        activeSpaceList = new MutableLiveData<>();
        disabledSpaceList = new MutableLiveData<>();
        requestedSpaceList = new MutableLiveData<>();
        updateStatusResponse = new MutableLiveData<>();
    }

    public MutableLiveData<List<Space>> getActiveSpaces(){
        return activeSpaceList;
    }

    public void setActiveSpaceList(List<Space> activeSpaceList){
        this.activeSpaceList.setValue(activeSpaceList);
    }

    public MutableLiveData<List<Space>> getDisabledSpaceList() {
        return disabledSpaceList;
    }

    public void setDisabledSpaceList(List<Space> disabledSpaceList) {
        this.disabledSpaceList.setValue(disabledSpaceList);
    }

    public MutableLiveData<List<Space>> getRequestedSpaceList() {
        return requestedSpaceList;
    }

    public void setRequestedSpaceList(List<Space> requestedSpaceList) {
        this.requestedSpaceList.setValue(requestedSpaceList);
    }

    public void fetchActiveSpaces(){
        spaceRepository.fetchActiveSpaces(activeSpaceList);
    }

    public MutableLiveData<GenericResponse> getUpdateStatusResponse() {
        return updateStatusResponse;
    }

    public void setUpdateStatusResponse(MutableLiveData<GenericResponse> updateStatusResponse) {
        this.updateStatusResponse = updateStatusResponse;
    }

    public void fetchDisabledSpaces(){
        spaceRepository.fetchDisabledSpaces(disabledSpaceList);
    }

    public void fetchRequestedSpaces(){
        spaceRepository.fetchRequestedSpaces(requestedSpaceList);
    }

    public void updateStatus(int spaceId, String status){
        Log.d("SPACE_LIST_VIEW_MODEL", "updateStatus: " + spaceId + " " + status);
        spaceRepository.updateStatus(spaceId, status, updateStatusResponse);
    }

    public void refreshAllData() {
        fetchActiveSpaces();
        fetchDisabledSpaces();
        fetchRequestedSpaces();
        updateStatusResponse.setValue(null);
    }

    public void deleteRequestedSpace(int locationId) {
//        TODO: Delete Requested Space
    }

    public void retryFetchActiveSpaces() {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                fetchActiveSpaces();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
