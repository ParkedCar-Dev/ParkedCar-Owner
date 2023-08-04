package com.example.spaceowner.model.data;

import androidx.recyclerview.widget.DiffUtil;

import java.util.Random;

public class Space {
    private int locationId;
    private String locationName;
    private String locationAddress;
    private String owner;
    private double latitude;
    private double longitude;
    private String baseFare;
    private double length, width, height;
    private String status;
    private String[] security;
    private boolean autoApproval;
    private String[] images;
    private double rating;

    Random random = new Random();
    public Space() {
        locationId = random.nextInt();
        locationAddress = "demo address";
        baseFare = "100";
        rating = 4.5;
        owner = "demo owner";
        locationName = "demo name";
        latitude = 0.0;
        longitude = 0.0;
        length = 0.0;
        width = 0.0;
        height = 0.0;
        status = "demo status";
        security = new String[]{"demo security"};
        autoApproval = false;
        images = new String[]{"demo image"};
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Space(int locationId, String locationName, String locationAddress, String owner, double latitude, double longitude, String baseFare, double length, double width, double height, String status, String[] security, boolean autoApproval, String[] images, double rating) {
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

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
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
}
