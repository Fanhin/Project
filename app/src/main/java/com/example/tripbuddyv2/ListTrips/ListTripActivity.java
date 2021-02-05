package com.example.tripbuddyv2.ListTrips;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripbuddyv2.R;

import java.util.List;

public class ListTripActivity extends AppCompatActivity {
    private ListTripsViewModel listTripsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_list_trips);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ListTripsAdapter listTripsAdapter = new ListTripsAdapter();
        recyclerView.setAdapter(listTripsAdapter);

        listTripsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ListTripsViewModel.class);
        listTripsViewModel.getAllListTrips().observe(this, new Observer<List<ListTrips>>() {
            @Override
            public void onChanged(List<ListTrips> listTrips) {
                //update recyclerview
                listTripsAdapter.setListTrips(listTrips);
                Toast.makeText(ListTripActivity.this, "OnChange", Toast.LENGTH_SHORT).show();

            }
        });
    }
    
}
