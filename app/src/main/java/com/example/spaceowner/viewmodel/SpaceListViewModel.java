package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.repositories.SpaceRepository;

import java.util.List;

public class SpaceListViewModel extends ViewModel {
    private MutableLiveData<List<Space>> activeSpaceList, disabledSpaceList, requestedSpaceList;
    private SpaceRepository spaceRepository;
    public SpaceListViewModel(SpaceRepository spaceRepository){
        this.spaceRepository = spaceRepository;
        activeSpaceList = new MutableLiveData<>();
        disabledSpaceList = new MutableLiveData<>();
        requestedSpaceList = new MutableLiveData<>();
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

    public void fetchDisabledSpaces(){
        spaceRepository.fetchDisabledSpaces(disabledSpaceList);
    }

    public void fetchRequestedSpaces(){
        spaceRepository.fetchRequestedSpaces(requestedSpaceList);
    }
}
