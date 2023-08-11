package com.example.spaceowner.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.spaceowner.model.repositories.LoginRepository;
import com.example.spaceowner.model.repositories.SignupRepository;
import com.example.spaceowner.model.repositories.SpaceRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(LoginRepository.getInstance());
        }else if(modelClass.isAssignableFrom(SignupViewModel.class)){
            return (T) new SignupViewModel(SignupRepository.getInstance());
        }else if(modelClass.isAssignableFrom(SpaceListViewModel.class)){
            return (T) new SpaceListViewModel(SpaceRepository.getInstance());
        }else{
            throw new IllegalArgumentException("Unknown ViewModel Class");
        }
    }
}
