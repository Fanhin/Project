package com.example.tripbuddyv2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Trip.class,version = 19 ,exportSchema = false)
@TypeConverters({UriConverter.class})
public abstract class TripDatabase extends RoomDatabase  {

    private static TripDatabase instance;

    public abstract TripDao tripDao();

    public static synchronized TripDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TripDatabase.class,"trip_database")
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

        private PopulateDbAsyncTask(TripDatabase db) {

            tripDao = db.tripDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }


}
