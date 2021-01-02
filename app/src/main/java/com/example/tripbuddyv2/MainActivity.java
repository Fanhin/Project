package com.example.tripbuddyv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tripbuddyv2.BottomNav.ActivityActivity;
import com.example.tripbuddyv2.BottomNav.LodgingEditActivity;
import com.example.tripbuddyv2.BottomNav.TransportationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 11;
    public static final int ADD_TRANSPORTATION = 1;
    public static final int ADD_TRANSPORTATION_FRAGMENT = 12;
    public static final int ADD_LODGING = 2;
    public static final int EDIT_LODGING = 22;
    public static final int ADD_ACTIVITY = 3;

    private TripViewModel tripViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton testBtn =findViewById(R.id.testBtn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, TransportationSpinner.class);
                startActivityForResult(intent1, ADD_TRANSPORTATION_FRAGMENT);

            }
        });


        FloatingActionButton buttonAddTransportation = findViewById(R.id.button_add_transportation);
        buttonAddTransportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(MainActivity.this, TransportationActivity.class);
                startActivityForResult(intent1, ADD_TRANSPORTATION);

            }
        });

        FloatingActionButton buttonAddLodging = findViewById(R.id.button_add_lodging);
        buttonAddLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, LodgingEditActivity.class);
                startActivityForResult(intent1, ADD_LODGING);

            }
        });

        FloatingActionButton buttonAddActivity = findViewById(R.id.button_add_activity);
        buttonAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, ActivityActivity.class);
                startActivityForResult(intent1, ADD_ACTIVITY);

            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final TripAdapter adapter = new TripAdapter();
        recyclerView.setAdapter(adapter);
        tripViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TripViewModel.class);
        tripViewModel.getAllTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable List<Trip> trips) {
                adapter.setTrips(trips);
            }
        });


        //slide to delete one trip
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tripViewModel.delete(adapter.getTripAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Trip deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TripAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Trip trip) {
                Intent intent = new Intent(MainActivity.this, LodgingEditActivity.class);


                intent.putExtra(LodgingEditActivity.EXTRA_LODGING_ID, trip.getId());
                intent.putExtra(LodgingEditActivity.EXTRA_LODGING_TITLE, trip.getLodgingTitle());
                intent.putExtra(LodgingEditActivity.EXTRA_LODGING_CHECK_IN_DATE_TIME, trip.getLodgingCheckInDateTime());
                intent.putExtra(LodgingEditActivity.EXTRA_LODGING_CHECK_OUT_DATE_TIME, trip.getLodgingCheckOutDateTime());
                intent.putExtra(LodgingEditActivity.EXTRA_LODGING_ADDRESS, trip.getLodgingAddress());

                startActivityForResult(intent, EDIT_LODGING);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ACTIVITY && resultCode == Activity.RESULT_OK) {
            //get data from activity

            String activityTitle = data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_TITLE);
            String activityDestination= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_DESTINATION);
            String activityDescription= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_DESCRIPTION);
            String activityStartDateTime= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_START_DATE_TIME);
            String activityEndDateTime= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_END_DATE_TIME);
            String activityAddress= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_ADDRESS);
            String activityPhone= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_TITLE);
            String activityWebsite= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_TITLE);
            String activityEmail= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_TITLE);
            int activityPriority= data.getIntExtra(ActivityActivity.EXTRA_ACTIVITY_TITLE,1);



            Trip activity = new Trip(activityTitle, activityDestination, activityDescription,activityStartDateTime,activityEndDateTime,
                    activityAddress,activityPhone,activityWebsite,activityEmail,activityPriority);
            tripViewModel.insert(activity);

            Toast.makeText(this, "activity save", Toast.LENGTH_SHORT).show();

        } else if (requestCode == ADD_TRANSPORTATION_FRAGMENT && resultCode == Activity.RESULT_OK){
            //get data from transportation spinner activity
            //airplane data
            String airplaneDepartureDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_AirplaneDepartureDateTime);
            String departureAirplaneAirlineText= data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneAirline);
            String departureAirplaneFlightNumberText=data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneFlightNumber);
            String departureAirplaneSeatsText=data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneSeats);
            String departureAirplaneTerminalText=data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneTerminal);
            String departureAirplaneGateText=data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneGate);
            String airplaneArrivalDateTimeText=data.getStringExtra(TransportationSpinner.EXTRA_AirplaneArrivalDateTime);
            String arrivalArrivingCityOrAirportText=data.getStringExtra(TransportationSpinner.EXTRA_ArrivalArrivingCityOrAirport);
            String arrivalTerminalText=data.getStringExtra(TransportationSpinner.EXTRA_ArrivalTerminal);
            String arrivalGateText=data.getStringExtra(TransportationSpinner.EXTRA_ArrivalGate);

            Trip airplaneTransportation = new Trip(airplaneDepartureDateTimeText,departureAirplaneAirlineText,departureAirplaneFlightNumberText,
                    departureAirplaneSeatsText,departureAirplaneTerminalText,departureAirplaneGateText,
                    airplaneArrivalDateTimeText,arrivalArrivingCityOrAirportText,arrivalTerminalText,arrivalGateText);

            //train data
            String trainDepartureDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_TrainDepartureDateTime);
            String departureTrainStationText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureTrainStation);
            String departureTrainAddressText=data.getStringExtra(TransportationSpinner.EXTRA_DepartureTrainAddress);
            String trainArrivalDateTimeText= data.getStringExtra(TransportationSpinner.EXTRA_TrainArrivalDateTime);
            String trainArrivalStationText= data.getStringExtra(TransportationSpinner.EXTRA_TrainArrivalStation);
            String trainTypeText=data.getStringExtra(TransportationSpinner.EXTRA_TrainType);
            String trainNumberText= data.getStringExtra(TransportationSpinner.EXTRA_TrainNumber);
            String trainClassText=data.getStringExtra(TransportationSpinner.EXTRA_TrainClass);
            String trainSeatsText=data.getStringExtra(TransportationSpinner.EXTRA_TrainSeats);

            Trip trainTransportation = new Trip(trainDepartureDateTimeText,departureTrainStationText,departureTrainAddressText,
                    trainArrivalDateTimeText,trainArrivalStationText,trainTypeText,trainNumberText,trainClassText,trainSeatsText);


            //bus data
            String busDepartureDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_BusDepartureDateTime);
            String busLicensePlateText = data.getStringExtra(TransportationSpinner.EXTRA_BusLicensePlate);
            String departureBusAddressText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureBusAddress);
            String busArrivalDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_BusArrivalDateTime);
            String busArrivalAddressText = data.getStringExtra(TransportationSpinner.EXTRA_BusArrivalAddress);

            Trip busTransportation = new Trip(busDepartureDateTimeText,busLicensePlateText,departureBusAddressText,
                    busArrivalDateTimeText,busArrivalAddressText );



            //boat data
            String boatDepartureDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_BoatDepartureDateTime);
            String boatNameText = data.getStringExtra(TransportationSpinner.EXTRA_BoatName);
            String departureBoatLocationText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureBoatLocation);
            String departureBoatAddressText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureBoatAddress);
            String boatArrivalDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_BoatArrivalDateTime);
            String arrivalBoatLocationText = data.getStringExtra(TransportationSpinner.EXTRA_ArrivalBoatLocation);
            String arrivalBoatAddressText = data.getStringExtra(TransportationSpinner.EXTRA_ArrivalBoatAddress);
            String portNameText = data.getStringExtra(TransportationSpinner.EXTRA_PortName);
            String portAddressText = data.getStringExtra(TransportationSpinner.EXTRA_PortAddress);
            String cabinTypeText = data.getStringExtra(TransportationSpinner.EXTRA_CabinType);
            String cabinNumberText = data.getStringExtra(TransportationSpinner.EXTRA_CabinNumber);
            String boatDescriptionText = data.getStringExtra(TransportationSpinner.EXTRA_BoatDescription);

            Trip boatTransportation = new Trip(boatDepartureDateTimeText,boatNameText,departureBoatLocationText,
                    departureBoatAddressText,boatArrivalDateTimeText,arrivalBoatLocationText,arrivalBoatAddressText,
                    portNameText,portAddressText,cabinTypeText,cabinNumberText,boatDescriptionText);



            //car rental data
            String rentalAgencyText = data.getStringExtra(TransportationSpinner.EXTRA_RentalAgency);
            String pickupDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_PickupDateTime);
            String pickupLocationText = data.getStringExtra(TransportationSpinner.EXTRA_PickupLocation);
            String carRentalPickupAddressText =data.getStringExtra(TransportationSpinner.EXTRA_CarRentalPickupAddress);
            String carRentalPhoneText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalPhone);
            String dropOffDateTimeText =data.getStringExtra(TransportationSpinner.EXTRA_DropOffDateTime);
            String dropOffLocationText = data.getStringExtra(TransportationSpinner.EXTRA_DropOffLocation);
            String dropOffAddressText = data.getStringExtra(TransportationSpinner.EXTRA_DropOffAddress);
            String carRentalWebsiteText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalWebsite);
            String carRentalEmailText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalEmail);
            String carRentalDescriptionText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalDescription);

            Trip carRentalTransportation = new Trip(rentalAgencyText,pickupDateTimeText,pickupLocationText,
                    carRentalPickupAddressText,carRentalPhoneText,dropOffDateTimeText,dropOffLocationText,
                    dropOffAddressText,carRentalWebsiteText,carRentalEmailText,carRentalDescriptionText);


            tripViewModel.insert(airplaneTransportation);
            tripViewModel.insert(trainTransportation);
            tripViewModel.insert(busTransportation);
            tripViewModel.insert(boatTransportation);
            tripViewModel.insert(carRentalTransportation);
            Toast.makeText(this, "Transportation save", Toast.LENGTH_SHORT).show();

        }// for add lodging
        else if (requestCode == ADD_LODGING && resultCode == Activity.RESULT_OK) {
            //get data from lodging activity
            String lodgingTitle = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_TITLE);
            String lodgingCheckInDateTime = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_CHECK_IN_DATE_TIME);
            String lodgingCheckOutDateTime = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_CHECK_OUT_DATE_TIME);
            String lodgingDescription = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_DESCRIPTION);
            String lodgingAddress = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_ADDRESS);
            String lodgingPhone = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_PHONE);
            String lodgingWebsite = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_WEBSITE);
            String lodgingEmail = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_EMAIL);

            Trip lodging = new Trip(lodgingTitle, lodgingCheckInDateTime, lodgingCheckOutDateTime,lodgingDescription, lodgingAddress, lodgingPhone, lodgingWebsite, lodgingEmail);
            tripViewModel.insert(lodging);

            Toast.makeText(this, "lodging save", Toast.LENGTH_SHORT).show();

        } //for edit lodging and update data
        else if (requestCode == EDIT_LODGING && resultCode == Activity.RESULT_OK) {
            int id = data.getIntExtra(LodgingEditActivity.EXTRA_LODGING_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Trip cant be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String lodgingTitle = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_TITLE);
            String lodgingCheckInDateTime = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_CHECK_IN_DATE_TIME);
            String lodgingCheckOutDateTime = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_CHECK_OUT_DATE_TIME);
            String lodgingDescription = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_DESCRIPTION);
            String lodgingAddress = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_ADDRESS);
            String lodgingPhone = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_PHONE);
            String lodgingWebsite = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_WEBSITE);
            String lodgingEmail = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_EMAIL);

            Trip editLodging = new Trip(lodgingTitle, lodgingCheckInDateTime, lodgingCheckOutDateTime,lodgingDescription, lodgingAddress, lodgingPhone, lodgingWebsite, lodgingEmail);
            editLodging.setId(id);
            tripViewModel.update(editLodging);
            Toast.makeText(this, "lodging updated", Toast.LENGTH_SHORT).show();

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_trip:
                tripViewModel.deleteAllTrips();

                Toast.makeText(this, "All trips deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}