package com.example.spaceowner.model.data.booking;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("booking_id")
    private int bookingId;

    @SerializedName("space_id")
    private int spaceId;

    @SerializedName("address")
    private String locationAddress;

    @SerializedName("city")
    private String city;

    @SerializedName("driver_id")
    private int driverId;

    @SerializedName("from_time")
    private long fromTime;

    @SerializedName("to_time")
    private long toTime;

    @SerializedName("booking_status")
    private String bookingStatus;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("total_fare")
    private int totalPrice;

    @SerializedName("base_fare")
    private int baseFare;

    @SerializedName("time_fare")
    private int timeFare;

    @SerializedName("payment_date")
    private long paymentDate;

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
// populate all attributes with dummy data
        this.bookingId = 1;
        this.spaceId = 1;
        this.locationAddress = "Dummy Address";
        this.city = "Dummy City";
        this.baseFare = 100;
        this.timeFare = 10;
        this.paymentDate = 123456789;
        this.driverId = 1;
        this.fromTime = 123456789;
        this.toTime = 123456789;
        this.bookingStatus = "Dummy Booking Status";
        this.createdAt = "Dummy Created At";
        this.updatedAt = "Dummy Updated At";
        this.totalPrice = 100;
        this.paymentId = "Dummy Payment Id";
        this.paymentStatus = "Dummy Payment Status";
        this.paymentMedium = "Dummy Payment Medium";
        this.mediumTransactionId = "Dummy Medium Transaction Id";
        this.driverName = "Dummy Driver Name";
        this.driverRating = 4.5;
        this.status = "success";
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
        this.bookingStatus = status;
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
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yy hh:mm a");
        return sdf.format(date);
    }

    public String getPaymentTime(){
        return unixToString(paymentDate);
    }

    public String getFromTime() {
        return unixToString(fromTime);
    }

    public String getToTime() {
        return unixToString(toTime);
    }

    public String getTotalPrice() {
        return Integer.toString(totalPrice);
    }

    public String getDriverName() {
        if(driverName == null) return "Place Holder";
        return driverName;
    }

    @NonNull
    @Override
    public String toString() {
        if(status == null || status.equals("failed")){
            return "Booking{" +
                    "status='" + status + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
        return "Booking{" +
                   "bookingId=" + bookingId +
                    ", spaceId=" + spaceId +
                    ", locationAddress='" + locationAddress + '\'' +
                    ", city='" + city + '\'' +
                    ", baseFare=" + baseFare +
                    ", timeFare=" + timeFare +
                    ", paymentDate=" + paymentDate +
                    ", driverId=" + driverId +
                    ", fromTime=" + fromTime +
                    ", toTime=" + toTime +
                    ", status='" + bookingStatus + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", totalPrice=" + totalPrice +
                    ", paymentId='" + paymentId + '\'' +
                    ", paymentStatus='" + paymentStatus + '\'' +
                    ", paymentMedium='" + paymentMedium + '\'' +
                    ", mediumTransactionId='" + mediumTransactionId + '\'' +
                    ", driverName='" + driverName + '\'' +
                    ", driverRating=" + driverRating +
                    '}';
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

    public String getDriverRating() {
        return Double.toString(driverRating);
    }

    public String getAddress() {
        return locationAddress;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getCity() {
        return city;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getBaseFare() {
        return baseFare;
    }

    public int getTimeFare() {
        return timeFare;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentMedium() {
        return paymentMedium;
    }

    public String getMediumTransactionId() {
        return mediumTransactionId;
    }
}
