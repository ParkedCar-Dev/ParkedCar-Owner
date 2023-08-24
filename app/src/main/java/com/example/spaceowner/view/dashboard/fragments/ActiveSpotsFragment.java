package com.example.spaceowner.view.dashboard.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.spaceowner.R;
import com.example.spaceowner.view.auth.AuthActivity;
import com.example.spaceowner.view.dashboard.adapters.ActiveSpaceRecyclerViewAdapter;
import com.example.spaceowner.viewmodel.SpaceListViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;

public class ActiveSpotsFragment extends Fragment {

    SpaceListViewModel viewModel;

    public ActiveSpotsFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active_spots, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchActiveSpaces();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(SpaceListViewModel.class);

        RecyclerView recyclerView = getView().findViewById(R.id.active_spots_recycler_view);
        ActiveSpaceRecyclerViewAdapter adapter = new ActiveSpaceRecyclerViewAdapter(viewModel);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getActiveSpaces().observe(getViewLifecycleOwner(), (spaces) -> {

            if(spaces != null){
                if(spaces.size() == 1 && spaces.get(0).isTimedOut()){
                    Toast.makeText(getContext(), "Timeout: Retrying", Toast.LENGTH_SHORT).show();
                    new Thread(() -> {
                        try {
                            Thread.sleep(5000);
                            viewModel.fetchActiveSpaces();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    return;
                }
                adapter.setActiveSpaces(spaces);
                adapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getContext(), "Session expired", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), AuthActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        viewModel.getUpdateStatusResponse().observe(getViewLifecycleOwner(), (response) -> {
            if(response != null){
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Refreshing Active", Toast.LENGTH_SHORT).show();
                    viewModel.refreshAllData();
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "Failed to update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.fetchActiveSpaces();
    }
}