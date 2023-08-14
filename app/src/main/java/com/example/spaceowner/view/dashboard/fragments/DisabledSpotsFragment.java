package com.example.spaceowner.view.dashboard.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.spaceowner.R;
import com.example.spaceowner.view.auth.AuthActivity;
import com.example.spaceowner.view.dashboard.DisabledSpaceRecyclerViewAdapter;
import com.example.spaceowner.viewmodel.SpaceListViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;

public class DisabledSpotsFragment extends Fragment {
    SpaceListViewModel viewModel;

    public DisabledSpotsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_disabled_spots, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchDisabledSpaces();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(SpaceListViewModel.class);

        RecyclerView recyclerView = getView().findViewById(R.id.disabled_spots_recycler_view);
        DisabledSpaceRecyclerViewAdapter adapter = new DisabledSpaceRecyclerViewAdapter(viewModel);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getDisabledSpaceList().observe(getViewLifecycleOwner(), (spaces) -> {
            if(spaces != null){
                adapter.setDisabledSpaces(spaces);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.getUpdateStatusResponse().observe(getViewLifecycleOwner(), (response) -> {
            if(response != null){
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Refreshing Disabled", Toast.LENGTH_SHORT).show();
                    viewModel.refreshAllData();
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "Failed to update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.fetchDisabledSpaces();
    }
}