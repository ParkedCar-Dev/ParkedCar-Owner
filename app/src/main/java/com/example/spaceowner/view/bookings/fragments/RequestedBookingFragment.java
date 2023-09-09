package com.example.spaceowner.view.bookings.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.view.bookings.fragments.adapters.BookingListAdapter;
import com.example.spaceowner.viewmodel.BookingViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;

public class RequestedBookingFragment extends Fragment {
    BookingViewModel viewModel;
    public RequestedBookingFragment() {
        viewModel = new ViewModelFactory().create(BookingViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchRequestedBookings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_requested_bookings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.requested_bookings_recyclerview);
        BookingListAdapter adapter = new BookingListAdapter(viewModel);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getRequestedBookings().observe(getViewLifecycleOwner(), (bookings) -> {
            if(bookings != null){
                adapter.setBookings(bookings);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getAcceptDeclineResponse().observe(getViewLifecycleOwner(), (response) -> {
            if(response != null){
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                    viewModel.getAcceptDeclineResponse().setValue(new GenericResponse("null", "null"));
                    viewModel.fetchRequestedBookings();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.fetchRequestedBookings();
    }
}