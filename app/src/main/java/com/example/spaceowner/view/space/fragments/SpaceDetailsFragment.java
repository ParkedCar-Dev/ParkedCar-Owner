package com.example.spaceowner.view.space.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.view.space.SpaceActivity;
import com.example.spaceowner.view.space.SpaceViewpagerAdapter;
import com.example.spaceowner.viewmodel.SpaceViewModel;
import com.example.spaceowner.viewmodel.ViewModelFactory;

public class SpaceDetailsFragment extends Fragment {
    SpaceViewModel viewModel;
    TextView latitude, longitude, address, length, width, height, baseFare, rating;
    CheckBox cctv, indoor, guard, auto_approval, activated;
    Button requestsButton, editButton;
    public SpaceDetailsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelFactory().create(SpaceViewModel.class);
    }

    @Override
    public void onResume() {
        updateSpace();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_space_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("SpaceDetailsFragment", "onViewCreated: "+viewModel.getCurrentSpaceDetails());

        if(viewModel.getCurrentSpaceDetails() == null){
            viewModel.fetchCurrentSpaceDetails();
            viewModel.getCurrentSpace().observe(getViewLifecycleOwner(), (space) -> {
                if(space != null){
                    updateSpace();
                }
            });
        }

        ImageSwitcher switcher = view.findViewById(R.id.space_image_switcher);
        switcher.setFactory(() -> {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        });
        switcher.setImageResource(R.drawable.space_place_holder);
        switcher.setImageResource(R.drawable.space_place_holder);


        latitude = view.findViewById(R.id.latitude_textview);
        longitude = view.findViewById(R.id.longitude_textview);
        address = view.findViewById(R.id.space_name_textview);
        length = view.findViewById(R.id.length_textview);
        width = view.findViewById(R.id.width_textview);
        height = view.findViewById(R.id.height_textview);
        baseFare = view.findViewById(R.id.base_fare_textview);
        rating = view.findViewById(R.id.rating_textview);
        cctv = view.findViewById(R.id.cctv_checkbox);
        indoor = view.findViewById(R.id.indoor_checkbox);
        guard = view.findViewById(R.id.guard_checkbox);
        auto_approval = view.findViewById(R.id.auto_approval_checkbox);
        activated = view.findViewById(R.id.activate_disable_checkbox);

        requestsButton = view.findViewById(R.id.show_requests_button);
        editButton = view.findViewById(R.id.edit_button);

        updateSpace();

        requestsButton.setOnClickListener((v) -> ((SpaceActivity)getActivity()).changeFragment(SpaceViewpagerAdapter.SpaceFragmentType.REQUESTS));
        editButton.setOnClickListener((v) -> ((SpaceActivity)getActivity()).changeFragment(SpaceViewpagerAdapter.SpaceFragmentType.UPDATE));
    }

    private void updateSpace() {
        Space currentSpace = viewModel.getCurrentSpaceDetails();
        if(currentSpace == null){
            Log.d("SpaceDetailsFragment", "updateSpace: currentSpace is null");
            return;
        }
        Log.d("SpaceDetailsFragment", "updateSpace: "+currentSpace.toString());
        latitude.setText("Lat: "+String.valueOf(currentSpace.getLatitude()));
        longitude.setText("Long: "+String.valueOf(currentSpace.getLongitude()));
        address.setText(currentSpace.getLocationAddress());
        length.setText("Length: "+String.valueOf(currentSpace.getLength()));
        width.setText("Width: "+String.valueOf(currentSpace.getWidth()));
        height.setText("Height: "+String.valueOf(currentSpace.getHeight()));
        baseFare.setText("Base Fare: "+String.valueOf(currentSpace.getBaseFare()));
        rating.setText("Rating: "+String.valueOf(currentSpace.getRating()));
        cctv.setChecked(currentSpace.isCctv());
        indoor.setChecked(currentSpace.isIndoor());
        guard.setChecked(currentSpace.isGuard());
        auto_approval.setChecked(currentSpace.isAutoApprove());

        activated.setChecked(currentSpace.isActivated());
        changeStatusText(currentSpace);
        activated.setOnClickListener((v) -> {
            if(activated.isChecked()) currentSpace.setStatus("active");
            else currentSpace.setStatus("disabled");
            changeStatusText(currentSpace);
            viewModel.updateStatus(currentSpace.getLocationId(), currentSpace.getStatus());
        });
    }

    private void changeStatusText(Space space) {
        if(activated.isChecked()) {
            activated.setText("Activated");
            activated.setTextColor(getResources().getColor(R.color.tilt));
        }else if(space.getStatus().equals("disabled")){
            activated.setText("Disabled");
            activated.setTextColor(getResources().getColor(R.color.red));
        }else if(space.getStatus().equals("requested")){
            activated.setText("Requested");
            activated.setTextColor(getResources().getColor(R.color.yellow));
            activated.setEnabled(false);
        }
    }
}