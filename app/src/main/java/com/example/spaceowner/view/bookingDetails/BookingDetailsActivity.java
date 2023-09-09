package com.example.spaceowner.view.bookingDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

        bookingViewModel.getBookingDetails(bookingIdFromDifferentActivity);

        bookingViewModel.getCurrentBooking().observe(this, booking -> {
            if(booking != null && booking.getBookingId() > 0){
                bookingId.setText("Booking ID: #" + booking.getBookingId());
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
//                Log.d("RateNowButton", "booking: " + booking);
//                Log.d("RateNowButton", "isRated: " + booking.isRated());
                setButton(booking);
            }
        });

        bookingViewModel.getAcceptDeclineResponse().observe(this, response -> {
            if(response != null && response.isSuccessful()){
                bookingViewModel.getBookingDetails(bookingIdFromDifferentActivity);
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
            rateNow.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            rateNow.setVisibility(View.VISIBLE);
        }else if(booking.getPaymentStatus().equalsIgnoreCase("paid")){
            requestAgain.setVisibility(View.GONE);
            reportIssue.setVisibility(View.GONE);
            paymentCard.setVisibility(View.VISIBLE);

            button.setVisibility(View.VISIBLE);
            button.setText("Confirm Payment");
            button.setBackgroundColor(Color.parseColor("#4CAF50"));
            button.setOnClickListener(v -> {
                bookingViewModel.confirmPayment(booking.getBookingId());
            });
            rateNow.setVisibility(View.GONE);
        }else if(booking.getPaymentStatus().equalsIgnoreCase("confirmed")) {
            requestAgain.setVisibility(View.GONE);
            reportIssue.setVisibility(View.GONE);
            paymentCard.setVisibility(View.VISIBLE);

            button.setVisibility(View.GONE);
        }

//        Log.d("RateNowButtonSetButton", "setButton: " + booking.isRated());
//        Log.d("RateNowButtonSetButton", "booking: " + booking.getBookingStatus());
        if(booking.isRated()){
//            Log.d("RateNowButton", "setButton: " + booking.isRated());
//            Log.d("RateNowButton", "booking: " + booking);
            rateNow.setText(booking.getDriverRating());
            rateNow.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            rateNow.setVisibility(View.VISIBLE);
        }else if(!booking.isRated() && booking.getStatus().equalsIgnoreCase("completed")){
//            Log.d("RateNowButton", "setButton: " + booking.isRated());
            rateNow.setText("Rate Now");
            rateNow.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            rateNow.setVisibility(View.VISIBLE);
            rateNow.setOnClickListener(v -> {
                FragmentManager fm = getSupportFragmentManager();
                CustomDialog customDialog = new CustomDialog(bookingViewModel);
                customDialog.show(fm, "Rate Driver");
            });
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