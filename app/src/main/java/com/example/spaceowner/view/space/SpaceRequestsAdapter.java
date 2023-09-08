package com.example.spaceowner.view.space;

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
import com.example.spaceowner.model.data.Booking;
import com.example.spaceowner.view.bookingDetails.BookingDetailsActivity;
import com.example.spaceowner.viewmodel.SpaceViewModel;

import java.util.ArrayList;
import java.util.List;

class SpaceRequestViewHolder extends RecyclerView.ViewHolder {
    TextView driverName, rating, fare, timeFrom, timeTo;
    Button accept, reject;

    int bookingId;
    public SpaceRequestViewHolder(@NonNull View itemView) {
        super(itemView);
        driverName = itemView.findViewById(R.id.request_card_driver_name);
        rating = itemView.findViewById(R.id.request_card_rating);
        fare = itemView.findViewById(R.id.request_card_total_fare);
        timeFrom = itemView.findViewById(R.id.request_card_time_from);
        timeTo = itemView.findViewById(R.id.request_card_time_to);

        accept = itemView.findViewById(R.id.accept_button);
        reject = itemView.findViewById(R.id.reject_button);

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), BookingDetailsActivity.class);
            intent.putExtra("bookingId", bookingId);
            itemView.getContext().startActivity(intent);
        });
    }

    public void setBooking(Booking booking){
        driverName.setText(booking.getDriverName());
        rating.setText(booking.getDriverRating());
        fare.setText(booking.getTotalPrice());
        timeFrom.setText(booking.getFromTime());
        timeTo.setText(booking.getToTime());
        bookingId = booking.getBookingId();
    }
}

public class SpaceRequestsAdapter extends RecyclerView.Adapter<SpaceRequestViewHolder> {
    private SpaceViewModel viewModel;
    private List<Booking> bookings = new ArrayList<>();
    public SpaceRequestsAdapter(SpaceViewModel viewModel) {
        this.viewModel = viewModel;
        bookings.add(new Booking());
    }

    @NonNull
    @Override
    public SpaceRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.space_request_card_item, parent, false);
        return new SpaceRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceRequestViewHolder holder, int position) {
        Booking booking = bookings.get(position);

        Log.d("SpaceRequestsAdapter", booking.toString());
        holder.setBooking(booking);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void setBookings(List<Booking> requests) {
        this.bookings = requests;
    }
}
