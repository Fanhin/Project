package com.example.tripbuddyv2.ListTrips;

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

public class ListTripsAdapter extends RecyclerView.Adapter<ListTripsAdapter.ListTripsHolder> {
    private List<ListTrips> listTrips = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ListTripsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_trips_item,parent,false);
        return new ListTripsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTripsHolder holder, int position) {
        ListTrips currentListTrips = listTrips.get(position);
        holder.textViewTitle.setText(currentListTrips.getTitle());
        holder.textViewStartDate.setText(currentListTrips.getStartDate());
        holder.textViewEndDate.setText(currentListTrips.getEndDate());
        holder.textViewDescription.setText(currentListTrips.getDescription());

    }
    public ListTrips getListTripsAt(int position) {
        return listTrips.get(position);
    }





    @Override
    public int getItemCount() {
        return listTrips.size();
    }

    public void setListTrips(List<ListTrips> listTrips){
        this.listTrips = listTrips;
        notifyDataSetChanged();
    }

    class ListTripsHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewStartDate;
        private TextView textViewEndDate;
        private TextView textViewDescription;

        public ListTripsHolder(View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewStartDate = itemView.findViewById(R.id.text_view_start_date);
            textViewEndDate = itemView.findViewById(R.id.text_view_end_date);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(listTrips.get(position));
                    }
                }
            });
        }



    }

    public interface OnItemClickListener {
        void onItemClick(ListTrips listTrips);


    }

    public void setOnItemClickListener(ListTripsAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}
