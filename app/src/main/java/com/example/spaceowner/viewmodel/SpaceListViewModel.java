package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.repositories.SpaceRepository;

import java.util.List;

public class SpaceListViewModel extends ViewModel {
    private MutableLiveData<List<Space>> spaceList;
    private SpaceRepository spaceRepository;
    public SpaceListViewModel(SpaceRepository spaceRepository){
        this.spaceRepository = spaceRepository;
        spaceList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Space>> getSpaces(){
        return spaceList;
    }

    public void setSpaceList(List<Space> spaceList){
        this.spaceList.setValue(spaceList);
    }

    public void fetchSpaces(){
        spaceRepository.fetchSpaces(spaceList);
    }
}
