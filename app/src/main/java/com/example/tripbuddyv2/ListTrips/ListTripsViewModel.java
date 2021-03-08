package com.example.tripbuddyv2.ListTrips;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripbuddyv2.TripDatabase;

import java.util.List;

public class ListTripsViewModel extends AndroidViewModel {

    private ListTripsRepository listTripsRepository;
    private LiveData<List<ListTrips>> allListTrips;

    private ListTripsDao listTripsDao;


    public ListTripsViewModel(@NonNull Application application) {
        super(application);

        listTripsRepository = new ListTripsRepository(application);
        listTripsDao = TripDatabase.getInstance(application).listTripsDao();
        allListTrips = listTripsDao.getAllListTrips();


    }

    public void insertListTrip(ListTrips listTrips){
        listTripsRepository.insert(listTrips);
    }


    public void delete(ListTrips listTrips){
        listTripsRepository.delete(listTrips);
    }




    public LiveData<List<ListTrips>> getAllListTrips() {
        return allListTrips;
    }


    public void getTripsWithIdFK(long idFk) {
        listTripsRepository.getTripsWithIdFK(idFk);
    }
}