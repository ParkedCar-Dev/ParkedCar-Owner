package com.example.spaceowner.view.bookingDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.booking.Booking;
import com.example.spaceowner.viewmodel.BookingViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;

public class BookingDetailsActivity extends AppCompatActivity {
    TextView bookingId, fromTime, toTime, bookingStatus, address, city, driverName, rateNow, baseFare, timeFare, totalFare, paymentMethod, paymentStatus, paymentId, paymentDate, reportIssue;
    CardView requestAgain, paymentCard;
    Button button;

    BookingViewModel bookingViewModel;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        int bookingIdFromDifferentActivity = getIntent().getIntExtra("bookingId", 8);

        bookingViewModel = new ViewModelFactory().create(BookingViewModel.class);

        bookingId = findViewById(R.id.booking_id);
        reportIssue = findViewById(R.id.report_issue_tv);
        fromTime = findViewById(R.id.from_date);
        toTime = findViewById(R.id.to_date);
        bookingStatus = findViewById(R.id.booking_status);
        address = findViewById(R.id.location_address);
        city = findViewById(R.id.city);
        driverName = findViewById(R.id.driver_name);
        baseFare = findViewById(R.id.booking_base_fare);
        timeFare = findViewById(R.id.booking_time_fare);
        totalFare = findViewById(R.id.total_fare);
        paymentMethod = findViewById(R.id.payment_method);
        paymentStatus = findViewById(R.id.payment_status);
        paymentId = findViewById(R.id.payment_id);
        paymentDate = findViewById(R.id.payment_date);

        rateNow = findViewById(R.id.rate_now_tv);
        requestAgain = findViewById(R.id.request_again_card);
        button = findViewById(R.id.btn_add_review);
        paymentCard = findViewById(R.id.payment_card);

        rateNow.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            CustomDialog customDialog = new CustomDialog(bookingViewModel);
            customDialog.show(fm, "Rate Driver");
        });

        bookingViewModel.getBookingDetails(bookingIdFromDifferentActivity);

        bookingViewModel.getCurrentBooking().observe(this, booking -> {
            if(booking != null && booking.getBookingId() > 0){
                bookingId.setText("#" + booking.getBookingId());
                fromTime.setText("From: "+booking.getFromTime());
                toTime.setText("To: "+booking.getToTime());
                bookingStatus.setText(booking.getStatus());
                address.setText(booking.getAddress());
                city.setText(booking.getCity());
                driverName.setText(booking.getDriverName());
                baseFare.setText(String.valueOf(booking.getBaseFare()));
                timeFare.setText(String.valueOf(booking.getTimeFare()));
                totalFare.setText(String.valueOf(booking.getTotalPrice()));
                paymentMethod.setText(booking.getPaymentMedium());
                paymentStatus.setText(booking.getPaymentStatus());
                paymentId.setText(String.valueOf(booking.getPaymentId()));
                paymentDate.setText(booking.getPaymentTime());

                setButton(booking);
            }
        });

        handler.postDelayed(fetchBookingDetails, 2000);
    }

    private void setButton(Booking booking) {
        String status = booking.getStatus();
        if(status.equalsIgnoreCase("requested")){
            requestAgain.setVisibility(View.GONE);
            reportIssue.setVisibility(View.GONE);
            paymentCard.setVisibility(View.GONE);

            button.setVisibility(View.VISIBLE);
            button.setText("Accept Booking");
            button.setBackgroundColor(Color.parseColor("#4CAF50"));
            button.setOnClickListener(v -> {
                bookingViewModel.acceptBooking(booking.getBookingId());
            });

            rateNow.setText(booking.getDriverRating());
            rateNow.setTextSize(20);
            rateNow.setVisibility(View.VISIBLE);
        }
    }

    private Runnable fetchBookingDetails = new Runnable() {
        @Override
        public void run() {
            bookingViewModel.getBookingDetails(getIntent().getIntExtra("bookingId", 8));
            handler.postDelayed(this, 2000);
        }
    };

    @Override
    protected void onPause(){
        super.onPause();
        handler.removeCallbacks(fetchBookingDetails);
    }
}