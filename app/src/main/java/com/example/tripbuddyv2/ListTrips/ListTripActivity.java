package com.example.tripbuddyv2.ListTrips;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripbuddyv2.BottomNav.LodgingEditActivity;
import com.example.tripbuddyv2.MainActivity;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.Trip;
import com.example.tripbuddyv2.TripAdapter;
import com.example.tripbuddyv2.TripDatabase;
import com.example.tripbuddyv2.TripViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListTripActivity extends AppCompatActivity implements DialogAddTripsList.DialogAddTripsListListener {
    private ListTripsViewModel listTripsViewModel;
    private List<Trip> trips = new ArrayList<>();


    public static final int LISTTRIP = 22;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips);
        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_trip_list);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTripsDialog();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_list_trips);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ListTripsAdapter listTripsAdapter = new ListTripsAdapter();
        recyclerView.setAdapter(listTripsAdapter);


        listTripsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ListTripsViewModel.class);
        listTripsViewModel.getAllListTrips().observe(this, new Observer<List<ListTrips>>() {
            @Override
            public void onChanged(List<ListTrips> listTrips) {

                listTripsAdapter.setListTrips(listTrips);
                Toast.makeText(ListTripActivity.this, "OnChange", Toast.LENGTH_SHORT).show();

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(viewHolder.itemView.getContext())
                        .setMessage("Are you sure ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listTripsViewModel.delete(listTripsAdapter.getListTripsAt(viewHolder.getAdapterPosition()));
                                Toast.makeText(ListTripActivity.this, "Trip deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listTripsAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                            }
                        })
                        .create()
                        .show();

                //tripViewModel.delete(adapter.getTripAt(viewHolder.getAdapterPosition()));


            }
        }).attachToRecyclerView(recyclerView);




        listTripsAdapter.setOnItemClickListener(new ListTripsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListTrips listTrips) {
                Intent intentTripTimeline = new Intent(ListTripActivity.this, MainActivity.class);
                intentTripTimeline.putExtra("idListTrip",listTrips.getIdListTrips());
                Log.e("Id List Trip",String.valueOf(listTrips.getIdListTrips()));
                startActivityForResult(intentTripTimeline, LISTTRIP);
            }

        });
    }





    private void openAddTripsDialog() {
        DialogAddTripsList dialogAddTripsList =  new DialogAddTripsList();
        dialogAddTripsList.show(getSupportFragmentManager(),"Add trips Dialog");
    }

    @Override
    public void applyTexts(String tripsName, String tripsStartDate, String tripsEndDate, String tripsDescription) {

        ListTrips listTrips = new ListTrips(tripsName,tripsStartDate,tripsEndDate,tripsDescription);
        //ListTripsWithTrip listTripsWithTrip = new ListTripsWithTrip(new ListTrips(tripsName,tripsStartDate,tripsEndDate,tripsDescription),trips);
        //listTripsViewModel.insertListTripWithTrip(listTripsWithTrip);

        listTripsViewModel.insertListTrip(listTrips);
       

    }


}
