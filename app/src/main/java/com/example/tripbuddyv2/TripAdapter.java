package com.example.tripbuddyv2;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import com.example.tripbuddyv2.Adapter.TripAdapterV2;
import com.example.tripbuddyv2.Models.ActivityEvent;
import com.example.tripbuddyv2.Models.Item;
import com.example.tripbuddyv2.Models.Lodging;
import com.example.tripbuddyv2.Models.Transportation;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripHolder> {

    private static int TYPE_LODGING = 1;
    private static int TYPE_ACTIVITY = 2;
    private static int TYPE_AIRPLANE_TRANSPORTATION = 3;
    private static int TYPE_TRAIN_TRANSPORTATION = 4;
    private static int TYPE_BUS_TRANSPORTATION = 5;
    private static int TYPE_BOAT_TRANSPORTATION = 6;
    private static int TYPE_CAR_RENTAL_TRANSPORTATION = 7;

    private List<Trip> trips = new ArrayList<>();
    private OnItemClickListener listener;
    private int type;

    public TripAdapter(int type) {
        this.type = type;
    }

    public TripAdapter() {
        super();

    }


    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;

        if (viewType == TYPE_LODGING) {

            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);

        } else if (viewType == TYPE_ACTIVITY) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        } else if (viewType == TYPE_AIRPLANE_TRANSPORTATION) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.airplane_item, parent, false);
        } else if (viewType == TYPE_TRAIN_TRANSPORTATION) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item, parent, false);
        } else if (viewType == TYPE_BUS_TRANSPORTATION) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item, parent, false);
        } else if (viewType == TYPE_BOAT_TRANSPORTATION) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.boat_item, parent, false);
        } else if (viewType == TYPE_CAR_RENTAL_TRANSPORTATION) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_rental_item, parent, false);
        }
        return new TripHolder(itemView);

    }


    @Override
    public void onBindViewHolder(@NonNull TripHolder holder, int position) {
        //get data to item view
        Trip currentTrip = trips.get(position);

        if (getItemViewType(position) == TYPE_LODGING) {//lodging
            holder.textViewLodgingTitle.setText(currentTrip.getLodgingTitle());
            holder.textTextViewLodgingCheckInDateTime.setText(currentTrip.getLodgingCheckInDateTime());
            holder.textTextViewLodgingCheckOutDateTime.setText(currentTrip.getLodgingCheckOutDateTime());
            holder.textViewLodgingAddress.setText(currentTrip.getLodgingAddress());
        } else if (getItemViewType(position) == TYPE_ACTIVITY) {//activity
            holder.textViewActivityTitle.setText(currentTrip.getActivityTitle());
            holder.textViewActivityStartDateTime.setText(currentTrip.getActivityStartDateTime());
            holder.textViewActivityEndDateTime.setText(currentTrip.getActivityEndDateTime());
            holder.textViewActivityDescription.setText(currentTrip.getActivityDescription());
            holder.textViewActivityPriority.setText(String.valueOf(currentTrip.getActivityPriority()));

        } else if (getItemViewType(position) == TYPE_AIRPLANE_TRANSPORTATION) {//airplane
            holder.textViewAirplaneTitle.setText(currentTrip.getAirplaneDescription());
            holder.textViewAirplaneDepartureDateTime.setText(currentTrip.getAirplaneDepartureDateTime());
            holder.textViewAirplaneArrivalDateTime.setText(currentTrip.getAirplaneArrivalDateTime());
            holder.textViewAirplaneAirline.setText(currentTrip.getDepartureAirplaneAirline());

        } else if (getItemViewType(position) == TYPE_TRAIN_TRANSPORTATION) {//train
            holder.textViewTrainTitle.setText(currentTrip.getDepartureTrainStation());
            holder.textViewTrainDepartureDateTime.setText(currentTrip.getTrainDepartureDateTime());
            holder.textViewTrainArrivalDateTime.setText(currentTrip.getTrainArrivalDateTime());
            holder.textViewTrainDepartureStation.setText(currentTrip.getDepartureTrainAddress());

        } else if (getItemViewType(position) == TYPE_BUS_TRANSPORTATION) {//bus
            holder.textViewBusTitle.setText(currentTrip.getBusLicensePlate());
            holder.textViewBusDepartureDateTime.setText(currentTrip.getBusDepartureDateTime());
            holder.textViewBusArrivalDateTime.setText(currentTrip.getBusArrivalDateTime());
            holder.textViewBusDepartureStation.setText(currentTrip.getDepartureBusAddress());

        } else if (getItemViewType(position) == TYPE_BOAT_TRANSPORTATION) {//boat
            holder.textViewBoatTitle.setText(currentTrip.getBoatName());
            holder.textViewBoatDepartureDateTime.setText(currentTrip.getBoatDepartureDateTime());
            holder.textViewBoatArrivalDateTime.setText(currentTrip.getBoatArrivalDateTime());
            holder.textViewBoatDepartureStation.setText(currentTrip.getDepartureBoatLocation());

        } else if (getItemViewType(position) == TYPE_CAR_RENTAL_TRANSPORTATION) {//boat
            holder.textViewCarRentalTitle.setText(currentTrip.getRentalAgency());
            holder.textViewCarRentalDepartureDateTime.setText(currentTrip.getPickupDateTime());
            holder.textViewCarRentalArrivalDateTime.setText(currentTrip.getDropOffDateTime());
            holder.textViewCarRentalDescription.setText(currentTrip.getCarRentalDescription());

        }


    }


    class TripHolder extends RecyclerView.ViewHolder {
        //lodging
        private TextView textViewLodgingTitle;
        private TextView textTextViewLodgingCheckInDateTime;
        private TextView textTextViewLodgingCheckOutDateTime;
        private TextView textViewLodgingAddress;

        private TextView textViewActivityTitle;
        private TextView textViewActivityStartDateTime;
        private TextView textViewActivityEndDateTime;
        private TextView textViewActivityDescription;
        private TextView textViewActivityPriority;

        private TextView textViewAirplaneTitle;
        private TextView textViewAirplaneDepartureDateTime;
        private TextView textViewAirplaneArrivalDateTime;
        private TextView textViewAirplaneAirline;

        private TextView textViewTrainTitle; //station
        private TextView textViewTrainDepartureDateTime;
        private TextView textViewTrainArrivalDateTime;
        private TextView textViewTrainDepartureStation; //address

        private TextView textViewBusTitle; //Bus license Plate
        private TextView textViewBusDepartureDateTime;
        private TextView textViewBusArrivalDateTime;
        private TextView textViewBusDepartureStation; //address

        private TextView textViewBoatTitle; // Name
        private TextView textViewBoatDepartureDateTime;
        private TextView textViewBoatArrivalDateTime;
        private TextView textViewBoatDepartureStation; //Location

        private TextView textViewCarRentalTitle; //station
        private TextView textViewCarRentalDepartureDateTime;
        private TextView textViewCarRentalArrivalDateTime;
        private TextView textViewCarRentalDescription; //description


        private final Context context;


        public TripHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            textViewLodgingTitle = itemView.findViewById(R.id.text_view_title);
            textTextViewLodgingCheckInDateTime = itemView.findViewById(R.id.text_view_start_date_time);
            textTextViewLodgingCheckOutDateTime = itemView.findViewById(R.id.text_view_end_date_time);
            textViewLodgingAddress = itemView.findViewById(R.id.text_view_description);

            textViewActivityTitle = itemView.findViewById(R.id.text_view_title_activity_item);
            textViewActivityStartDateTime = itemView.findViewById(R.id.text_view_start_date_time_activity_item);
            textViewActivityEndDateTime = itemView.findViewById(R.id.text_view_end_date_time_activity_item);
            textViewActivityDescription = itemView.findViewById(R.id.text_view_description_activity_item);
            textViewActivityPriority = itemView.findViewById(R.id.text_view_activity_priority);

            textViewAirplaneTitle = itemView.findViewById(R.id.text_view_title_airplane_item);
            textViewAirplaneDepartureDateTime = itemView.findViewById(R.id.text_view_departure_date_time_airplane_item);
            textViewAirplaneArrivalDateTime = itemView.findViewById(R.id.text_view_arrival_date_time_airplane_item);
            textViewAirplaneAirline = itemView.findViewById(R.id.text_view_airline_airplane_item);

            textViewTrainTitle = itemView.findViewById(R.id.text_view_title_train_item);
            textViewTrainDepartureDateTime = itemView.findViewById(R.id.text_view_departure_date_time_train_item);
            textViewTrainArrivalDateTime = itemView.findViewById(R.id.text_view_arrival_date_time_train_item);
            textViewTrainDepartureStation = itemView.findViewById(R.id.text_view_station_train_item);

            textViewBusTitle = itemView.findViewById(R.id.text_view_title_bus_item);
            textViewBusDepartureDateTime = itemView.findViewById(R.id.text_view_departure_date_time_bus_item);
            textViewBusArrivalDateTime = itemView.findViewById(R.id.text_view_arrival_date_time_bus_item);
            textViewBusDepartureStation = itemView.findViewById(R.id.text_view_station_bus_item);

            textViewBoatTitle = itemView.findViewById(R.id.text_view_title_boat_item);
            textViewBoatDepartureDateTime = itemView.findViewById(R.id.text_view_departure_date_time_boat_item);
            textViewBoatArrivalDateTime = itemView.findViewById(R.id.text_view_arrival_date_time_boat_item);
            textViewBoatDepartureStation = itemView.findViewById(R.id.text_view_location_boat_item);

            textViewCarRentalTitle = itemView.findViewById(R.id.text_view_title_car_rental_item);
            textViewCarRentalDepartureDateTime = itemView.findViewById(R.id.text_view_departure_date_time_car_rental_item);
            textViewCarRentalArrivalDateTime = itemView.findViewById(R.id.text_view_arrival_date_time_car_rental_item);
            textViewCarRentalDescription = itemView.findViewById(R.id.text_view_description_car_rental_item);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(trips.get(position), getItemViewType());
                    }
                }
            });


        }
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

    @Override
    public int getItemViewType(int position) {
        // return trips.get(position).getType();
        if (trips.get(position).getType() == 0) {
            return TYPE_LODGING;

        } else if (trips.get(position).getType() == 1) {
            return TYPE_ACTIVITY;
        } else if (trips.get(position).getType() == 2) {
            return TYPE_AIRPLANE_TRANSPORTATION;
        } else if (trips.get(position).getType() == 3) {
            return TYPE_TRAIN_TRANSPORTATION;
        } else if (trips.get(position).getType() == 4) {
            return TYPE_BUS_TRANSPORTATION;
        } else if (trips.get(position).getType() == 5) {
            return TYPE_BOAT_TRANSPORTATION;
        } else {
            return TYPE_CAR_RENTAL_TRANSPORTATION;


        }
    }


    //start from here


    public interface OnItemClickListener {
        void onItemClick(Trip trip, int type);


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}


