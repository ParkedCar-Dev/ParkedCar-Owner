package com.example.spaceowner.model.data;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Random;

public class Space {
    @SerializedName("space_id")
    private int locationId;
    @SerializedName("space_name")
    private String locationName;
    @SerializedName("address")
    private String locationAddress;
    private String owner;
    @SerializedName("latitude")
    private double latitude;

    public String getSecurityMeasured() {
        return securityMeasured;
    }

    public void setSecurityMeasured(String securityMeasured) {
        this.securityMeasured = securityMeasured;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public static DiffUtil.ItemCallback<Space> getDiffCallback() {
        return DIFF_CALLBACK;
    }

    public static void setDiffCallback(DiffUtil.ItemCallback<Space> diffCallback) {
        DIFF_CALLBACK = diffCallback;
    }

    @SerializedName("longitude")
    private double longitude;
    @SerializedName("base_fare")
    private double baseFare;
    @SerializedName("length")
    private double length;
    @SerializedName("width")
    private double width;
    @SerializedName("height")
    private double height;
    private String status;
    @SerializedName("security_measured")
    private String securityMeasured;
    private String[] security;
    @SerializedName("auto_approve")
    private boolean autoApproval;
    private String[] images;
    private double rating;

    @SerializedName("availability_mask")
    private String availabilityMask;
    @SerializedName("time_slots")
    private boolean[] timeSlots;

    Random random = new Random();
    public Space() {
//        locationId = random.nextInt();
//        locationAddress = "demo address";
//        baseFare = "100";
//        rating = 4.5;
//        owner = "demo owner";
//        locationName = "demo name";
//        latitude = 0.0;
//        longitude = 0.0;
//        length = 0.0;
//        width = 0.0;
//        height = 0.0;
//        status = "demo status";
//        security = new String[]{"demo security"};
//        autoApproval = false;
//        images = new String[]{"demo image"};
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Space(int locationId, String locationName, String locationAddress, String owner, double latitude, double longitude, double baseFare, double length, double width, double height, String status, String[] security, boolean autoApproval, String[] images, double rating, String securityMeasured) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.owner = owner;
        this.latitude = latitude;
        this.longitude = longitude;
        this.baseFare = baseFare;
        this.length = length;
        this.width = width;
        this.height = height;
        this.status = status;
        this.security = security;
        this.autoApproval = autoApproval;
        this.images = images;
        this.rating = rating;
        this.securityMeasured = securityMeasured;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getSecurity() {
        return security;
    }

    public void setSecurity(String[] security) {
        this.security = security;
    }

    public boolean isAutoApproval() {
        return autoApproval;
    }

    public void setAutoApproval(boolean autoApproval) {
        this.autoApproval = autoApproval;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", locationAddress='" + locationAddress + '\'' +
                ", owner='" + owner + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", baseFare='" + baseFare + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", status='" + status + '\'' +
                ", security=" + Arrays.toString(security) +
                ", autoApproval=" + autoApproval +
                ", images=" + Arrays.toString(images) +
                ", rating=" + rating +
                ", securityMeasured='" + securityMeasured + '\'' +
                '}';

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Space space = (Space) obj;
        return space.locationId == this.locationId;
    }

    public static DiffUtil.ItemCallback<Space> DIFF_CALLBACK = new DiffUtil.ItemCallback<Space>() {
        @Override
        public boolean areItemsTheSame(Space oldItem, Space newItem) {
            return oldItem.locationId == newItem.locationId;
        }

        @Override
        public boolean areContentsTheSame(Space oldItem, Space newItem) {
            return oldItem.equals(newItem);
        }
    };

    public void setAvailabilityMask() {
        availabilityMask = "maskmask";
    }

    public void setTimeSlots(){
        timeSlots = new boolean[]{true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false};
    }
}
