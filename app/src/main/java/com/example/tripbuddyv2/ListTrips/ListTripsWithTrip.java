package com.example.tripbuddyv2.ListTrips;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.tripbuddyv2.Trip;

import java.util.List;

public class ListTripsWithTrip {

    @Embedded
    public ListTrips listTrips;
    @Relation(
            parentColumn = "id_ListTrips",
            entityColumn = "id_fkListTrips"
    )
    public List<Trip> trips;

    public ListTripsWithTrip(ListTrips listTrips, List<Trip> trips) {
        this.listTrips = listTrips;
        this.trips = trips;
    }
}
