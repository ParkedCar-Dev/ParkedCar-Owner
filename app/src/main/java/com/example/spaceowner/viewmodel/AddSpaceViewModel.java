package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.repositories.SpaceRepository;

public class AddSpaceViewModel extends ViewModel {
    MutableLiveData<Space> result = new MutableLiveData<>();
    MutableLiveData<GenericResponse> response = new MutableLiveData<>();

    private SpaceRepository spaceRepository;
    public AddSpaceViewModel(SpaceRepository spaceRepository){
        this.spaceRepository = spaceRepository;
        this.response = new MutableLiveData<>();
        response.setValue(new GenericResponse());
    }


    public void addSpace(String address, double latitude, double longitude, double length, double width, double height, boolean autoApprove, boolean cctv, boolean guard, boolean indoor, String city, double baseFare){
        Space space = new Space();
        space.setLocationAddress(address);
        space.setLatitude(latitude);
        space.setLongitude(longitude);
        space.setLength(length);
        space.setWidth(width);
        space.setHeight(height);
        space.setStatus("requested");
        space.setCity(city.toLowerCase());
        space.setBaseFare(baseFare);

        space.setAutoApprove(autoApprove);
        space.setSecurity(new String[]{cctv ? "cctv" : "", guard ? "guard" : "", indoor ? "indoor" : ""});


        spaceRepository.addNewSpace(space, response);
    }

    public MutableLiveData<Space> getResult() {
        return result;
    }
    public MutableLiveData<GenericResponse> getResponse() {
        return response;
    }
}
