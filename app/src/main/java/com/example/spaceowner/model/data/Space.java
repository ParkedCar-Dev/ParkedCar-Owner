package com.example.spaceowner.model.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Space implements Serializable {
    @SerializedName("space_id")
    private int locationId;
    @SerializedName("space_name")
    private String locationName;
    @SerializedName("address")
    private String locationAddress;
    private String owner;
    @SerializedName("latitude")
    private double latitude;
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
    @SerializedName("status")
    private String status;
    @SerializedName("security_measures")
    private String securityMeasures;
    private String[] security;
    @SerializedName("auto_approve")
    private boolean autoApproval;
    private String[] images;
    @SerializedName("rating")
    private double rating;
    @SerializedName("availability_mask")
    private String availabilityMask;
    @SerializedName("time_slots")
    private boolean[] timeSlots;
    @SerializedName("total_books")
    private int totalBooks;
    @SerializedName("city")
    private String city;
    @SerializedName("message")
    private String message;

    public Space() {
        locationId = new Random().nextInt(1000);
        length = width = height = 1;
        latitude = longitude = 1;
        baseFare = 1;
        status = "active";
        security = new String[]{"", "", ""};
        autoApproval = false;
        images = new String[]{};
        rating = 1;
        availabilityMask = "AVAILABILITY_MASK";
        timeSlots = new boolean[]{true, true, true, false, false, false, false};
        totalBooks = 1;
        city = "DHAKA";
        securityMeasures = "";
        message = "";
    }

    public Space(Space currentSpace) {
        this.locationId = currentSpace.locationId;
        this.locationName = currentSpace.locationName;
        this.locationAddress = currentSpace.locationAddress;
        this.owner = currentSpace.owner;
        this.latitude = currentSpace.latitude;
        this.longitude = currentSpace.longitude;
        this.baseFare = currentSpace.baseFare;
        this.length = currentSpace.length;
        this.width = currentSpace.width;
        this.height = currentSpace.height;
        this.status = currentSpace.status;
        this.securityMeasures = currentSpace.securityMeasures;
        this.security = currentSpace.security;
        this.autoApproval = currentSpace.autoApproval;
        this.images = currentSpace.images;
        this.rating = currentSpace.rating;
        this.availabilityMask = currentSpace.availabilityMask;
        this.timeSlots = currentSpace.timeSlots;
        this.totalBooks = currentSpace.totalBooks;
        this.city = currentSpace.city;
        this.message = currentSpace.message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getSecurityMeasures() {
        return securityMeasures;
    }

    public void setSecurityMeasures(String securityMeasures) {
        this.securityMeasures = securityMeasures;
    }

    public String[] getSecurity() {
        return security;
    }

    public void setSecurity(String[] security) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<security.length; i++){
            sb.append(security[i]+"/");
        }
        setSecurityMeasures(sb.toString());
        this.security = security;
    }

    public boolean isAutoApprove() {
        return autoApproval;
    }

    public void setAutoApprove(boolean autoApproval) {
        this.autoApproval = autoApproval;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAvailabilityMask() {
        return availabilityMask;
    }

    public void setAvailabilityMask(String availabilityMask) {
        this.availabilityMask = availabilityMask;
    }

    public boolean[] getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(boolean[] timeSlots) {
        this.timeSlots = timeSlots;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
                ", baseFare=" + baseFare +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", status='" + status + '\'' +
                ", securityMeasures='" + securityMeasures + '\'' +
                ", security=" + Arrays.toString(security) +
                ", autoApproval=" + autoApproval +
                ", images=" + Arrays.toString(images) +
                ", rating=" + rating +
                ", availabilityMask='" + availabilityMask + '\'' +
                ", timeSlots=" + Arrays.toString(timeSlots) +
                ", totalBooks=" + totalBooks +
                ", city='" + city + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isCctv() {
        return securityMeasures.contains("CC") || securityMeasures.contains("cc");
    }
    public boolean isIndoor() {
        return securityMeasures.contains("IN") || securityMeasures.contains("in");
    }
    public boolean isGuard() {
        return securityMeasures.contains("GUARD") || securityMeasures.contains("guard") || securityMeasures.contains("SECU") || securityMeasures.contains("secu");
    }

    public boolean isActivated() {
        return status.equals("active");
    }

    public void setSecurity(boolean cc, boolean guard, boolean indoor) {
        String[] security = new String[]{"", "", ""};
        if(cc) security[0] = "cctv";
        if(guard) security[1] = "guard";
        if(indoor) security[2] = "indoor";
        setSecurity(security);
    }

    public boolean equals(@Nullable Space sp) {
        return this.locationAddress.equals(sp.locationAddress) &&
                this.latitude == sp.latitude &&
                this.longitude == sp.longitude &&
                this.baseFare == sp.baseFare &&
                this.length == sp.length &&
                this.width == sp.width &&
                this.height == sp.height &&
                this.autoApproval == sp.autoApproval &&
                this.securityMeasures.equals(sp.securityMeasures);
    }
}
