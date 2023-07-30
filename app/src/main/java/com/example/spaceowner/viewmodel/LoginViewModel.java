package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.repositories.LoginRepository;
import com.example.spaceowner.view.auth.LoggedInUser;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoggedInUser> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository repository) {
        this.loginRepository = repository;
    }

    public void login(String email, String password){
        loginRepository.login(email, password, loginResult);
    }

    public MutableLiveData<LoggedInUser> getLoginResult() {
        return loginResult;
    }
}
