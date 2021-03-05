package com.example.tripbuddyv2;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.tripbuddyv2.ListTrips.ListTrips;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "trip_table")
public class Trip {

    @PrimaryKey(autoGenerate = true)
    private int idTrip;

    @ForeignKey(
            entity = ListTrips.class,
            parentColumns = "id_ListTrips",
            childColumns = "id_fkListTrips",
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
    )
    private long id_fkListTrips;

    public long getId_fkListTrips() {
        return id_fkListTrips;
    }

    public void setId_fkListTrips(long id_fkListTrips) {
        this.id_fkListTrips = id_fkListTrips;
    }

    //airplane transportation
    private String airplaneDepartureDateTime;
    private String departureAirplaneAirline;
    private String departureAirplaneFlightNumber;
    private String departureAirplaneSeats;
    private String departureAirplaneTerminal;
    private String departureAirplaneGate;
    private String airplaneArrivalDateTime;
    private String arrivalArrivingCityOrAirport;
    private String arrivalTerminal;
    private String arrivalGate;
    private String airplaneDescription;

    //constructor for airplane

    public Trip(int type,String airplaneDepartureDateTime, String departureAirplaneAirline, String departureAirplaneFlightNumber,
                String departureAirplaneSeats, String departureAirplaneTerminal, String departureAirplaneGate,
                String airplaneArrivalDateTime, String arrivalArrivingCityOrAirport, String arrivalTerminal,
                String arrivalGate,String airplaneDescription) {
        this.type = type;
        this.airplaneDepartureDateTime = airplaneDepartureDateTime;
        this.departureAirplaneAirline = departureAirplaneAirline;
        this.departureAirplaneFlightNumber = departureAirplaneFlightNumber;
        this.departureAirplaneSeats = departureAirplaneSeats;
        this.departureAirplaneTerminal = departureAirplaneTerminal;
        this.departureAirplaneGate = departureAirplaneGate;
        this.airplaneArrivalDateTime = airplaneArrivalDateTime;
        this.arrivalArrivingCityOrAirport = arrivalArrivingCityOrAirport;
        this.arrivalTerminal = arrivalTerminal;
        this.arrivalGate = arrivalGate;
        this.airplaneDescription = airplaneDescription;


    }


    //train transportation
    private String trainDepartureDateTime;
    private String departureTrainStation;
    private String departureTrainAddress;
    private String trainArrivalDateTime;
    private String trainArrivalStation;
    private String trainType;
    private String trainNumber;
    private String trainCoach;
    private String trainClass;
    private String trainSeats;

    //constructor for train

    public Trip(int type,String trainDepartureDateTime, String departureTrainStation, String departureTrainAddress, String trainArrivalDateTime,String trainArrivalStation,
                String trainType, String trainNumber,String trainCoach, String trainClass, String trainSeats) {
        this.type = type;
        this.trainDepartureDateTime = trainDepartureDateTime;
        this.departureTrainStation = departureTrainStation;
        this.departureTrainAddress = departureTrainAddress;
        this.trainArrivalDateTime = trainArrivalDateTime;
        this.trainArrivalStation = trainArrivalStation;
        this.trainType = trainType;
        this.trainNumber = trainNumber;
        this.trainCoach = trainCoach;
        this.trainClass = trainClass;
        this.trainSeats = trainSeats;
    }

    //Bus transportation
    private String busDepartureDateTime;
    private String busLicensePlate;
    private String departureBusAddress;
    private String busArrivalDateTime;
    private String busArrivalAddress;

    //constructor for Bus

    public Trip(int type,String busDepartureDateTime, String busLicensePlate, String departureBusAddress, String busArrivalDateTime, String busArrivalAddress) {
        this.type = type;
        this.busDepartureDateTime = busDepartureDateTime;
        this.busLicensePlate = busLicensePlate;
        this.departureBusAddress = departureBusAddress;
        this.busArrivalDateTime = busArrivalDateTime;
        this.busArrivalAddress = busArrivalAddress;
    }

