package com.example.spaceowner.model.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class GenericResponse {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public GenericResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public GenericResponse(){
        status = "failure";
        message = "Something went wrong";
    }

    @NonNull
    @Override
    public String toString() {
        return "GenericResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return status.equals("success");
    }

    public boolean isSuccessful() {
        return status.equals("success");
    }
}
