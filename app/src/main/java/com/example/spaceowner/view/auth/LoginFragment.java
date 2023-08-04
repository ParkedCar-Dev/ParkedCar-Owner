package com.example.spaceowner.view.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spaceowner.R;
import com.example.spaceowner.viewmodel.LoginViewModel;
import com.example.spaceowner.viewmodel.LoginViewModelFactory;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    TextInputLayout email, password;
    Button loginButton, signupButton;
    LoginViewModel viewModel;
    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = getView().findViewById(R.id.login_email);
        password = getView().findViewById(R.id.login_pass);
        loginButton = getView().findViewById(R.id.login_button);
        signupButton = getView().findViewById(R.id.login_to_signup);
        viewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);
        loginButton.setOnClickListener((v) -> {
            viewModel.login(email.getEditText().getText().toString(), password.getEditText().getText().toString());
        });

        viewModel.getLoginResult().observe(getViewLifecycleOwner(), (result) -> {
            if(result == null) return;
            if(result.getError() != null){
                Toast.makeText(getContext(), result.getError(), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            }
        });

        signupButton.setOnClickListener((v) -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_loginFragment_to_signupFragment2);
        });
    }
}