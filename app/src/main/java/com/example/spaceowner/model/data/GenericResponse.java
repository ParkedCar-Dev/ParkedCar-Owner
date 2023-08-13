package com.example.spaceowner.model.data;

import com.google.gson.annotations.SerializedName;

public class GenericResponse {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
}
