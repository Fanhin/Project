package com.example.tripbuddyv2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.tripbuddyv2.ListTrips.ListTrips;

import java.util.List;

@Dao
public interface TripDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Trip trip);

    @Update
    void update(Trip trip);

    @Delete
    void delete(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAllTrips();

    @Query("SELECT * FROM trip_table  ORDER BY COALESCE(activityStartDateTime,lodgingCheckInDateTime,airplaneDepartureDateTime,trainDepartureDateTime,busDepartureDateTime,boatDepartureDateTime,pickupDateTime)  ASC")
    LiveData<List<Trip>> getAllTrips();

    @Query("SELECT * FROM trip_table WHERE id_fkListTrips=:idFk ORDER BY COALESCE(activityStartDateTime,lodgingCheckInDateTime,airplaneDepartureDateTime,trainDepartureDateTime,busDepartureDateTime,boatDepartureDateTime,pickupDateTime)  ASC")
    LiveData<List<Trip>> getTripWithIdFK(long idFk);

    @Query("SELECT SUM(expense)  AS expenseTotal FROM trip_table WHERE id_fkListTrips = :idFk ")
    long getTotalExpenseWithIdFk(long idFk);



}
