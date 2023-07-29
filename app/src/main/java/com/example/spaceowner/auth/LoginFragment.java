package com.example.spaceowner.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spaceowner.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    public LoginFragment() {}
    TextInputLayout email, password;
    Button loginButton, signupButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private void tryToLogin(){
        String email = this.email.getEditText().getText().toString();
        String password = this.password.getEditText().getText().toString();
        Toast.makeText(getContext(), "Email: " + email + " Password: " + password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = getView().findViewById(R.id.login_email);
        password = getView().findViewById(R.id.login_pass);
        loginButton = getView().findViewById(R.id.login_button);
        signupButton = getView().findViewById(R.id.login_to_signup);

        loginButton.setOnClickListener((v) -> {
            tryToLogin();
        });

        signupButton.setOnClickListener((v) -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_loginFragment_to_signupFragment2);
        });
    }
}