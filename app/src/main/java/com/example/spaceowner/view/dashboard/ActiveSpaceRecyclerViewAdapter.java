package com.example.spaceowner.view.dashboard;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.view.space.SpaceActivity;
import com.example.spaceowner.view.space.SpaceViewpagerAdapter;
import com.example.spaceowner.viewmodel.SpaceListViewModel;

import java.util.LinkedList;
import java.util.List;

class ActiveSpaceViewHolder extends RecyclerView.ViewHolder{
    TextView address, basefare, rating;
    Button requestButton, disableButton;
    Space space;
    SpaceListViewModel viewModel;
    public ActiveSpaceViewHolder(@NonNull View itemView) {
        super(itemView);
        address = itemView.findViewById(R.id.active_address);
        basefare = itemView.findViewById(R.id.active_base_fare);
        rating = itemView.findViewById(R.id.active_rating);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Show "+space.getLocationId()+" Details", Toast.LENGTH_SHORT).show();
                changeFragment(SpaceViewpagerAdapter.SpaceFragmentType.DETAILS);
            }
        });
        requestButton = itemView.findViewById(R.id.requests);
        disableButton = itemView.findViewById(R.id.disable);

        requestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Show "+space.getLocationId()+" Requests", Toast.LENGTH_SHORT).show();
                changeFragment(SpaceViewpagerAdapter.SpaceFragmentType.REQUESTS);
            }
        });
        disableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Disable "+space.getLocationId(), Toast.LENGTH_SHORT).show();
                viewModel.updateStatus(space.getLocationId(), "disabled");
            }
        });
    }
    public void changeFragment(SpaceViewpagerAdapter.SpaceFragmentType fragmentType){
        Intent intent = new Intent(itemView.getContext(), SpaceActivity.class);
        intent.putExtra("space", space);
        intent.putExtra("fragment", fragmentType);
        itemView.getContext().startActivity(intent);
    }
}

public class ActiveSpaceRecyclerViewAdapter extends RecyclerView.Adapter<ActiveSpaceViewHolder>{
    private List<Space> ACTIVE_SPACES = new LinkedList<>();
    private SpaceListViewModel viewModel;
    public ActiveSpaceRecyclerViewAdapter(SpaceListViewModel viewModel){
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        this.viewModel = viewModel;
    }
    @NonNull
    @Override
    public ActiveSpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.active_space_card_item, parent, false);
        return new ActiveSpaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveSpaceViewHolder holder, int position) {
        Space space = ACTIVE_SPACES.get(position);
        if(holder == null)
            Log.d("SpaceRecyclerViewAdapter", "holder is null");
        holder.address.setText(space.getLocationAddress());
        holder.basefare.setText(Double.toString(space.getBaseFare()));
        holder.rating.setText(Double.toString(space.getRating()));

        holder.space = space;
        holder.viewModel = this.viewModel;
    }

    @Override
    public int getItemCount() {
        return ACTIVE_SPACES.size();
    }

    public void setActiveSpaces(List<Space> spaces) {
        ACTIVE_SPACES.clear();
        ACTIVE_SPACES.addAll(spaces);
    }
}