    //Boat transportation
    private String boatDepartureDateTime;
    private String boatName;
    private String departureBoatLocation;
    private String departureBoatAddress;
    private String boatArrivalDateTime;
    private String arrivalBoatLocation;
    private String arrivalBoatAddress;
    private String portName;
    private String portAddress;
    private String cabinType;
    private String cabinNumber;
    private String boatDescription;
    private String boatPhone;

    public Trip(int type,String boatDepartureDateTime, String boatName, String departureBoatLocation, String departureBoatAddress,
                String boatArrivalDateTime, String arrivalBoatLocation, String arrivalBoatAddress, String portName, String portAddress,
                String cabinType, String cabinNumber, String boatDescription ,String boatPhone) {
        this.type = type;
        this.boatDepartureDateTime = boatDepartureDateTime;
        this.boatName = boatName;
        this.departureBoatLocation = departureBoatLocation;
        this.departureBoatAddress = departureBoatAddress;
        this.boatArrivalDateTime = boatArrivalDateTime;
        this.arrivalBoatLocation = arrivalBoatLocation;
        this.arrivalBoatAddress = arrivalBoatAddress;
        this.portName = portName;
        this.portAddress = portAddress;
        this.cabinType = cabinType;
        this.cabinNumber = cabinNumber;
        this.boatDescription = boatDescription;
        this.boatPhone = boatPhone;
    }

    //Car rental transportation
    private String rentalAgency;
    private String pickupDateTime;
    private String pickupLocation;
    private String carRentalPickupAddress;
    private String carRentalPhone;
    private String dropOffDateTime;
    private String dropOffLocation;
    private String dropOffAddress;
    private String carRentalWebsite;
    private String carRentalEmail;
    private String carRentalDescription;
    private String carRentalConfirmation;


    public Trip(int type,String rentalAgency, String pickupDateTime, String pickupLocation, String carRentalPickupAddress,
                String carRentalPhone, String dropOffDateTime, String dropOffLocation, String dropOffAddress,
                String carRentalWebsite, String carRentalEmail,String carRentalDescription,String carRentalConfirmation) {
        this.type = type;
        this.rentalAgency = rentalAgency;
        this.pickupDateTime = pickupDateTime;
        this.pickupLocation = pickupLocation;
        this.carRentalPickupAddress = carRentalPickupAddress;
        this.carRentalPhone = carRentalPhone;
        this.dropOffDateTime = dropOffDateTime;
        this.dropOffLocation = dropOffLocation;
        this.dropOffAddress = dropOffAddress;
        this.carRentalWebsite = carRentalWebsite;
        this.carRentalEmail = carRentalEmail;
        this.carRentalDescription = carRentalDescription;
        this.carRentalConfirmation = carRentalConfirmation;
    }

    //lodging 9*
    private String lodgingTitle;
    private String lodgingCheckInDateTime;
    private String lodgingCheckOutDateTime;
    private String lodgingDescription;
    private String lodgingAddress;
    private String lodgingPhone;
    private String lodgingWebsite;
    private String lodgingEmail;
    private String lodgingImage1;
    private String lodgingImage2;
    private String lodgingImage3;
    private ArrayList<String> uriLodgingPath;

    public ArrayList<String> getUriLodgingPath() {
        return uriLodgingPath;
    }

    public void setUriLodgingPath(ArrayList<String> uriLodgingPath) {
        this.uriLodgingPath = uriLodgingPath;
    }
    //constructor for lodging


