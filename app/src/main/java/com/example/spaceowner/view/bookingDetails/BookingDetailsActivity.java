package com.example.spaceowner.view.bookingDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.spaceowner.R;

public class BookingDetailsActivity extends AppCompatActivity {
    TextView bookingId, fromTime, toTime, bookingStatus, address, city, driverName, rateNow, baseFare, timeFare, totalFare, paymentMethod, paymentStatus, paymentId, paymentDate;
    CardView requestAgain;
    Button addAdditionalReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        bookingId = findViewById(R.id.booking_id);
        fromTime = findViewById(R.id.from_date);
        toTime = findViewById(R.id.to_date);
        bookingStatus = findViewById(R.id.booking_status);
        address = findViewById(R.id.location_address);
        city = findViewById(R.id.city);
        driverName = findViewById(R.id.driver_name);
        rateNow = findViewById(R.id.rate_now_tv);
        baseFare = findViewById(R.id.booking_base_fare);
        timeFare = findViewById(R.id.booking_time_fare);
        totalFare = findViewById(R.id.total_fare);
        paymentMethod = findViewById(R.id.payment_method);
        paymentStatus = findViewById(R.id.payment_status);
        paymentId = findViewById(R.id.payment_id);
        paymentDate = findViewById(R.id.payment_date);
    }
}