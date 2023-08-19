package com.example.spaceowner.model.data.requests;

import com.google.gson.annotations.SerializedName;

public class SpaceRequest {
    @SerializedName("driver_name")
    private String driverName;
    @SerializedName("driver_rating")
    private String driverRating;
    @SerializedName("date")
    private String date;
    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("fare")
    private String fare;

    public SpaceRequest(String driverName, String driverRating, String date, String from, String to, String fare) {
        this.driverName = driverName;
        this.driverRating = driverRating;
        this.date = date;
        this.from = from;
        this.to = to;
        this.fare = fare;
    }

    public SpaceRequest() {
        this.driverName = "John Doe";
        this.driverRating = "4.5";
        this.date = "12/12/2020";
        this.from = "12:00";
        this.to = "13:00";
        this.fare = "1000";
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(String driverRating) {
        this.driverRating = driverRating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getTimeFrom() {
        return from;
    }
    public String getTimeTo() {
        return to;
    }
}
