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
    TextView bookingId, fromTime, toTime, bookingStatus, address, city, driverName, rateNow, baseFare, timeFare, totalFare, paymentStatus;
    CardView paymentCard;
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
        fromTime = findViewById(R.id.from_date);
        toTime = findViewById(R.id.to_date);
        bookingStatus = findViewById(R.id.booking_status);
        address = findViewById(R.id.location_address);
        city = findViewById(R.id.city);
        driverName = findViewById(R.id.driver_name);
        baseFare = findViewById(R.id.booking_base_fare);
        timeFare = findViewById(R.id.booking_time_fare);
        totalFare = findViewById(R.id.total_fare);
        paymentStatus = findViewById(R.id.payment_status);

        rateNow = findViewById(R.id.rate_now_tv);
        button = findViewById(R.id.btn_add_review);
        paymentCard = findViewById(R.id.payment_card);

        bookingViewModel.getBookingDetails(bookingIdFromDifferentActivity);

        bookingViewModel.getCurrentBooking().observe(this, booking -> {
            if(booking != null && booking.getBookingId() > 0){
                bookingId.setText("Booking ID: #" + booking.getBookingId());
                fromTime.setText("From: "+booking.getFromTime());
                toTime.setText("To: "+booking.getToTime());
                bookingStatus.setText(booking.getStatus().equalsIgnoreCase("unpaid")?"Pending":booking.getStatus());

                if(booking.getStatus().equalsIgnoreCase("requested"))
                    bookingStatus.setTextColor(Color.BLUE);
                else if(booking.getStatus().equalsIgnoreCase("active"))
                    bookingStatus.setTextColor(Color.parseColor("#00802B"));
                else if(booking.getStatus().equalsIgnoreCase("completed"))
                    bookingStatus.setTextColor(Color.parseColor("#FFA500"));
                else if(booking.getStatus().equalsIgnoreCase("cancelled"))
                    bookingStatus.setTextColor(Color.RED);
                else
                    bookingStatus.setTextColor(Color.BLACK);

                address.setText(booking.getAddress());
                city.setText(booking.getCity());
                driverName.setText(booking.getDriverName());
                baseFare.setText(String.valueOf(booking.getBaseFare()));
                timeFare.setText(String.valueOf(booking.getTimeFare()));
                totalFare.setText(String.valueOf(booking.getTotalPrice()));
                paymentStatus.setText(booking.getPaymentStatus());

                if(booking.getPaymentStatus().equalsIgnoreCase("confirmed"))
                    paymentStatus.setTextColor(Color.parseColor("#00802B"));
                else if(booking.getPaymentStatus().equalsIgnoreCase("paid"))
                    paymentStatus.setTextColor(Color.parseColor("#0055FF"));
                else
                    paymentStatus.setTextColor(Color.RED);

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
        String paymentStatus = booking.getPaymentStatus();
        button.setVisibility(View.GONE);

        if(status.equalsIgnoreCase("requested")) {
            button.setText("Accept Booking");
            button.setVisibility(View.VISIBLE);
            button.setBackgroundColor(Color.BLUE);
            button.setOnClickListener(v -> bookingViewModel.acceptBooking(booking.getBookingId()));
        }

        if(paymentStatus.equalsIgnoreCase("paid")){
            button.setText("Confirm Payment");
            button.setVisibility(View.VISIBLE);
            button.setBackgroundColor(Color.parseColor("#00802B"));
            button.setOnClickListener(v -> bookingViewModel.confirmPayment(booking.getBookingId()));
        }

        if(status.equalsIgnoreCase("completed") && !booking.isRated()){
            rateNow.setText("Rate Now");
            rateNow.setOnClickListener(v -> {
                FragmentManager fm = getSupportFragmentManager();
                CustomDialog dialog = new CustomDialog(bookingViewModel);
                dialog.show(fm, "Rate Now");
            });
        }else{
            rateNow.setText(booking.getDriverRating());
            rateNow.setOnClickListener(null);
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