    public Trip(int type, String lodgingTitle, String lodgingCheckInDateTime, String lodgingCheckOutDateTime,
                String lodgingDescription, String lodgingAddress, String lodgingPhone, String lodgingWebsite, String lodgingEmail
            , String lodgingImage1, String lodgingImage2, String lodgingImage3, ArrayList<String> uriLodgingPath) {
        this.type = type;
        this.lodgingTitle = lodgingTitle;
        this.lodgingCheckInDateTime = lodgingCheckInDateTime;
        this.lodgingCheckOutDateTime = lodgingCheckOutDateTime;
        this.lodgingDescription = lodgingDescription;
        this.lodgingAddress = lodgingAddress;
        this.lodgingPhone = lodgingPhone;
        this.lodgingWebsite = lodgingWebsite;
        this.lodgingEmail = lodgingEmail;
        this.lodgingImage1 = lodgingImage1;
        this.lodgingImage2 = lodgingImage2;
        this.lodgingImage3 = lodgingImage3;
        this.uriLodgingPath = uriLodgingPath;
    }

    //activity
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
    private  String activityImage1;
    private  String activityImage2;
    private  String activityImage3;
    private ArrayList<String> uriActivityPath;

    public ArrayList<String> getUriActivityPath() {
        return uriActivityPath;
    }

    public void setUriActivityPath(ArrayList<String> uriActivityPath) {
        this.uriActivityPath = uriActivityPath;
    }
    //constructor for activity

