package com.example.spaceowner.view.dashboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.spaceowner.R;
import com.example.spaceowner.view.dashboard.ActiveSpaceRecyclerViewAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ActiveSpotsFragment extends Fragment {
    public ActiveSpotsFragment(){}
    private OnRequestClickListener onRequestClickListener = new OnRequestClickListener();
    private OnDisableClickListener onDisableClickListener = new OnDisableClickListener();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active_spots, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.active_spots_recycler_view);
        ActiveSpaceRecyclerViewAdapter adapter = new ActiveSpaceRecyclerViewAdapter(onRequestClickListener, onDisableClickListener);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }


    class OnRequestClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "Request Sent", Toast.LENGTH_SHORT).show();
        }
    }
    class OnDisableClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "Spot Disabled", Toast.LENGTH_SHORT).show();
        }
    }
}