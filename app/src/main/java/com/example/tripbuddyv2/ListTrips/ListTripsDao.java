package com.example.tripbuddyv2.ListTrips;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListTripsDao {
    @Insert
    void insert(ListTrips listTrips);

    @Update
    void update(ListTrips listTrips);

    @Delete
    void delete(ListTrips listTrips);

    @Query("DELETE FROM list_trips_table")
    void deleteAllListTrips();

    @Query("SELECT * FROM list_trips_table ORDER BY startDate DESC")
    LiveData<List<ListTrips>> getAllListTrips();
}
