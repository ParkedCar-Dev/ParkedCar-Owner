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

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.view.bookings.fragments.adapters.BookingListAdapter;
import com.example.spaceowner.viewmodel.BookingViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;

public class CurrentBookingsFragment extends Fragment {
    BookingViewModel viewModel;
    public CurrentBookingsFragment() {
        viewModel = new ViewModelFactory().create(BookingViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchActiveBookings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_bookings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.current_bookings_recyclerview);
        BookingListAdapter adapter = new BookingListAdapter(viewModel);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getActiveBookings().observe(getViewLifecycleOwner(), (bookings) -> {
            if (bookings != null) {
                adapter.setBookings(bookings);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getAcceptDeclineResponse().observe(getViewLifecycleOwner(), (response) -> {
            if(response != null){
                if(response.isSuccessful()){
                    viewModel.getAcceptDeclineResponse().setValue(new GenericResponse("null", "null"));
                    viewModel.fetchActiveBookings();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.fetchActiveBookings();
    }
}