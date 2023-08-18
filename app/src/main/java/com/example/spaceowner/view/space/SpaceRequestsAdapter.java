package com.example.spaceowner.view.space;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spaceowner.R;
import com.example.spaceowner.model.data.requests.SpaceRequest;
import com.example.spaceowner.viewmodel.SpaceViewModel;

import java.util.ArrayList;
import java.util.List;

class SpaceRequestViewHolder extends RecyclerView.ViewHolder {
    TextView driverName, rating, fare, timeFrom, timeTo;
    Button accept, reject;
    public SpaceRequestViewHolder(@NonNull View itemView) {
        super(itemView);
        driverName = itemView.findViewById(R.id.request_card_driver_name);
        rating = itemView.findViewById(R.id.request_card_rating);
        fare = itemView.findViewById(R.id.request_card_fare);
        timeFrom = itemView.findViewById(R.id.request_card_time_from);
        timeTo = itemView.findViewById(R.id.request_card_time_to);

        accept = itemView.findViewById(R.id.accept_button);
        reject = itemView.findViewById(R.id.reject_button);
    }
}

public class SpaceRequestsAdapter extends RecyclerView.Adapter<SpaceRequestViewHolder> {
    private List<SpaceRequest> requestList = new ArrayList<>();
    private SpaceViewModel viewModel;
    public SpaceRequestsAdapter(SpaceViewModel viewModel) {
        requestList.add(new SpaceRequest());
        requestList.add(new SpaceRequest());
        requestList.add(new SpaceRequest());
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public SpaceRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.space_request_card_item, parent, false);
        return new SpaceRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceRequestViewHolder holder, int position) {
        SpaceRequest request = requestList.get(position);

        Log.d("SpaceRequestsAdapter", request.toString());
        holder.driverName.setText(request.getDriverName());
        holder.rating.setText(request.getDriverRating());
        holder.fare.setText(request.getFare());
        holder.timeFrom.setText(request.getTimeFrom());
        holder.timeTo.setText(request.getTimeTo());
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }
}
