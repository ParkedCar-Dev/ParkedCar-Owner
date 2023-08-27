package com.example.spaceowner.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.Stack;

public class BookingRequest {
    @SerializedName("space_id")
    private int spaceId;
    @SerializedName("status")
    private String status;

    public BookingRequest(int spaceId, String status) {
        this.spaceId = spaceId;
        this.status = status;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
