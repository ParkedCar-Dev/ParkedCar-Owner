package com.example.spaceowner.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.spaceowner.model.repositories.LoginRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(LoginRepository.getInstance());
        }else{
            throw new IllegalArgumentException("Unknown ViewModel Class");
        }
    }
}
