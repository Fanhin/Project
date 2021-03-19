package com.example.tripbuddyv2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tripbuddyv2.ListTrips.ListTrips;
import com.example.tripbuddyv2.ListTrips.ListTripsDao;

@Database(entities = {Trip.class, ListTrips.class},version = 42 )
@TypeConverters({UriConverter.class})
public abstract class TripDatabase extends RoomDatabase  {

    private static TripDatabase instance;

    public abstract TripDao tripDao();
    public abstract ListTripsDao listTripsDao();


    public static synchronized TripDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TripDatabase.class,"trip_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private  static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,Void>{
        private TripDao tripDao;
        private ListTripsDao listTripsDao;


        private PopulateDbAsyncTask(TripDatabase db) {

            tripDao = db.tripDao();
            listTripsDao = db.listTripsDao();

        }


        @Override
        protected Void doInBackground(Void... voids) {

//            ListTrips listTrips1 = new ListTrips("JapanTrip","2012-11-23 05:00","2012-12-1 13:00","Japan Itinerary");
////            listTripsDao.insertListTrips(listTrips1);





            return null;
        }
    }


}
