package com.example.tripbuddyv2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tripbuddyv2.Tab.AirplaneFragment;
import com.example.tripbuddyv2.Tab.BoatFragment;
import com.example.tripbuddyv2.Tab.BusFragment;
import com.example.tripbuddyv2.Tab.CarRentalFragment;
import com.example.tripbuddyv2.Tab.TrainFragment;

public class TransportationSpinner extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //airplane tag
    public static final String EXTRA_AirplaneDepartureDateTime = "com.example.tripbuddyv2.EXTRA_AirplaneDepartureDateTime";
    public static final String EXTRA_DepartureAirplaneAirline = "com.example.tripbuddyv2.EXTRA_DepartureAirplaneAirline";
    public static final String EXTRA_DepartureAirplaneFlightNumber = "com.example.tripbuddyv2.EXTRA_DepartureAirplaneFlightNumber";
    public static final String EXTRA_DepartureAirplaneSeats = "com.example.tripbuddyv2.EXTRA_DepartureAirplaneSeats";
    public static final String EXTRA_DepartureAirplaneTerminal = "com.example.tripbuddyv2.EXTRA_DepartureAirplaneTerminal";
    public static final String EXTRA_DepartureAirplaneGate = "com.example.tripbuddyv2.EXTRA_DepartureAirplaneGate";
    public static final String EXTRA_AirplaneArrivalDateTime = "com.example.tripbuddyv2.EXTRA_AirplaneArrivalDateTime";
    public static final String EXTRA_ArrivalArrivingCityOrAirport = "com.example.tripbuddyv2.EXTRA_ArrivalArrivingCityOrAirport";
    public static final String EXTRA_ArrivalTerminal = "com.example.tripbuddyv2.EXTRA_ArrivalTerminal";
    public static final String EXTRA_ArrivalGate = "com.example.tripbuddyv2.EXTRA_ArrivalGate";
    public static final String EXTRA_AirplaneDescription = "com.example.tripbuddyv2.EXTRA_AirplaneDescription";

    //train tag
    public static final  String EXTRA_TrainDepartureDateTime="com.example.tripbuddyv2.EXTRA_TrainDepartureDateTime";
    public static final  String EXTRA_DepartureTrainStation="com.example.tripbuddyv2.EXTRA_DepartureTrainStation";
    public static final  String EXTRA_DepartureTrainAddress="com.example.tripbuddyv2.EXTRA_DepartureTrainAddress";
    public static final  String EXTRA_TrainArrivalDateTime="com.example.tripbuddyv2.EXTRA_TrainArrivalDateTime";
    public static final  String EXTRA_TrainArrivalStation="com.example.tripbuddyv2.EXTRA_TrainArrivalStation";
    public static final  String EXTRA_TrainType="com.example.tripbuddyv2.EXTRA_TrainType";
    public static final  String EXTRA_TrainNumber="com.example.tripbuddyv2.EXTRA_TrainNumber";
    public static final  String EXTRA_TrainClass="com.example.tripbuddyv2.EXTRA_TrainClass";
    public static final  String EXTRA_TrainSeats="com.example.tripbuddyv2.EXTRA_TrainSeats";
    //bus tag
    public static final  String EXTRA_BusDepartureDateTime="com.example.tripbuddyv2.EXTRA_BusDepartureDateTime";
    public static final  String  EXTRA_BusLicensePlate="com.example.tripbuddyv2.EXTRA_BusLicensePlate";
    public static final  String  EXTRA_DepartureBusAddress="com.example.tripbuddyv2.EXTRA_DepartureBusAddress";
    public static final  String  EXTRA_BusArrivalDateTime="com.example.tripbuddyv2.EXTRA_BusArrivalDateTime";
    public static final  String  EXTRA_BusArrivalAddress="com.example.tripbuddyv2.EXTRA_BusArrivalAddress";
    //boat tag
    public static final  String EXTRA_BoatDepartureDateTime="com.example.tripbuddyv2.EXTRA_BoatDepartureDateTime";
    public static final  String EXTRA_BoatName="com.example.tripbuddyv2.EXTRA_BoatName";
    public static final  String EXTRA_DepartureBoatLocation="com.example.tripbuddyv2.EXTRA_DepartureBoatLocation";
    public static final  String EXTRA_DepartureBoatAddress="com.example.tripbuddyv2.EXTRA_DepartureBoatAddress";
    public static final  String EXTRA_BoatArrivalDateTime="com.example.tripbuddyv2.EXTRA_BoatArrivalDateTime";
    public static final  String EXTRA_ArrivalBoatLocation="com.example.tripbuddyv2.EXTRA_ArrivalBoatLocation";
    public static final  String EXTRA_ArrivalBoatAddress="com.example.tripbuddyv2.EXTRA_ArrivalBoatAddress";
    public static final  String EXTRA_PortName="com.example.tripbuddyv2.EXTRA_PortName";
    public static final  String EXTRA_PortAddress="com.example.tripbuddyv2.EXTRA_PortAddress";
    public static final  String EXTRA_CabinType="com.example.tripbuddyv2.EXTRA_CabinType";
    public static final  String EXTRA_CabinNumber="com.example.tripbuddyv2.EXTRA_CabinNumber";
    public static final  String EXTRA_BoatDescription="com.example.tripbuddyv2.EXTRA_BoatDescription";
    //car rental tag
    public static final  String EXTRA_RentalAgency="com.example.tripbuddyv2.EXTRA_RentalAgency";
    public static final  String EXTRA_PickupDateTime="com.example.tripbuddyv2.EXTRA_PickupDateTime";
    public static final  String EXTRA_PickupLocation="com.example.tripbuddyv2.EXTRA_PickupLocation";
    public static final  String EXTRA_CarRentalPickupAddress="com.example.tripbuddyv2.EXTRA_CarRentalPickupAddress";
    public static final  String EXTRA_CarRentalPhone="com.example.tripbuddyv2.EXTRA_CarRentalPhone";
    public static final  String EXTRA_DropOffDateTime="com.example.tripbuddyv2.EXTRA_DropOffDateTime";
    public static final  String EXTRA_DropOffLocation="com.example.tripbuddyv2.EXTRA_DropOffLocation";
    public static final  String EXTRA_DropOffAddress="com.example.tripbuddyv2.EXTRA_DropOffAddress";
    public static final  String EXTRA_CarRentalWebsite="com.example.tripbuddyv2.EXTRA_CarRentalWebsite";
    public static final  String EXTRA_CarRentalEmail="com.example.tripbuddyv2.EXTRA_CarRentalEmail";
    public static final  String EXTRA_CarRentalDescription="com.example.tripbuddyv2.EXTRA_CarRentalDescription";

    AirplaneFragment airplaneFragment;
    TrainFragment trainFragment;
    BusFragment busFragment;
    BoatFragment boatFragment;
    CarRentalFragment carRentalFragment;

    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation_spinner);

        spinner = findViewById(R.id.spinner);
        airplaneFragment = new AirplaneFragment();
        trainFragment = new TrainFragment();
        busFragment = new BusFragment();
        boatFragment = new BoatFragment();
        carRentalFragment = new CarRentalFragment();



        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.transportation_menu,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                selectFragment(airplaneFragment);
                break;
            case  1:
                selectFragment(trainFragment);
                break;
            case 2:
                selectFragment(busFragment);
                break;
            case 3:
                selectFragment(boatFragment);
                break;
            case 4:
                selectFragment(carRentalFragment);
                break;

        }

    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }







}
