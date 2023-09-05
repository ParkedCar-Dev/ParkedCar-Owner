package com.example.spaceowner.model.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking implements Serializable {
    @SerializedName("booking_id")
    private int bookingId;

    @SerializedName("space_id")
    private int spaceId;

    @SerializedName("driver_id")
    private int driverId;

    @SerializedName("from_time")
    private long fromTime;

    @SerializedName("to_time")
    private long toTime;

    private String status;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("total_price")
    private int totalPrice;

    @SerializedName("payment_id")
    private String paymentId;

    @SerializedName("payment_status")
    private String paymentStatus;

    @SerializedName("payment_medium")
    private String paymentMedium;

    @SerializedName("medium_transaction_id")
    private String mediumTransactionId;

    @SerializedName("driver_name")
    private String driverName;

    public void setDriverRating(double driverRating) {
        this.driverRating = driverRating;
    }

    @SerializedName("driver_rating")
    private double driverRating;

    // Constructors
    public Booking() {
        this.bookingId = 0;
        this.spaceId = 0;
        this.driverId = 0;
        this.fromTime = 1692551064093l;
        this.toTime = 1692554711212l;
        this.status = "requested";
        this.createdAt = "1970-01-01T00:00:00.000Z";
        this.updatedAt = "1970-01-01T00:00:00.000Z";
        this.totalPrice = 0;
        this.paymentId = "0";
        this.paymentStatus = "null";
        this.paymentMedium = "null";
        this.mediumTransactionId = "0";
        this.driverName = "John Doe";
    }

    public Booking(int bookingId, int spaceId, int driverId, long fromTime, long toTime,
                   String status, String createdAt, String updatedAt, int totalPrice,
                   String paymentId, String paymentStatus, String paymentMedium,
                   String mediumTransactionId, String driverName, double driverRating) {
        this.bookingId = bookingId;
        this.spaceId = spaceId;
        this.driverId = driverId;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalPrice = totalPrice;
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.paymentMedium = paymentMedium;
        this.mediumTransactionId = mediumTransactionId;
        this.driverName = driverName;
        this.driverRating = driverRating;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    String unixToString(long time){
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public String getFromTime() {
        return unixToString(fromTime);
    }

    public void setFromTime(long fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return unixToString(toTime);
    }

    public void setToTime(long toTime) {
        this.toTime = toTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTotalPrice() {
        return Integer.toString(totalPrice);
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMedium() {
        return paymentMedium;
    }

    public void setPaymentMedium(String paymentMedium) {
        this.paymentMedium = paymentMedium;
    }

    public String getMediumTransactionId() {
        return mediumTransactionId;
    }

    public void setMediumTransactionId(String mediumTransactionId) {
        this.mediumTransactionId = mediumTransactionId;
    }

    public String getDriverName() {
        if(driverName == null) return "Place Holder";
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    // toString method
    @NonNull
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", spaceId=" + spaceId +
                ", driverId=" + driverId +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", totalPrice=" + totalPrice +
                ", paymentId='" + paymentId + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentMedium='" + paymentMedium + '\'' +
                ", mediumTransactionId='" + mediumTransactionId + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }

    public String getDriverRating() {
        return Double.toString(driverRating);
    }
}
