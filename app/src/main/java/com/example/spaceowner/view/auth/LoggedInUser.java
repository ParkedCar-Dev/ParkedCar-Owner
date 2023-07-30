package com.example.spaceowner.view.auth;

import com.example.spaceowner.model.data.LoginResponse;

public class LoggedInUser {
    private String displayName;
    private String email;
    private String error_message;
    public LoggedInUser() {}
    public String getDisplayName() {
        return displayName;
    }
    public String getError(){
        return error_message;
    }
    public LoggedInUser(LoginResponse response){
        setResponse(response);
    }
    public void setResponse(LoginResponse response) {
        if (response.getStatus().equals("success")) {
            this.displayName = "User";
            this.error_message = null;
        } else {
            this.displayName = null;
            this.error_message = response.getMessage();
        }
    }
}
