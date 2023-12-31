package com.example.spaceowner.view.space.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.view.bookings.fragments.adapters.BookingListAdapter;
import com.example.spaceowner.view.space.SpaceActivity;
import com.example.spaceowner.view.space.SpaceViewpagerAdapter;
import com.example.spaceowner.viewmodel.BookingViewModel;
import com.example.spaceowner.viewmodel.SpaceViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;

public class SpaceRequestsFragment extends Fragment {
    RecyclerView recyclerView;
    SpaceViewModel viewModel;
    BookingViewModel bookingViewModel;


    public SpaceRequestsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelFactory().create(SpaceViewModel.class);
        bookingViewModel = new ViewModelFactory().create(BookingViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_space_requests, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if(viewModel.getCurrentSpaceDetails() != null && viewModel.getCurrentSpaceDetails().getLocationAddress() != null){
            TextView locationAddress = getView().findViewById(R.id.space_name_textview);
            locationAddress.setText(viewModel.getCurrentSpaceDetails().getLocationAddress());
        }

        recyclerView = getView().findViewById(R.id.space_requests_recyclerview);
//        SpaceRequestsAdapter adapter = new SpaceRequestsAdapter(viewModel);
        BookingListAdapter adapter = new BookingListAdapter(bookingViewModel, true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getSpaceRequests().observe(getViewLifecycleOwner(), (requests) -> {
            if(requests != null){
                adapter.setBookings(requests.getBookings());
                adapter.notifyDataSetChanged();
            }else Log.d("SPACE REQUESTS FRAGMENT", "null requests");
        });

        bookingViewModel.getAcceptDeclineResponse().observe(getViewLifecycleOwner(), (response) -> {
            if(response != null){
                if(response.isSuccessful()){
                    Log.d("SpaceRequestsFragment", "onViewCreated: " + response);
                    bookingViewModel.getAcceptDeclineResponse().setValue(new GenericResponse("null", "null"));
                    viewModel.fetchSpaceRequests();
                }
            }
        });

        viewModel.fetchSpaceRequests();


        view.findViewById(R.id.fab_edit_space).setOnClickListener(v -> ((SpaceActivity)getActivity()).changeFragment(SpaceViewpagerAdapter.SpaceFragmentType.UPDATE));

        Log.d("SpaceRequestsFragment", "onViewCreated: ");
    }
}