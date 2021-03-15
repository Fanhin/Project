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

import java.util.ArrayList;
import java.util.List;

public class ExpenseListTripAdapter extends RecyclerView.Adapter<ExpenseListTripAdapter.ExpenseListTripsHolder> {
    private List<ListTrips> listTrips = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ExpenseListTripsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_expense_item,parent,false);
        return new ExpenseListTripsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseListTripsHolder holder, int position) {
        ListTrips currentListTrips = listTrips.get(position);
        holder.textViewTitle.setText(currentListTrips.getTitle());

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


    class ExpenseListTripsHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;


        public ExpenseListTripsHolder(View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.expense_trip_title);


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

    public void setOnItemClickListener(ExpenseListTripAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