    public Trip(int type,String activityTitle, String activityDestination, String activityDescription,
                String activityStartDateTime, String activityEndDateTime, String activityAddress,
                String activityPhone, String activityWebsite, String activityEmail, int activityPriority
            ,String activityImage1,String activityImage2,String activityImage3,ArrayList<String> uriActivityPath) {
        this.type = type;
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
        this.activityImage1 = activityImage1;
        this.activityImage2 = activityImage2;
        this.activityImage3 = activityImage3;
        this.uriActivityPath = uriActivityPath;
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Trip() {

    }

    public void setIdTrip(int id) {
        this.idTrip = id;
    }

    public int getIdTrip() {
        return idTrip;
    }


    public String getAirplaneDepartureDateTime() {
        return airplaneDepartureDateTime;
    }

    public void setAirplaneDepartureDateTime(String airplaneDepartureDateTime) {
        this.airplaneDepartureDateTime = airplaneDepartureDateTime;
    }

    public String getDepartureAirplaneAirline() {
        return departureAirplaneAirline;
    }

    public void setDepartureAirplaneAirline(String departureAirplaneAirline) {
        this.departureAirplaneAirline = departureAirplaneAirline;
    }

    public String getDepartureAirplaneFlightNumber() {
        return departureAirplaneFlightNumber;
    }

    public void setDepartureAirplaneFlightNumber(String departureAirplaneFlightNumber) {
        this.departureAirplaneFlightNumber = departureAirplaneFlightNumber;
    }

    public String getDepartureAirplaneSeats() {
        return departureAirplaneSeats;
    }

    public void setDepartureAirplaneSeats(String departureAirplaneSeats) {
        this.departureAirplaneSeats = departureAirplaneSeats;
    }

    public String getDepartureAirplaneTerminal() {
        return departureAirplaneTerminal;
    }

    public void setDepartureAirplaneTerminal(String departureAirplaneTerminal) {
        this.departureAirplaneTerminal = departureAirplaneTerminal;
    }

    public String getDepartureAirplaneGate() {
        return departureAirplaneGate;
    }

    public void setDepartureAirplaneGate(String departureAirplaneGate) {
        this.departureAirplaneGate = departureAirplaneGate;
    }

    public String getAirplaneArrivalDateTime() {
        return airplaneArrivalDateTime;
    }

    public void setAirplaneArrivalDateTime(String airplaneArrivalDateTime) {
        this.airplaneArrivalDateTime = airplaneArrivalDateTime;
    }

    public String getArrivalArrivingCityOrAirport() {
        return arrivalArrivingCityOrAirport;
    }

    public void setArrivalArrivingCityOrAirport(String arrivalArrivingCityOrAirport) {
        this.arrivalArrivingCityOrAirport = arrivalArrivingCityOrAirport;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public String getArrivalGate() {
        return arrivalGate;
    }

    public void setArrivalGate(String arrivalGate) {
        this.arrivalGate = arrivalGate;
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

    public String getAirplaneDescription() {
        return airplaneDescription;
    }

    public void setAirplaneDescription(String airplaneDescription) {
        this.airplaneDescription = airplaneDescription;
    }

    public String getTrainDepartureDateTime() {
        return trainDepartureDateTime;
    }

    public void setTrainDepartureDateTime(String trainDepartureDateTime) {
        this.trainDepartureDateTime = trainDepartureDateTime;
    }

    public String getDepartureTrainStation() {
        return departureTrainStation;
    }

    public void setDepartureTrainStation(String departureTrainStation) {
        this.departureTrainStation = departureTrainStation;
    }

    public String getDepartureTrainAddress() {
        return departureTrainAddress;
    }

    public void setDepartureTrainAddress(String departureTrainAddress) {
        this.departureTrainAddress = departureTrainAddress;
    }

    public String getTrainArrivalDateTime() {
        return trainArrivalDateTime;
    }

    public void setTrainArrivalDateTime(String trainArrivalDateTime) {
        this.trainArrivalDateTime = trainArrivalDateTime;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainClass() {
        return trainClass;
    }

    public void setTrainClass(String trainClass) {
        this.trainClass = trainClass;
    }

    public String getTrainSeats() {
        return trainSeats;
    }

    public void setTrainSeats(String trainSeats) {
        this.trainSeats = trainSeats;
    }

    public String getBusDepartureDateTime() {
        return busDepartureDateTime;
    }

    public void setBusDepartureDateTime(String busDepartureDateTime) {
        this.busDepartureDateTime = busDepartureDateTime;
    }

    public String getBusLicensePlate() {
        return busLicensePlate;
    }

    public void setBusLicensePlate(String busLicensePlate) {
        this.busLicensePlate = busLicensePlate;
    }

    public String getDepartureBusAddress() {
        return departureBusAddress;
    }

    public void setDepartureBusAddress(String departureBusAddress) {
        this.departureBusAddress = departureBusAddress;
    }

    public String getBusArrivalDateTime() {
        return busArrivalDateTime;
    }

    public void setBusArrivalDateTime(String busArrivalDateTime) {
        this.busArrivalDateTime = busArrivalDateTime;
    }

    public String getBusArrivalAddress() {
        return busArrivalAddress;
    }

    public void setBusArrivalAddress(String busArrivalAddress) {
        this.busArrivalAddress = busArrivalAddress;
    }

    public String getBoatDepartureDateTime() {
        return boatDepartureDateTime;
    }

    public void setBoatDepartureDateTime(String boatDepartureDateTime) {
        this.boatDepartureDateTime = boatDepartureDateTime;
    }

    public String getBoatName() {
        return boatName;
    }

    public void setBoatName(String boatName) {
        this.boatName = boatName;
    }

    public String getDepartureBoatLocation() {
        return departureBoatLocation;
    }

    public void setDepartureBoatLocation(String departureBoatLocation) {
        this.departureBoatLocation = departureBoatLocation;
    }

    public String getDepartureBoatAddress() {
        return departureBoatAddress;
    }

    public void setDepartureBoatAddress(String departureBoatAddress) {
        this.departureBoatAddress = departureBoatAddress;
    }

    public String getBoatArrivalDateTime() {
        return boatArrivalDateTime;
    }

    public void setBoatArrivalDateTime(String boatArrivalDateTime) {
        this.boatArrivalDateTime = boatArrivalDateTime;
    }

    public String getArrivalBoatLocation() {
        return arrivalBoatLocation;
    }

    public void setArrivalBoatLocation(String arrivalBoatLocation) {
        this.arrivalBoatLocation = arrivalBoatLocation;
    }

    public String getArrivalBoatAddress() {
        return arrivalBoatAddress;
    }

    public void setArrivalBoatAddress(String arrivalBoatAddress) {
        this.arrivalBoatAddress = arrivalBoatAddress;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPortAddress() {
        return portAddress;
    }

    public void setPortAddress(String portAddress) {
        this.portAddress = portAddress;
    }

    public String getCabinType() {
        return cabinType;
    }

    public void setCabinType(String cabinType) {
        this.cabinType = cabinType;
    }

    public String getCabinNumber() {
        return cabinNumber;
    }

    public void setCabinNumber(String cabinNumber) {
        this.cabinNumber = cabinNumber;
    }

    public String getBoatDescription() {
        return boatDescription;
    }

    public void setBoatDescription(String boatDescription) {
        this.boatDescription = boatDescription;
    }

    public String getRentalAgency() {
        return rentalAgency;
    }

    public void setRentalAgency(String rentalAgency) {
        this.rentalAgency = rentalAgency;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getCarRentalPickupAddress() {
        return carRentalPickupAddress;
    }

    public void setCarRentalPickupAddress(String carRentalPickupAddress) {
        this.carRentalPickupAddress = carRentalPickupAddress;
    }

    public String getCarRentalPhone() {
        return carRentalPhone;
    }

    public void setCarRentalPhone(String carRentalPhone) {
        this.carRentalPhone = carRentalPhone;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(String dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public String getCarRentalDropOffAddress() {
        return dropOffAddress;
    }

    public void setCarRentalDropOffAddress(String carRentalDropOffAddress) {
        this.dropOffAddress = carRentalDropOffAddress;
    }

    public String getCarRentalWebsite() {
        return carRentalWebsite;
    }

    public void setCarRentalWebsite(String carRentalWebsite) {
        this.carRentalWebsite = carRentalWebsite;
    }

    public String getCarRentalEmail() {
        return carRentalEmail;
    }

    public void setCarRentalEmail(String carRentalEmail) {
        this.carRentalEmail = carRentalEmail;
    }

    public String getCarRentalDescription() {
        return carRentalDescription;
    }

    public void setCarRentalDescription(String carRentalDescription) {
        this.carRentalDescription = carRentalDescription;
    }

    public String getLodgingDescription() {
        return lodgingDescription;
    }

    public void setLodgingDescription(String lodgingDescription) {
        this.lodgingDescription = lodgingDescription;
    }

    public String getTrainArrivalStation() {
        return trainArrivalStation;
    }

    public void setTrainArrivalStation(String trainArrivalStation) {
        this.trainArrivalStation = trainArrivalStation;
    }

    public String getDropOffAddress() {
        return dropOffAddress;
    }

    public void setDropOffAddress(String dropOffAddress) {
        this.dropOffAddress = dropOffAddress;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getTrainCoach() {
        return trainCoach;
    }

    public void setTrainCoach(String trainCoach) {
        this.trainCoach = trainCoach;
    }

    public String getBoatPhone() {
        return boatPhone;
    }

    public void setBoatPhone(String boatPhone) {
        this.boatPhone = boatPhone;
    }

    public String getCarRentalConfirmation() {
        return carRentalConfirmation;
    }

    public void setCarRentalConfirmation(String carRentalConfirmation) {
        this.carRentalConfirmation = carRentalConfirmation;
    }

    public String getLodgingImage1() {
        return lodgingImage1;
    }

    public void setLodgingImage1(String lodgingImage1) {
        this.lodgingImage1 = lodgingImage1;
    }


    public String getLodgingImage2() {
        return lodgingImage2;
    }

    public void setLodgingImage2(String lodgingImage2) {
        this.lodgingImage2 = lodgingImage2;
    }

    public String getLodgingImage3() {
        return lodgingImage3;
    }

    public void setLodgingImage3(String lodgingImage3) {
        this.lodgingImage3 = lodgingImage3;
    }

    public String getActivityImage1() {
        return activityImage1;
    }

    public void setActivityImage1(String activityImage1) {
        this.activityImage1 = activityImage1;
    }

    public String getActivityImage2() {
        return activityImage2;
    }

    public void setActivityImage2(String activityImage2) {
        this.activityImage2 = activityImage2;
    }

    public String getActivityImage3() {
        return activityImage3;
    }

    public void setActivityImage3(String activityImage3) {
        this.activityImage3 = activityImage3;
    }
}
