package com.example.tripbuddyv2.Models;

public class Lodging {
    private String lodgingTitle,lodgingDescription;

    public Lodging(String lodgingTitle, String lodgingDescription) {
        this.lodgingTitle = lodgingTitle;
        this.lodgingDescription = lodgingDescription;
    }

    public String getLodgingTitle() {
        return lodgingTitle;
    }

    public String getLodgingDescription() {
        return lodgingDescription;
    }
}
