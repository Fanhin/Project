package com.example.tripbuddyv2.Expense;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripbuddyv2.ListTrips.ListTrips;
import com.example.tripbuddyv2.ListTrips.ListTripsAdapter;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.Trip;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDetailAdapter extends RecyclerView.Adapter<ExpenseDetailAdapter.ExpenseDetailHolder> {
    private List<Trip> trips = new ArrayList<>();
    private ListTripsAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public ExpenseDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_expense_detail_trips_item,parent,false);
        return new ExpenseDetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseDetailHolder holder, int position) {
        Trip currentTrips = trips.get(position);


    }


    public Trip getListTripsAt(int position) {
        return trips.get(position);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public void setListTrips(List<Trip> trips){
        this.trips = trips;
        notifyDataSetChanged();
    }

    class ExpenseDetailHolder extends RecyclerView.ViewHolder{
        private TextView textViewActivityTitleExpense;
        private TextView textViewActivityExpense;

        public ExpenseDetailHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
