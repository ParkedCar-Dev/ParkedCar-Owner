package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.view.auth.SignupResult;
import com.example.spaceowner.model.repositories.SignupRepository;

public class SignupViewModel extends ViewModel {
    private MutableLiveData<SignupResult> signupResult = new MutableLiveData<>();
    private SignupRepository signupRepository;

    public SignupViewModel(SignupRepository repository) {
        this.signupRepository = repository;
    }

    public void signup(String name, String email, String phone, String password) {
        signupRepository.signup(name, email, phone, password, signupResult);
    }

    public MutableLiveData<SignupResult> getSignupResult() {
        return signupResult;
    }
}
