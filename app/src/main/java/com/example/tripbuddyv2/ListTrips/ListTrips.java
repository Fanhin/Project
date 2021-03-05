package com.example.tripbuddyv2.ListTrips;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.tripbuddyv2.Trip;

@Entity(tableName = "list_trips_table")
@ForeignKey(entity = Trip.class,parentColumns = "idTripsList",childColumns = "id")

public class ListTrips {
    @PrimaryKey(autoGenerate = true)
    private int idListTrips;


    private String title;
    private String startDate;
    private String endDate;
    private String description;

    public ListTrips(String title, String startDate,String endDate,String description) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getIdListTrips() {
        return idListTrips;
    }

    public void setIdListTrips(int idTripsList) {
        this.idListTrips = idTripsList;
    }

    public int getId() {
        return idListTrips;
    }

    public void setId(int idTripsList) {
        this.idListTrips = idTripsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
