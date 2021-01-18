package com.example.tripbuddyv2.Models;

public class Transportation {
    private String transportationTitle,transportationDescription;

    public Transportation(String transportationTitle, String transportationDescription) {
        this.transportationTitle = transportationTitle;
        this.transportationDescription = transportationDescription;
    }

    public String getTransportationTitle() {
        return transportationTitle;
    }

    public String getTransportationDescription() {
        return transportationDescription;
    }
}
