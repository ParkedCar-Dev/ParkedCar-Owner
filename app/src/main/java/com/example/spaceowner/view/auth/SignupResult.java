package com.example.spaceowner.view.auth;

import com.example.spaceowner.model.data.SignupResponse;

public class SignupResult {
    String error_message;

    public SignupResult(SignupResponse response) {
        setResponse(response);
    }

    public SignupResult(Throwable error) {
        this.error_message = error.getMessage();
    }

    public String getError() {
        return error_message;
    }

    public void setResponse(SignupResponse response) {
        if (response.getStatus().equals("success")) {
            this.error_message = null;
        } else {
            this.error_message = response.getMessage();
        }
    }
}
