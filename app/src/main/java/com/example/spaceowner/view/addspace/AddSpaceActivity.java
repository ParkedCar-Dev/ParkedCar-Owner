package com.example.spaceowner.view.addspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.spaceowner.R;
import com.example.spaceowner.view.dashboard.DashboardActivity;
import com.example.spaceowner.viewmodel.AddSpaceViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddSpaceActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    ImageView locationIcon;
    TextInputEditText locationAddress;
    TextInputEditText latitudeEditText;
    TextInputEditText longitudeEditText;
    TextInputEditText cityEditText;
    TextInputEditText baseFareEditText;
    TextInputEditText length, width, height;
    CheckBox autoApprove, cctv, guard, indoor;

    AddSpaceViewModel addSpaceViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_space);

        addSpaceViewModel = new ViewModelFactory().create(AddSpaceViewModel.class);

        locationIcon = findViewById(R.id.location_icon);
        locationAddress = findViewById(R.id.location_address);
        cityEditText = findViewById(R.id.location_city);
        baseFareEditText = findViewById(R.id.base_fare);
        latitudeEditText = findViewById(R.id.location_latitude);
        longitudeEditText = findViewById(R.id.location_longitude);
        length = findViewById(R.id.length);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);

        autoApprove = findViewById(R.id.auto_approval);
        cctv = findViewById(R.id.cc_camera);
        guard = findViewById(R.id.guard);
        indoor = findViewById(R.id.indoor);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLocation();
            }
        });

        addSpaceViewModel.getResponse().observe(this, response -> {
            if(response != null && response.isSuccessful()){
                Log.d("ADD_SPACE", "onCreate: " + response.toString());
                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
                this.finish();
            }else{
                Log.d("ADD_SPACE", "onCreate: " + response.toString());
            }
        });

        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            private boolean validate(){
                if(locationAddress.getText().toString().isEmpty()){
                    locationAddress.setError("Location address is required");
                    return false;
                }
                if(latitudeEditText.getText().toString().isEmpty() || longitudeEditText.getText().toString().isEmpty()){
                    latitudeEditText.setError("Latitude is required");
                    longitudeEditText.setError("Longitude is required");
                    return false;
                }
                if(length.getText().toString().isEmpty() || width.getText().toString().isEmpty() || height.getText().toString().isEmpty()){
                    length.setError("Length is required");
                    width.setError("Width is required");
                    height.setError("Height is required");
                    return false;
                }
                return true;
            }


            @Override
            public void onClick(View view) {
                if(!validate()) return;
                addSpaceViewModel.addSpace(
                        locationAddress.getText().toString(),
                        Double.parseDouble(latitudeEditText.getText().toString()),
                        Double.parseDouble(longitudeEditText.getText().toString()),
                        Double.parseDouble(length.getText().toString()),
                        Double.parseDouble(width.getText().toString()),
                        Double.parseDouble(height.getText().toString()),
                        autoApprove.isChecked(),
                        cctv.isChecked(),
                        guard.isChecked(),
                        indoor.isChecked(),
                        cityEditText.getText().toString(),
                        Double.parseDouble(baseFareEditText.getText().toString())
                );
            }
        });
    }

    private void updateLocation(){
        Log.d("ADD_SPACE", "updateLocation: ");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionGranted();
            return;
        }
        Log.d("ADD_SPACE", "getLastLocationCalled");
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Log.d("ADD_SPACE", "onSuccess: " + location);
                if(location != null){
                    Log.d("ADD_SPACE", "onSuccess: " + location.getLatitude() + " " + location.getLongitude());
                    latitudeEditText.setText(String.valueOf(location.getLatitude()));
                    longitudeEditText.setText(String.valueOf(location.getLongitude()));

                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if(addresses != null && addresses.size() > 0){
                            String city = addresses.get(0).getLocality();
                            cityEditText.setText(city);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
    }

    private boolean isLocationPermissionGranted(){
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    1
            );
            return false;
        }else return true;
    }
}