package com.example.tripbuddyv2.ListTrips;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tripbuddyv2.Trip;
import com.example.tripbuddyv2.TripDao;
import com.example.tripbuddyv2.TripDatabase;

import java.util.List;

public class ListTripsRepository {

    static ListTripsRepository instance;
    private ListTripsDao listTripsDao;
    private TripDao tripDao;
    private LiveData<List<ListTrips>> allListTrips;
    private LiveData<List<Trip>> allTrips;



    public ListTripsRepository(Application application){
        TripDatabase database = TripDatabase.getInstance(application);
        listTripsDao = database.listTripsDao();
        allListTrips = listTripsDao.getAllListTrips();



    }

    public void insert(ListTrips listTrips){

        new InsertListTripAsyncTask(listTripsDao).execute(listTrips);
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

    public LiveData<List<Trip>> getTripsWithIdFK(long idFk) {
        return listTripsDao.getTripsByIdFK(idFk);
    }





    private static class InsertListTripAsyncTask extends AsyncTask<ListTrips,Void,Void> {
        private ListTripsDao listTripsDao;

        private InsertListTripAsyncTask(ListTripsDao listTripsDao){

            this.listTripsDao = listTripsDao;
        }

        @Override
        protected Void doInBackground(ListTrips... listTrips) {

            listTripsDao.insertListTrips(listTrips[0]);
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
