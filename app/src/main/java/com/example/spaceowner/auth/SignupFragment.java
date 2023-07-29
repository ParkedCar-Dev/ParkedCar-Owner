package com.example.spaceowner.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.spaceowner.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignupFragment extends Fragment {

    private static final String TAG = "SignupFragment";
    private TextInputLayout email, password, confirmPassword, name, phone;
    private Button signupButton, gotoLoginButton;
    public SignupFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = getView().findViewById(R.id.signup_email);
        password = getView().findViewById(R.id.signup_pass);
        confirmPassword = getView().findViewById(R.id.signup_confirm_pass);
        name = getView().findViewById(R.id.signup_name);
        phone = getView().findViewById(R.id.signup_phone);
        signupButton = getView().findViewById(R.id.signup_button);
        gotoLoginButton = getView().findViewById(R.id.signup_to_login);

        signupButton.setOnClickListener((v) -> {
            tryToSignup();
        });

        gotoLoginButton.setOnClickListener((v) -> { getActivity().onBackPressed(); });

    }

    private void tryToSignup() {
        Toast.makeText(getContext(), "Signup", Toast.LENGTH_SHORT).show();
    }
}