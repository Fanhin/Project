package com.example.tripbuddyv2.ListTrips;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripbuddyv2.Trip;
import com.example.tripbuddyv2.TripRepository;

import java.util.List;

public class ListTripsViewModel extends AndroidViewModel {

    private ListTripsRepository listTripsRepository;
    private LiveData<List<ListTrips>> allListTrips;

    public ListTripsViewModel(@NonNull Application application) {
        super(application);

        listTripsRepository = new ListTripsRepository(application);
        allListTrips = listTripsRepository.getAllListTrips();
    }

    public void insertListTripWithTrip(ListTripsWithTrip listTripsWithTrip){
        listTripsRepository.insert(listTripsWithTrip);
    }

    public void  update(ListTrips listTrips){
        listTripsRepository.update(listTrips);
    }

    public void delete(ListTrips listTrips){
        listTripsRepository.delete(listTrips);
    }

    public void deleteAllTrips(){
        listTripsRepository.deleteAllTrips();
    }

    public LiveData<List<ListTrips>> getAllListTrips() {
        return allListTrips;
    }
}
