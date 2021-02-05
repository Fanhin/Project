package com.example.tripbuddyv2.ListTrips;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListTripsViewModel extends AndroidViewModel {

    private ListTripsRepository repository;
    private LiveData<List<ListTrips>> allListTrips;

    public ListTripsViewModel(@NonNull Application application) {
        super(application);
        repository = new ListTripsRepository(application);
        allListTrips = repository.getAllListTrips();
    }

    public void insert(ListTrips listTrips){
        repository.insert(listTrips);
    }

    public void update(ListTrips listTrips){
        repository.update(listTrips);
    }

    public void delete(ListTrips listTrips){
        repository.delete(listTrips);
    }
    public void deleteAllListTrips(){
        repository.deleteAllListTrips();
    }

    public LiveData<List<ListTrips>> getAllListTrips(){
        return allListTrips;
    }
}
