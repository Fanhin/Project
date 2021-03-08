package com.example.tripbuddyv2.ListTrips;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.tripbuddyv2.Trip;

import java.util.List;
@Dao
public interface ListTripsDao {

    @Transaction
    @Insert
    long insertListTrips(ListTrips listTrips);

    @Update
    void update(ListTrips listTrips);

    @Delete
    void delete(ListTrips listTrips);

    @Query("DELETE FROM list_trips_table")
    void deleteAllListTrips();


    @Query("SELECT * FROM list_trips_table  ORDER BY idListTrips ASC")
    LiveData<List<ListTrips>> getAllListTrips();

    @Query("SELECT * FROM trip_table WHERE id_fkListTrips = :id LIMIT 1")
    LiveData<List<Trip>> getTripsByIdFK(long id);


}
