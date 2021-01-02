package com.example.tripbuddyv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripHolder> {
    private List<Trip> trips = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);

        return new TripHolder(itemView);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull TripHolder holder, int position) {
        Trip currentTrip = trips.get(position);
        holder.textViewLodgingTitle.setText(currentTrip.getLodgingTitle());
        holder.textTextViewLodgingCheckInDateTime.setText(currentTrip.getLodgingCheckInDateTime());
        holder.textTextViewLodgingCheckOutDateTime.setText(currentTrip.getLodgingCheckOutDateTime());
        holder.textViewLodgingAddress.setText(currentTrip.getLodgingAddress());

    }


    @Override
    public int getItemCount() {
        return trips.size();
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
        notifyDataSetChanged();
    }

    public Trip getTripAt(int position) {
        return trips.get(position);
    }


    class TripHolder extends RecyclerView.ViewHolder {
        //lodging
        private TextView textViewLodgingTitle;
        private TextView textTextViewLodgingCheckInDateTime;
        private TextView textTextViewLodgingCheckOutDateTime;
        private TextView textViewLodgingAddress;
        private TextView textViewLodgingPhone;
        private TextView textViewLodgingWebsite;
        private TextView textViewLodgingEmail;

        private final Context context;


        public TripHolder(@NonNull View itemView) {
            super(itemView);
            context  = itemView.getContext();
            textViewLodgingTitle = itemView.findViewById(R.id.text_view_title);
            textTextViewLodgingCheckInDateTime = itemView.findViewById(R.id.text_view_start_date_time);
            textTextViewLodgingCheckOutDateTime = itemView.findViewById(R.id.text_view_end_date_time);
            textViewLodgingAddress = itemView.findViewById(R.id.text_view_description);

            //textViewLodgingPhone = itemView.findViewById(R.id.text_view_title);
            //textViewLodgingWebsite = itemView.findViewById(R.id.text_view_title);
            //textViewLodgingEmail = itemView.findViewById(R.id.text_view_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(trips.get(position));
                    }


                }
            });


        }
    }

    public interface OnItemClickListener {
        void onItemClick(Trip trip);


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}


