package com.example.spaceowner.model.data;

import com.google.gson.annotations.SerializedName;

public class SpaceStatusUpdateRequest {
    @SerializedName("spaceId")
    int spaceId;
    @SerializedName("status")
    String status;

    public SpaceStatusUpdateRequest(int spaceId, String status) {
        this.spaceId = spaceId;
        this.status = status;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public String getStatus() {
        return status;
    }
}
