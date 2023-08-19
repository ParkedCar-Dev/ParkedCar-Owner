package com.example.spaceowner.model.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpaceListResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("spaces")
    private List<Space> spaces;

    public SpaceListResponse(String status, String message, List<Space> spaces) {
        this.status = status;
        this.message = message;
        this.spaces = spaces;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder("SpaceListResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", spaces=");
        for(Space space : spaces){
            stringBuffer.append(space.toString());
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
