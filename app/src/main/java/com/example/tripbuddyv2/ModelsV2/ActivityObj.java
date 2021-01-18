package com.example.tripbuddyv2.ModelsV2;

public class ActivityObj {

    private String activityTitle;
    private String activityDestination;
    private String activityDescription;
    private String activityStartDateTime;
    private String activityEndDateTime;
    private String activityAddress;
    private String activityPhone;
    private String activityWebsite;
    private String activityEmail;
    private int activityPriority;
    private  String activityImage;

    public ActivityObj(String activityTitle, String activityDestination, String activityDescription, String activityStartDateTime, String activityEndDateTime, String activityAddress, String activityPhone, String activityWebsite, String activityEmail, int activityPriority, String activityImage) {
        this.activityTitle = activityTitle;
        this.activityDestination = activityDestination;
        this.activityDescription = activityDescription;
        this.activityStartDateTime = activityStartDateTime;
        this.activityEndDateTime = activityEndDateTime;
        this.activityAddress = activityAddress;
        this.activityPhone = activityPhone;
        this.activityWebsite = activityWebsite;
        this.activityEmail = activityEmail;
        this.activityPriority = activityPriority;
        this.activityImage = activityImage;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityDestination() {
        return activityDestination;
    }

    public void setActivityDestination(String activityDestination) {
        this.activityDestination = activityDestination;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getActivityStartDateTime() {
        return activityStartDateTime;
    }

    public void setActivityStartDateTime(String activityStartDateTime) {
        this.activityStartDateTime = activityStartDateTime;
    }

    public String getActivityEndDateTime() {
        return activityEndDateTime;
    }

    public void setActivityEndDateTime(String activityEndDateTime) {
        this.activityEndDateTime = activityEndDateTime;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityPhone() {
        return activityPhone;
    }

    public void setActivityPhone(String activityPhone) {
        this.activityPhone = activityPhone;
    }

    public String getActivityWebsite() {
        return activityWebsite;
    }

    public void setActivityWebsite(String activityWebsite) {
        this.activityWebsite = activityWebsite;
    }

    public String getActivityEmail() {
        return activityEmail;
    }

    public void setActivityEmail(String activityEmail) {
        this.activityEmail = activityEmail;
    }

    public int getActivityPriority() {
        return activityPriority;
    }

    public void setActivityPriority(int activityPriority) {
        this.activityPriority = activityPriority;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }
}
