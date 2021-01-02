package com.example.tripbuddyv2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripRepository repository;
    private LiveData<List<Trip>> allTrips;

    public TripViewModel(@NonNull Application application) {
        super(application);

        repository = new TripRepository(application);
        allTrips = repository.getAllTrip();
    }

    public void insert(Trip trip){
        repository.insert(trip);
    }

    public void  update(Trip trip){
        repository.update(trip);
    }

    public void delete(Trip trip){
        repository.delete(trip);
    }

    public void deleteAllTrips(){
        repository.deleteAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return allTrips;
    }
}
