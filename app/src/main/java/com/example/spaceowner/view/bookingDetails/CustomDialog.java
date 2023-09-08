package com.example.spaceowner.view.bookingDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.spaceowner.R;
import com.example.spaceowner.viewmodel.BookingViewModel;

public class CustomDialog extends DialogFragment {
    RatingBar ratingBar;
    Button skipButton, rateButton;

    View.OnClickListener skipRating = v -> {
        dismiss();
    };

    BookingViewModel viewModel;

    public CustomDialog(BookingViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog_layout, container, false);
        ratingBar = view.findViewById(R.id.rating_bar);
        skipButton = view.findViewById(R.id.skip_button);
        rateButton = view.findViewById(R.id.rate_button);
        skipButton.setOnClickListener(skipRating);
        rateButton.setOnClickListener(v -> {
            double rating = ratingBar.getRating();
            Toast.makeText(getContext(), "Rating: " + rating, Toast.LENGTH_SHORT).show();
            viewModel.rateDriver(viewModel.getCurrentBooking().getValue().getBookingId(), rating);
            dismiss();
        });
        getDialog().setTitle("Rate the driver");
        return view;
    }
}
