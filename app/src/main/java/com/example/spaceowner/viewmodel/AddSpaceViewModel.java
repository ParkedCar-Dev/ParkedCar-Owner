package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.repositories.SpaceRepository;

public class AddSpaceViewModel extends ViewModel {
    MutableLiveData<Space> result = new MutableLiveData<>();

    private SpaceRepository spaceRepository;
    public AddSpaceViewModel(SpaceRepository spaceRepository){
        this.spaceRepository = spaceRepository;
    }


    public void addSpace(String address, double latitude, double longitude, double length, double width, double height, boolean autoApprove, boolean cctv, boolean guard, boolean indoor){
        Space space = new Space();
        space.setLocationAddress(address);
        space.setLatitude(latitude);
        space.setLongitude(longitude);
        space.setLength(length);
        space.setWidth(width);
        space.setHeight(height);
        space.setBaseFare(0);
        space.setStatus("requested");
        space.setAvailabilityMask();
        space.setTimeSlots();

        space.setAutoApproval(autoApprove);
        space.setSecurity(new String[]{cctv ? "cctv" : "", guard ? "guard" : "", indoor ? "indoor" : ""});


        spaceRepository.addNewSpace(space, result);
    }

    public MutableLiveData<Space> getResult() {
        return result;
    }
}
