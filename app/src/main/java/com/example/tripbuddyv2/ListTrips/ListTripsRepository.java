package com.example.tripbuddyv2.ListTrips;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tripbuddyv2.Trip;
import com.example.tripbuddyv2.TripDao;
import com.example.tripbuddyv2.TripDatabase;
import com.example.tripbuddyv2.TripRepository;

import java.util.List;

public class ListTripsRepository {

    private ListTripsDao listTripsDao;
    private LiveData<List<ListTrips>> allListTrips;

    public ListTripsRepository(Application application){
        TripDatabase database = TripDatabase.getInstance(application);
        listTripsDao = database.listTripsDao();
        allListTrips = listTripsDao.getAllListTrips();

    }

    public void insert(ListTripsWithTrip listTripsWithTrip){

        new InsertTripAsyncTask(listTripsDao).execute(listTripsWithTrip);
    }

    public void update(ListTrips listTrips){

        new ListTripsRepository.UpdateTripAsyncTask(listTripsDao).execute(listTrips);
    }

    public void delete(ListTrips listTrips){

        new ListTripsRepository.DeleteTripAsyncTask(listTripsDao).execute(listTrips);
    }

    public void deleteAllTrips( ){

        new ListTripsRepository.DeleteAllTripAsyncTask(listTripsDao).execute();
    }

    public LiveData<List<ListTrips>>getAllListTrips( ){

        return allListTrips;
    }


    private static class InsertTripAsyncTask extends AsyncTask<ListTripsWithTrip,Void,Void> {
        private ListTripsDao listTripsDao;

        private InsertTripAsyncTask(ListTripsDao listTripsDao){

            this.listTripsDao = listTripsDao;
        }

        @Override
        protected Void doInBackground(ListTripsWithTrip... listTripsWithTrips) {
            long identifier = listTripsDao.insertListTrips(listTripsWithTrips[0].listTrips);

            for (Trip trip : listTripsWithTrips[0].trips){
                trip.setId_fkListTrips(identifier);
            }
            return null;
        }
    }

    private static class UpdateTripAsyncTask extends AsyncTask<ListTrips,Void,Void>{
        private ListTripsDao listTripsDao;

        private UpdateTripAsyncTask(ListTripsDao listTripsDao){

            this.listTripsDao = listTripsDao;
        }

        @Override
        protected Void doInBackground(ListTrips... listTrips) {
            listTripsDao.update(listTrips[0]);
            return null;
        }
    }

    private static class DeleteTripAsyncTask extends AsyncTask<ListTrips,Void,Void>{
        private ListTripsDao listTripsDao;

        private DeleteTripAsyncTask(ListTripsDao listTripsDao){

            this.listTripsDao = listTripsDao;
        }

        @Override
        protected Void doInBackground(ListTrips... listTrips) {
            listTripsDao.delete(listTrips[0]);
            return null;
        }
    }

    private static class DeleteAllTripAsyncTask extends AsyncTask<Void,Void,Void>{
        private ListTripsDao listTripsDao;

        private DeleteAllTripAsyncTask(ListTripsDao listTripsDao){

            this.listTripsDao = listTripsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            listTripsDao.deleteAllListTrips();
            return null;
        }
    }

}
