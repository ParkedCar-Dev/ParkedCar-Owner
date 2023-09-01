package com.example.spaceowner.view.space.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.view.space.SpaceActivity;
import com.example.spaceowner.view.space.SpaceViewpagerAdapter;
import com.example.spaceowner.viewmodel.SpaceViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;

public class SpaceUpdateFragment extends Fragment {
    ImageView locationIcon;
    TextInputEditText locationAddress;
    TextInputEditText latitudeEditText;
    TextInputEditText longitudeEditText;
    TextInputEditText baseFareEditText;
    TextInputEditText length, width, height;
    CheckBox autoApprove, cctv, guard, indoor;

    SpaceViewModel viewModel;

    public SpaceUpdateFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        viewModel = new ViewModelFactory().create(SpaceViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_space_update, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        locationIcon = view.findViewById(R.id.location_icon);
        locationAddress = view.findViewById(R.id.location_address);
        latitudeEditText = view.findViewById(R.id.location_latitude);
        longitudeEditText = view.findViewById(R.id.location_longitude);

        latitudeEditText.setEnabled(false);
        longitudeEditText.setEnabled(false);

        baseFareEditText = view.findViewById(R.id.location_base_fare);
        length = view.findViewById(R.id.length);
        width = view.findViewById(R.id.width);
        height = view.findViewById(R.id.height);


        autoApprove = view.findViewById(R.id.auto_approval);
        cctv = view.findViewById(R.id.cc_camera);
        guard = view.findViewById(R.id.guard);
        indoor = view.findViewById(R.id.indoor);

        Space currentSpace = viewModel.getCurrentSpaceDetails();
        if(currentSpace != null){
            locationAddress.setText(currentSpace.getLocationAddress());
            latitudeEditText.setText(String.valueOf(currentSpace.getLatitude()));
            longitudeEditText.setText(String.valueOf(currentSpace.getLongitude()));
            length.setText(String.valueOf(currentSpace.getLength()));
            width.setText(String.valueOf(currentSpace.getWidth()));
            height.setText(String.valueOf(currentSpace.getHeight()));
            autoApprove.setChecked(currentSpace.isAutoApprove());
            cctv.setChecked(currentSpace.isCctv());
            guard.setChecked(currentSpace.isGuard());
            indoor.setChecked(currentSpace.isIndoor());
            baseFareEditText.setText(String.valueOf(currentSpace.getBaseFare()));
        }

        view.findViewById(R.id.submit_button).setOnClickListener((v) -> {
            Space space = new Space(currentSpace);
            space.setLocationAddress(locationAddress.getText().toString());
            space.setLatitude(Double.parseDouble(latitudeEditText.getText().toString()));
            space.setLongitude(Double.parseDouble(longitudeEditText.getText().toString()));
            space.setLength(Double.parseDouble(length.getText().toString()));
            space.setWidth(Double.parseDouble(width.getText().toString()));
            space.setHeight(Double.parseDouble(height.getText().toString()));
            space.setAutoApprove(autoApprove.isChecked());
            space.setSecurity(cctv.isChecked(), guard.isChecked(), indoor.isChecked());
            space.setBaseFare(Double.parseDouble(baseFareEditText.getText().toString()));


            if(space.equals(currentSpace)){
                Log.d("SpaceUpdateFragment", "onViewCreated: space is same as current space");
                return;
            }

            viewModel.updateSpace(space);
        });


        viewModel.getSpaceUpdateResult().observe(getViewLifecycleOwner(), (isUpdated) -> {
            if(isUpdated){
                ((SpaceActivity)getActivity()).changeFragment(SpaceViewpagerAdapter.SpaceFragmentType.DETAILS);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}