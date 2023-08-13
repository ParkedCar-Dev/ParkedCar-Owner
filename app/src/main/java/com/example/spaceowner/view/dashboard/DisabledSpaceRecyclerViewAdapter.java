package com.example.spaceowner.view.dashboard;

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
import com.example.spaceowner.view.dashboard.fragments.SpaceType;

import java.util.LinkedList;
import java.util.List;

class DisabledSpaceViewHolder extends RecyclerView.ViewHolder{
    TextView address, basefare, rating;
    Button activateButton;
    Space space;
    public DisabledSpaceViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d("SpaceViewHolder", itemView.toString()+" is created");
        address = itemView.findViewById(R.id.disabled_address);
        basefare = itemView.findViewById(R.id.disabled_base_fare);
        rating = itemView.findViewById(R.id.disabled_rating);

        activateButton = itemView.findViewById(R.id.activate);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Show "+space.getLocationId()+" Details", Toast.LENGTH_SHORT).show();
            }
        });

        activateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Activate "+space.getLocationId(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

public class DisabledSpaceRecyclerViewAdapter extends RecyclerView.Adapter<DisabledSpaceViewHolder>{
    private List<Space> ACTIVE_SPACES = new LinkedList<>();
    private SpaceType spaceType;
    public DisabledSpaceRecyclerViewAdapter(SpaceType spaceType){
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        this.spaceType = spaceType;
    }
    @NonNull
    @Override
    public DisabledSpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.disabled_card_item, parent, false);
        return new DisabledSpaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisabledSpaceViewHolder holder, int position) {
        Space space = ACTIVE_SPACES.get(position);
        holder.address.setText(space.getLocationAddress());
        holder.basefare.setText(Double.toString(space.getBaseFare()));
        holder.rating.setText(Double.toString(space.getRating()));

        holder.space = space;
    }

    @Override
    public int getItemCount() {
        return ACTIVE_SPACES.size();
    }

    public void setACTIVE_SPACES(List<Space> spaces) {
        ACTIVE_SPACES.clear();
        ACTIVE_SPACES.addAll(spaces);
    }
}
