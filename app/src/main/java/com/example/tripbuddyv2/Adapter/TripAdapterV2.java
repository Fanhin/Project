package com.example.tripbuddyv2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripbuddyv2.Models.ActivityEvent;
import com.example.tripbuddyv2.Models.Item;
import com.example.tripbuddyv2.Models.Lodging;
import com.example.tripbuddyv2.Models.Transportation;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.Trip;
import com.example.tripbuddyv2.TripAdapter;

import java.util.List;

public class TripAdapterV2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Trip> items;
    private TripAdapter.OnItemClickListener listener;

    public TripAdapterV2(List<Trip> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0){
            return  new ActivityViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_activity,parent,false
                    )
            );
        }else if (viewType == 1){
            return  new LodgingViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_lodging,parent,false
                    )
            );

        }else{
            return  new TransportationViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_trainsportation,parent,false
                    )
            );

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Trip currentTrip = items.get(position);
//        holder.textViewLodgingTitle.setText(currentTrip.getLodgingTitle());
//        holder.textTextViewLodgingCheckInDateTime.setText(currentTrip.getLodgingCheckInDateTime());
//        holder.textTextViewLodgingCheckOutDateTime.setText(currentTrip.getLodgingCheckOutDateTime());
//        holder.textViewLodgingAddress.setText(currentTrip.getLodgingAddress());

        if (getItemViewType(position)== 0) {
            ((ActivityViewHolder) holder).textActivityTitle.setText(currentTrip.getActivityTitle());
            ((ActivityViewHolder) holder).textActivityDescription.setText(currentTrip.getActivityDescription());
        }else if (getItemViewType(position) == 1) {
            ((LodgingViewHolder) holder).textLodgingTitle.setText(currentTrip.getLodgingTitle());
            ((LodgingViewHolder) holder).textLodgingDescription.setText(currentTrip.getLodgingDescription());
        }else {
            ((TransportationViewHolder) holder).textTransportationTitle.setText(currentTrip.getAirplaneArrivalDateTime());
            ((TransportationViewHolder) holder).textTransportationDescription.setText(currentTrip.getAirplaneDescription());
        }

//        if (getItemViewType(position)== 0) {
//            ActivityEvent activity = (ActivityEvent) items.get(position).getObject();
//            ((ActivityViewHolder) holder).setActivityItemData(activity);
//        }else if (getItemViewType(position) == 1) {
//            Lodging lodging = (Lodging) items.get(position).getObject();
//            ((LodgingViewHolder) holder).setLodgingItemData(lodging);
//        }else {
//            Transportation transportation = (Transportation) items.get(position).getObject();
//            ((TransportationViewHolder) holder).setTransportationItemData(transportation);
//        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setTrips(List<Trip> trips) {
        this.items = trips;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Trip trip);

    }
    public void setOnItemClickListener(TripAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    static class ActivityViewHolder extends RecyclerView.ViewHolder{

        private TextView textActivityTitle,textActivityDescription;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            textActivityTitle = itemView.findViewById(R.id.text_view_activity_title);
            textActivityDescription = itemView.findViewById(R.id.text_view_activity_description);
        }

        void setActivityItemData(ActivityEvent activity){
            textActivityTitle.setText(activity.getActivityTitle());
            textActivityDescription.setText(activity.getActivityDescription());

        }
    }
    static class LodgingViewHolder extends RecyclerView.ViewHolder{

        private TextView textLodgingTitle,textLodgingDescription;

        public LodgingViewHolder(@NonNull View itemView) {
            super(itemView);
            textLodgingTitle = itemView.findViewById(R.id.text_view_lodging_title);
            textLodgingDescription = itemView.findViewById(R.id.text_view_lodging_description);
        }

        void setLodgingItemData(Lodging lodging){
            textLodgingTitle.setText(lodging.getLodgingTitle());
            textLodgingDescription.setText(lodging.getLodgingDescription());

        }
    }
    static class TransportationViewHolder extends RecyclerView.ViewHolder{

        private TextView textTransportationTitle,textTransportationDescription;

        public TransportationViewHolder(@NonNull View itemView) {
            super(itemView);
            textTransportationTitle = itemView.findViewById(R.id.text_view_transportation_title);
            textTransportationDescription = itemView.findViewById(R.id.text_view_transportation_description);
        }

        void setTransportationItemData(Transportation transportation){
            textTransportationTitle.setText(transportation.getTransportationTitle());
            textTransportationDescription.setText(transportation.getTransportationDescription());

        }
    }
}
