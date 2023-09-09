package com.example.spaceowner.view.bookings.fragments.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.booking.Booking;
import com.example.spaceowner.view.bookingDetails.BookingDetailsActivity;
import com.example.spaceowner.viewmodel.BookingViewModel;

import java.util.ArrayList;
import java.util.List;

class BookingListViewHolder extends RecyclerView.ViewHolder {
    Booking booking;
    BookingViewModel viewModel;
    TextView driverName, rating, fare, timeFrom, timeTo, location, status;
    Button accept, reject;

    public BookingListViewHolder(@NonNull View itemView) {
        super(itemView);
        driverName = itemView.findViewById(R.id.request_card_driver_name);
        rating = itemView.findViewById(R.id.request_card_rating);
        fare = itemView.findViewById(R.id.request_card_total_fare);
        timeFrom = itemView.findViewById(R.id.request_card_time_from);
        timeTo = itemView.findViewById(R.id.request_card_time_to);
        location = itemView.findViewById(R.id.request_card_location);

        accept = itemView.findViewById(R.id.accept_button);
        reject = itemView.findViewById(R.id.reject_button);
        status = itemView.findViewById(R.id.request_card_status);
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), BookingDetailsActivity.class);
            intent.putExtra("bookingId", booking.getBookingId());
            itemView.getContext().startActivity(intent);
        });
    }

    public void setBooking(Booking booking, BookingViewModel viewModel, boolean isSpaceBookings){
        this.booking = booking;
        this.viewModel = viewModel;

        driverName.setText(booking.getDriverName());
        rating.setText(booking.getDriverRating());
        fare.setText(booking.getTotalPrice());
        timeFrom.setText(String.format("From: %s", booking.getFromTime()));
        timeTo.setText(String.format("  To:%s", booking.getToTime()));

        Log.d("BookingListAdapter", "isSpaceBookings: ");

        if(isSpaceBookings){
            location.setHeight(0);
            location.setVisibility(View.GONE);
        }else{
            location.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            location.setVisibility(View.VISIBLE);
            location.setText(String.format("%s, %s", booking.getLocationAddress(), booking.getCity()));
        }

        if(booking.getStatus().equalsIgnoreCase("requested")){
            accept.setVisibility(View.VISIBLE);
            reject.setVisibility(View.VISIBLE);
            status.setVisibility(View.GONE);
            accept.setOnClickListener(v -> {
                viewModel.acceptBooking(booking.getBookingId());
            });
            reject.setOnClickListener(v -> {
                viewModel.declineBooking(booking.getBookingId());
            });
        }else {
            accept.setVisibility(View.GONE);
            reject.setVisibility(View.GONE);
            status.setVisibility(View.VISIBLE);
            status.setText(booking.getStatus());
        }
    }
}

public class BookingListAdapter extends RecyclerView.Adapter<BookingListViewHolder> {
    BookingViewModel viewModel;
    List<Booking> bookings;
    boolean isSpaceBookings = false;

    public BookingListAdapter(BookingViewModel viewModel) {
        this.viewModel = viewModel;
        bookings = new ArrayList<>();
    }

    public BookingListAdapter(BookingViewModel viewModel, boolean isSpaceBookings) {
        this(viewModel);
        this.isSpaceBookings = isSpaceBookings;
    }

    @NonNull
    @Override
    public BookingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_common_booking_request, parent, false);
        return new BookingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingListViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.setBooking(booking, viewModel, isSpaceBookings);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void setBookings(List<Booking> bookings){
        this.bookings = bookings;
    }
}
