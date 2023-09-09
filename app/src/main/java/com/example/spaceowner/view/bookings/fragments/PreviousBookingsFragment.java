package com.example.spaceowner.view.bookings.fragments;

import android.os.Bundle;

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
import com.google.android.material.switchmaterial.SwitchMaterial;

public class PreviousBookingsFragment extends Fragment {
    BookingViewModel viewModel;
    SwitchMaterial cancelledSwitch;
    boolean showCancelled = false;
    public PreviousBookingsFragment() {
        viewModel = new ViewModelFactory().create(BookingViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchPastBookings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_previous_bookings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cancelledSwitch = getView().findViewById(R.id.cancelledButton);

        RecyclerView recyclerView = getView().findViewById(R.id.previous_bookings_recyclerview);
        BookingListAdapter adapter = new BookingListAdapter(viewModel);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewModel.setShowCancelled(cancelledSwitch.isChecked());

        viewModel.getPastBookings().observe(getViewLifecycleOwner(), (bookings) -> {
            if(bookings != null){
                adapter.setBookings(bookings);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getAcceptDeclineResponse().observe(getViewLifecycleOwner(), (response) -> {
            if(response != null){
                if(response.isSuccessful()){
                    viewModel.getAcceptDeclineResponse().setValue(new GenericResponse("null", "null"));
                    viewModel.fetchPastBookings();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        cancelledSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setShowCancelled(isChecked);
            viewModel.fetchPastBookings();
            adapter.notifyDataSetChanged();
        });

        viewModel.fetchPastBookings();
    }
}