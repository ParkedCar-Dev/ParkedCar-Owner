package com.example.spaceowner.view.dashboard;

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
import com.example.spaceowner.viewmodel.SpaceListViewModel;

import java.util.LinkedList;
import java.util.List;

class RequestedSpaceViewHolder extends RecyclerView.ViewHolder{
    SpaceListViewModel viewModel;
    TextView address, basefare, rating;
    Button updateButton, deleteButton;
    Space space;
    public RequestedSpaceViewHolder(@NonNull View itemView) {
        super(itemView);
        address = itemView.findViewById(R.id.requested_address);
        basefare = itemView.findViewById(R.id.requested_base_fare);
        rating = itemView.findViewById(R.id.requested_rating);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Show "+space.getLocationId()+" Details", Toast.LENGTH_SHORT).show();
            }
        });
        updateButton = itemView.findViewById(R.id.update);
        deleteButton = itemView.findViewById(R.id.delete);

        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Update "+space.getLocationId(), Toast.LENGTH_SHORT).show();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Delete "+space.getLocationId(), Toast.LENGTH_SHORT).show();
                viewModel.deleteRequestedSpace(space.getLocationId());
            }
        });
    }
}

public class RequestedSpaceRecyclerViewAdapter extends RecyclerView.Adapter<RequestedSpaceViewHolder>{
    private List<Space> ACTIVE_SPACES = new LinkedList<>();
    private SpaceListViewModel viewModel;
    public RequestedSpaceRecyclerViewAdapter(SpaceListViewModel viewModel){
        this.viewModel = viewModel;
    }
    @NonNull
    @Override
    public RequestedSpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.requested_space_card_item, parent, false);
        return new RequestedSpaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestedSpaceViewHolder holder, int position) {
        Space space = ACTIVE_SPACES.get(position);
        holder.address.setText(space.getLocationAddress());
        holder.basefare.setText(Double.toString(space.getBaseFare()));
        holder.rating.setText(Double.toString(space.getRating()));

        holder.space = space;
        holder.viewModel = viewModel;
    }

    @Override
    public int getItemCount() {
        return ACTIVE_SPACES.size();
    }

    public void setRequestedSpaces(List<Space> spaces) {
        ACTIVE_SPACES.clear();
        ACTIVE_SPACES.addAll(spaces);
    }
}
