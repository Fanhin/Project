package com.example.tripbuddyv2.ListTrips;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "list_trips_table")
public class ListTrips {

    @PrimaryKey(autoGenerate = true)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
