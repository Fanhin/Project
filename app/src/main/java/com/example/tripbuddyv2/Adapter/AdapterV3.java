package com.example.tripbuddyv2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.Trip;
import com.example.tripbuddyv2.TripAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterV3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Trip> trips = new ArrayList<>();
    private OnItemClickListener listener;


    public AdapterV3(List<Trip> trips) {
        this.trips = trips;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0){
            return  new TripAdapterV2.ActivityViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.lodging_item,parent,false
                    )
            );
        }else {
            return  new TripAdapterV2.ActivityViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.activity_item,parent,false
                    )
            );

        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Trip currentTrip = trips.get(position);

        if (currentTrip.getType()== 0){
            ((LodgingHolder) holder).textViewLodgingTitle.setText(currentTrip.getLodgingTitle());
            ((LodgingHolder) holder).textTextViewLodgingCheckInDateTime.setText(currentTrip.getLodgingCheckInDateTime());
            ((LodgingHolder) holder).textTextViewLodgingCheckOutDateTime.setText(currentTrip.getLodgingCheckOutDateTime());
            ((LodgingHolder) holder).textViewLodgingDescription.setText(currentTrip.getLodgingDescription());

        }else {
            ((ActivityHolder) holder).textViewActivityTitle.setText(currentTrip.getActivityTitle());
            ((ActivityHolder) holder).textTextViewActivityStartDateTime.setText(currentTrip.getActivityStartDateTime());
            ((ActivityHolder) holder).textTextViewActivityEndDateTime.setText(currentTrip.getActivityEndDateTime());
            ((ActivityHolder) holder).textViewActivityDescription.setText(currentTrip.getActivityDescription());

        }



//        if (currentTrip.getType() == 0) {
//            ((LodgingHolder) holder).textViewLodgingTitle.setText(currentTrip.getLodgingTitle());
//            ((LodgingHolder) holder).textTextViewLodgingCheckInDateTime.setText(currentTrip.getLodgingCheckInDateTime());
//            ((LodgingHolder) holder).textTextViewLodgingCheckOutDateTime.setText(currentTrip.getLodgingCheckOutDateTime());
//            ((LodgingHolder) holder).textViewLodgingDescription.setText(currentTrip.getLodgingDescription());
//        }else{
//            ((ActivityHolder) holder).textViewActivityTitle.setText(currentTrip.getActivityTitle());
//            ((ActivityHolder) holder).textTextViewActivityStartDateTime.setText(currentTrip.getActivityStartDateTime());
//            ((ActivityHolder) holder).textTextViewActivityEndDateTime.setText(currentTrip.getActivityEndDateTime());
//            ((ActivityHolder) holder).textViewActivityDescription.setText(currentTrip.getActivityDescription());
//
//        }

    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    @Override
    public int getItemViewType(int position) {
        return trips.get(position).getType();
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
        notifyDataSetChanged();
    }

    public Trip getTripAt(int position) {
        return trips.get(position);
    }


    class LodgingHolder extends RecyclerView.ViewHolder {
        //lodging
        private TextView textViewLodgingTitle;
        private TextView textTextViewLodgingCheckInDateTime;
        private TextView textTextViewLodgingCheckOutDateTime;
        private TextView textViewLodgingDescription;


        private final Context context;


        public LodgingHolder(@NonNull View itemView) {
            super(itemView);
            context  = itemView.getContext();
            textViewLodgingTitle = itemView.findViewById(R.id.text_view_title_lodging_item);
            textTextViewLodgingCheckInDateTime = itemView.findViewById(R.id.text_view_start_date_time_lodging_item);
            textTextViewLodgingCheckOutDateTime = itemView.findViewById(R.id.text_view_end_date_time_lodging_item);
            textViewLodgingDescription = itemView.findViewById(R.id.text_view_description_lodging_item);

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

    class ActivityHolder extends RecyclerView.ViewHolder {

        //lodging
        private TextView textViewActivityTitle;
        private TextView textTextViewActivityStartDateTime;
        private TextView textTextViewActivityEndDateTime;
        private TextView textViewActivityDescription;


        private final Context context;


        public ActivityHolder(@NonNull View itemView) {
            super(itemView);
            context  = itemView.getContext();
            textViewActivityTitle = itemView.findViewById(R.id.text_view_title_activity_item);
            textTextViewActivityStartDateTime = itemView.findViewById(R.id.text_view_start_date_time_activity_item);
            textTextViewActivityEndDateTime = itemView.findViewById(R.id.text_view_end_date_time_activity_item);
            textViewActivityDescription = itemView.findViewById(R.id.text_view_description_activity_item);

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
