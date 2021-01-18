package com.example.tripbuddyv2.ModelsV2;

public class LodgingObj {

    private String lodgingTitle;
    private String lodgingCheckInDateTime;
    private String lodgingCheckOutDateTime;
    private String lodgingDescription;
    private String lodgingAddress;
    private String lodgingPhone;
    private String lodgingWebsite;
    private String lodgingEmail;
    private String lodgingImage;

    public LodgingObj(String lodgingTitle, String lodgingCheckInDateTime, String lodgingCheckOutDateTime, String lodgingDescription, String lodgingAddress, String lodgingPhone, String lodgingWebsite, String lodgingEmail, String lodgingImage) {
        this.lodgingTitle = lodgingTitle;
        this.lodgingCheckInDateTime = lodgingCheckInDateTime;
        this.lodgingCheckOutDateTime = lodgingCheckOutDateTime;
        this.lodgingDescription = lodgingDescription;
        this.lodgingAddress = lodgingAddress;
        this.lodgingPhone = lodgingPhone;
        this.lodgingWebsite = lodgingWebsite;
        this.lodgingEmail = lodgingEmail;
        this.lodgingImage = lodgingImage;
    }

    public String getLodgingTitle() {
        return lodgingTitle;
    }

    public void setLodgingTitle(String lodgingTitle) {
        this.lodgingTitle = lodgingTitle;
    }

    public String getLodgingCheckInDateTime() {
        return lodgingCheckInDateTime;
    }

    public void setLodgingCheckInDateTime(String lodgingCheckInDateTime) {
        this.lodgingCheckInDateTime = lodgingCheckInDateTime;
    }

    public String getLodgingCheckOutDateTime() {
        return lodgingCheckOutDateTime;
    }

    public void setLodgingCheckOutDateTime(String lodgingCheckOutDateTime) {
        this.lodgingCheckOutDateTime = lodgingCheckOutDateTime;
    }

    public String getLodgingDescription() {
        return lodgingDescription;
    }

    public void setLodgingDescription(String lodgingDescription) {
        this.lodgingDescription = lodgingDescription;
    }

    public String getLodgingAddress() {
        return lodgingAddress;
    }

    public void setLodgingAddress(String lodgingAddress) {
        this.lodgingAddress = lodgingAddress;
    }

    public String getLodgingPhone() {
        return lodgingPhone;
    }

    public void setLodgingPhone(String lodgingPhone) {
        this.lodgingPhone = lodgingPhone;
    }

    public String getLodgingWebsite() {
        return lodgingWebsite;
    }

    public void setLodgingWebsite(String lodgingWebsite) {
        this.lodgingWebsite = lodgingWebsite;
    }

    public String getLodgingEmail() {
        return lodgingEmail;
    }

    public void setLodgingEmail(String lodgingEmail) {
        this.lodgingEmail = lodgingEmail;
    }

    public String getLodgingImage() {
        return lodgingImage;
    }

    public void setLodgingImage(String lodgingImage) {
        this.lodgingImage = lodgingImage;
    }
}
