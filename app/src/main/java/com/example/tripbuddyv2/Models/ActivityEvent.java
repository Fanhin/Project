package com.example.tripbuddyv2.Models;

public class ActivityEvent {

    private String activityTitle,activityDescription;

    public ActivityEvent(String activityTitle, String activityDescription) {
        this.activityTitle = activityTitle;
        this.activityDescription = activityDescription;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public String getActivityDescription() {
        return activityDescription;
    }
}
