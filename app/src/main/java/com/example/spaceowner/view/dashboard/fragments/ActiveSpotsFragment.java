package com.example.spaceowner.view.dashboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spaceowner.R;
import com.example.spaceowner.view.dashboard.ActiveSpaceRecyclerViewAdapter;
import com.example.spaceowner.view.dashboard.DisabledSpaceRecyclerViewAdapter;
import com.example.spaceowner.viewmodel.LoginViewModel;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(SpaceListViewModel.class);

        RecyclerView recyclerView = getView().findViewById(R.id.active_spots_recycler_view);
        ActiveSpaceRecyclerViewAdapter adapter = new ActiveSpaceRecyclerViewAdapter(SpaceType.ACTIVE);
//        adapter.setACTIVE_SPACES(viewModel.getSpaces().getValue());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getSpaces().observe(getViewLifecycleOwner(), (spaces) -> {
            adapter.setACTIVE_SPACES(spaces);
            adapter.notifyDataSetChanged();
        });

        viewModel.fetchSpaces();
    }

}