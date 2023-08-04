package com.example.spaceowner.view.dashboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spaceowner.R;

public class RequestedSpotsFragment extends Fragment {
    public RequestedSpotsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_requested_spots, container, false);
    }
}