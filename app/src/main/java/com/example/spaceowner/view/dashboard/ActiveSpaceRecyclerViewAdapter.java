package com.example.spaceowner.view.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.Space;

import java.util.LinkedList;
import java.util.List;

class ActiveSpaceViewHolder extends RecyclerView.ViewHolder{
    TextView address, basefare, rating;
    Button requestButton, disableButton;
    public ActiveSpaceViewHolder(@NonNull View itemView) {
        super(itemView);
        address = itemView.findViewById(R.id.space_card_address);
        basefare = itemView.findViewById(R.id.space_card_base_fare);
        rating = itemView.findViewById(R.id.space_card_rating);
        requestButton = itemView.findViewById(R.id.requests);
        disableButton = itemView.findViewById(R.id.disable);
    }
}

public class ActiveSpaceRecyclerViewAdapter extends RecyclerView.Adapter<ActiveSpaceViewHolder>{
    private static List<Space> ACTIVE_SPACES = new LinkedList<>();
    private View.OnClickListener onRequest, onDisable;
    public ActiveSpaceRecyclerViewAdapter(View.OnClickListener onRequest, View.OnClickListener onDisable) {
        this.onRequest = onRequest;
        this.onDisable = onDisable;

//        populate active_space with dummy data
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
        ACTIVE_SPACES.add(new Space());
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
        holder.address.setText(space.getLocationAddress());
        holder.basefare.setText(space.getBaseFare());
        holder.rating.setText(Double.toString(space.getRating()));

        holder.requestButton.setOnClickListener(onRequest);
        holder.disableButton.setOnClickListener(onDisable);
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
