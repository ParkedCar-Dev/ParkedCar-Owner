package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.data.SpaceBookings;
import com.example.spaceowner.model.data.requests.SpaceRequest;
import com.example.spaceowner.model.repositories.SpaceRepository;

import java.util.ArrayList;
import java.util.List;

public class SpaceViewModel extends ViewModel {
    private SpaceRepository spaceRepository;
    private MutableLiveData<Space> currentSpace;
    private MutableLiveData<Boolean> spaceUpdateResult;
    private MutableLiveData<SpaceBookings> spaceRequests;
    private static SpaceViewModel instance;
    private SpaceViewModel(SpaceRepository spaceRepository){
        this.spaceRepository = spaceRepository;
        currentSpace = new MutableLiveData<>();
        spaceRequests = new MutableLiveData<>();
        spaceUpdateResult = new MutableLiveData<>(false);
    }

    public static SpaceViewModel getInstance(SpaceRepository spaceRepository){
        if(instance == null) instance = new SpaceViewModel(spaceRepository);
        return instance;
    }

    public MutableLiveData<Space> getCurrentSpace(){
        return currentSpace;
    }

    public Space getSpaceDetails(int spaceId){
//  TODO:  spaceRepository.fetchSpaceDetails(space, spaceId);
        return new Space();
    }

    public Space getCurrentSpaceDetails(){
        return currentSpace.getValue();
    }

    public List<SpaceRequest> getSpaceRequests(int spaceId){
//        TODO: spaceRepository.fetchSpaceRequests(spaceRequests, spaceId);
        List<SpaceRequest> requests = new ArrayList<>();
        requests.add(new SpaceRequest());
        requests.add(new SpaceRequest());
        requests.add(new SpaceRequest());
        requests.add(new SpaceRequest());
        return requests;
    }


    public void setSpace(Space activitySpace) {
        this.currentSpace.setValue(activitySpace);
    }

    public void fetchCurrentSpaceDetails() {
//        TODO: spaceRepository.fetchCurrentSpaceDetails(currentSpace);
        currentSpace.setValue(new Space());
    }

    public void updateSpace(Space space) {
        spaceRepository.updateSpace(space, spaceUpdateResult, currentSpace);
    }

    public MutableLiveData<Boolean> getSpaceUpdateResult(){
        return spaceUpdateResult;
    }

    public void updateStatus(int locationId, String status) {
        spaceRepository.updateStatus(locationId, status, new MutableLiveData<>());
    }

    public MutableLiveData<SpaceBookings> getSpaceRequests() {
        return spaceRequests;
    }

    public void fetchSpaceRequests() {
        spaceRepository.fetchSpaceRequests(spaceRequests, currentSpace.getValue().getLocationId(), "requested");
    }
}
