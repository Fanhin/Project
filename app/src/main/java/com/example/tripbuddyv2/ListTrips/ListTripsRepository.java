package com.example.tripbuddyv2.ListTrips;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tripbuddyv2.TripDatabase;

import java.util.List;

public class ListTripsRepository {
    private ListTripsDao listTripsDao;
    private LiveData<List<ListTrips>> allListTrips;

    public ListTripsRepository(Application application){
        TripDatabase database = TripDatabase.getInstance(application);
        listTripsDao = database.listTripsDao();
        allListTrips = listTripsDao.getAllListTrips();
    }

    public void insert(ListTrips listTrips){
        new InsertListTripsAsyncTask(listTripsDao).execute(listTrips);

    }
    public void update(ListTrips listTrips){
        new UpdateListTripsAsyncTask(listTripsDao).execute(listTrips);

    }
    public void delete(ListTrips listTrips){
        new DeleteListTripsAsyncTask(listTripsDao).execute(listTrips);

    }
    public void deleteAllListTrips( ){
        new DeleteAllListTripsAsyncTask(listTripsDao).execute();
    }

    public LiveData<List<ListTrips>> getAllListTrips() {
        return allListTrips;
    }

    private static class InsertListTripsAsyncTask extends AsyncTask<ListTrips, Void, Void> {
        private ListTripsDao listTripsDao;
        private InsertListTripsAsyncTask(ListTripsDao listTripsDao) {
            this.listTripsDao = listTripsDao;
        }
        @Override
        protected Void doInBackground(ListTrips... listTrips) {
            listTripsDao.insert(listTrips[0]);
            return null;
        }
    }
    private static class UpdateListTripsAsyncTask extends AsyncTask<ListTrips, Void, Void> {
        private ListTripsDao listTripsDao;
        private UpdateListTripsAsyncTask(ListTripsDao listTripsDao) {
            this.listTripsDao = listTripsDao;
        }
        @Override
        protected Void doInBackground(ListTrips... listTrips) {
            listTripsDao.update(listTrips[0]);
            return null;
        }
    }
    private static class DeleteListTripsAsyncTask extends AsyncTask<ListTrips, Void, Void> {
        private ListTripsDao listTripsDao;
        private DeleteListTripsAsyncTask(ListTripsDao listTripsDao) {
            this.listTripsDao = listTripsDao;
        }
        @Override
        protected Void doInBackground(ListTrips... listTrips) {
            listTripsDao.delete(listTrips[0]);
            return null;
        }
    }
    private static class DeleteAllListTripsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ListTripsDao listTripsDao;
        private DeleteAllListTripsAsyncTask(ListTripsDao listTripsDao) {
            this.listTripsDao = listTripsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            listTripsDao.deleteAllListTrips();
            return null;
        }
    }
}
