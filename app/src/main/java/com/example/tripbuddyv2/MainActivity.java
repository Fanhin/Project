package com.example.tripbuddyv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tripbuddyv2.Adapter.AdapterV3;
import com.example.tripbuddyv2.Adapter.TripAdapterV2;
import com.example.tripbuddyv2.BottomNav.ActivityActivity;
import com.example.tripbuddyv2.BottomNav.LodgingEditActivity;
import com.example.tripbuddyv2.BottomNav.TransportationActivity;
import com.example.tripbuddyv2.Models.ActivityEvent;
import com.example.tripbuddyv2.Models.Item;
import com.example.tripbuddyv2.Models.Lodging;
import com.example.tripbuddyv2.Models.Transportation;
import com.example.tripbuddyv2.ModelsV2.ActivityObj;
import com.example.tripbuddyv2.ModelsV2.ItemV2;
import com.example.tripbuddyv2.ModelsV2.LodgingObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 11;
    public static final int ADD_TRANSPORTATION = 1;
    public static final int ADD_TRANSPORTATION_FRAGMENT = 12;
    public static final int ADD_LODGING = 2;
    public static final int EDIT_LODGING = 22;
    public static final int EDIT_ACTIVITY =33;
    public static final int ADD_ACTIVITY = 3;

    public static final int EXTRA_Airplane = 1;
    public static final int EXTRA_Train = 2;
    public static final int EXTRA_Bus = 3;
    public static final int EXTRA_Boat = 4;
    public static final int EXTRA_Car_rental = 5;


    private TripViewModel tripViewModel;

    List<ItemV2> itemV2;
    ArrayList<String> imageUrisLodgingPath;
    ArrayList<String> imageUrisActivityPath;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        FloatingActionButton buttonAddTransportation = findViewById(R.id.button_add_transportation);

        buttonAddTransportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, TransportationSpinner.class);
                startActivityForResult(intent1, ADD_TRANSPORTATION_FRAGMENT);

            }
        });

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;

        Log.e("MyActivity", "manufacturer " + manufacturer
                + " \n model " + model
                + " \n version " + version
                + " \n versionRelease " + versionRelease
        );


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

        //start here
        List<Item> items = new ArrayList<>();
        ActivityEvent activity = new ActivityEvent("Activity Title","Activity Description");
        items.add(new Item(0,activity));
        Lodging lodging = new Lodging("Lodging Title","Lodging Description");
        items.add(new Item(1,lodging));
        Transportation transportation = new Transportation("Transportation Title","Transportation Description");
        items.add(new Item(2,transportation));

         itemV2 = new ArrayList<>();





        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final TripAdapter adapter = new TripAdapter();

        //adapter
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
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(viewHolder.itemView.getContext())
                        .setMessage("Are you sure ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tripViewModel.delete(adapter.getTripAt(viewHolder.getAdapterPosition()));
                                Toast.makeText(MainActivity.this, "Trip deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                            }
                        })
                        .create()
                        .show();

                //tripViewModel.delete(adapter.getTripAt(viewHolder.getAdapterPosition()));


            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TripAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Trip trip,int type) {
                Intent intentLodging = new Intent(MainActivity.this, LodgingEditActivity.class);
                Intent intentActivity = new Intent(MainActivity.this, ActivityActivity.class);
                if (type == 1){
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_ID, trip.getId());
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_TITLE, trip.getLodgingTitle());
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_DESCRIPTION,trip.getLodgingDescription());
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_CHECK_IN_DATE_TIME, trip.getLodgingCheckInDateTime());
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_CHECK_OUT_DATE_TIME, trip.getLodgingCheckOutDateTime());
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_ADDRESS, trip.getLodgingAddress());
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_PHONE, trip.getLodgingPhone());
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_WEBSITE, trip.getLodgingWebsite());
                    intentLodging.putExtra(LodgingEditActivity.EXTRA_LODGING_EMAIL, trip.getLodgingEmail());
                    intentLodging.putStringArrayListExtra(LodgingEditActivity.EXTRA_LODGING_ARRAY_OF_IMAGE,trip.getUriLodgingPath());
                    startActivityForResult(intentLodging, EDIT_LODGING);
                } else if (type == 2){
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_ID, trip.getId());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_TITLE,trip.getActivityTitle());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_DESTINATION,trip.getActivityDestination());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_DESCRIPTION,trip.getActivityDescription());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_START_DATE_TIME,trip.getActivityStartDateTime());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_END_DATE_TIME,trip.getActivityEndDateTime());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_ADDRESS,trip.getActivityAddress());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_PHONE,trip.getActivityPhone());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_WEBSITE,trip.getActivityWebsite());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_EMAIL,trip.getActivityEmail());
                    intentActivity.putExtra(ActivityActivity.EXTRA_ACTIVITY_PRIORITY,trip.getActivityPriority());
                    intentActivity.putStringArrayListExtra(ActivityActivity.EXTRA_ACTIVITY_ARRAY_OF_IMAGE,trip.getUriActivityPath());
                    startActivityForResult(intentActivity, EDIT_ACTIVITY);


                }

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
            String activityPhone= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_PHONE);
            String activityWebsite= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_WEBSITE);
            String activityEmail= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_EMAIL);
            int activityPriority= data.getIntExtra(ActivityActivity.EXTRA_ACTIVITY_PRIORITY,1);
            String activityImagePath1 = data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_IMAGE_PATH1);
            String activityImagePath2 = data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_IMAGE_PATH2);
            String activityImagePath3 = data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_IMAGE_PATH3);

            imageUrisActivityPath = data.getExtras().getStringArrayList("strActivityImagePath");
            Log.e("path edit",imageUrisActivityPath.toString());


            Trip activity = new Trip(1,activityTitle, activityDestination, activityDescription,activityStartDateTime,activityEndDateTime,
                    activityAddress,activityPhone,activityWebsite,activityEmail,activityPriority,activityImagePath1
                    ,activityImagePath2,activityImagePath3,imageUrisActivityPath);
            tripViewModel.insert(activity);

            Toast.makeText(this, "activity save", Toast.LENGTH_SHORT).show();
            //edit activity
        }else if (requestCode == EDIT_ACTIVITY && resultCode == Activity.RESULT_OK) {
            //get data from activity
            int id = data.getIntExtra(ActivityActivity.EXTRA_ACTIVITY_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Trip cant be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String activityTitle = data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_TITLE);
            String activityDestination= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_DESTINATION);
            String activityDescription= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_DESCRIPTION);
            String activityStartDateTime= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_START_DATE_TIME);
            String activityEndDateTime= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_END_DATE_TIME);
            String activityAddress= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_ADDRESS);
            String activityPhone= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_PHONE);
            String activityWebsite= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_WEBSITE);
            String activityEmail= data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_EMAIL);
            int activityPriority= data.getIntExtra(ActivityActivity.EXTRA_ACTIVITY_PRIORITY,1);
            String activityImagePath1 = data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_IMAGE_PATH1);
            String activityImagePath2 = data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_IMAGE_PATH2);
            String activityImagePath3 = data.getStringExtra(ActivityActivity.EXTRA_ACTIVITY_IMAGE_PATH3);

            imageUrisActivityPath = data.getExtras().getStringArrayList("strActivityImagePath");
            Log.e("path edit",imageUrisActivityPath.toString());

            Trip editActivity = new Trip(1,activityTitle, activityDestination, activityDescription,activityStartDateTime,activityEndDateTime,
                    activityAddress,activityPhone,activityWebsite,activityEmail,activityPriority,activityImagePath1,
                    activityImagePath2,activityImagePath3,imageUrisActivityPath);
            editActivity.setId(id);
            tripViewModel.update(editActivity);

            Toast.makeText(this, "activity updated", Toast.LENGTH_SHORT).show();

        } else if (requestCode == ADD_TRANSPORTATION_FRAGMENT && resultCode == EXTRA_Airplane   ) {
            //get data from transportation spinner activity
            //airplane data

            String airplaneDepartureDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_AirplaneDepartureDateTime);
            String departureAirplaneAirlineText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneAirline);
            String departureAirplaneFlightNumberText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneFlightNumber);
            String departureAirplaneSeatsText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneSeats);
            String departureAirplaneTerminalText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneTerminal);
            String departureAirplaneGateText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureAirplaneGate);
            String airplaneArrivalDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_AirplaneArrivalDateTime);
            String arrivalArrivingCityOrAirportText = data.getStringExtra(TransportationSpinner.EXTRA_ArrivalArrivingCityOrAirport);
            String arrivalTerminalText = data.getStringExtra(TransportationSpinner.EXTRA_ArrivalTerminal);
            String arrivalGateText = data.getStringExtra(TransportationSpinner.EXTRA_ArrivalGate);
            String airplaneDescription = data.getStringExtra(TransportationSpinner.EXTRA_AirplaneDescription);

            Trip airplaneTransportation = new Trip(2,airplaneDepartureDateTimeText, departureAirplaneAirlineText, departureAirplaneFlightNumberText,
                    departureAirplaneSeatsText, departureAirplaneTerminalText, departureAirplaneGateText,
                    airplaneArrivalDateTimeText, arrivalArrivingCityOrAirportText, arrivalTerminalText, arrivalGateText,airplaneDescription);

            tripViewModel.insert(airplaneTransportation);

            Toast.makeText(this, "Airplane Transportation save", Toast.LENGTH_SHORT).show();
        }else if (requestCode == ADD_TRANSPORTATION_FRAGMENT && resultCode == EXTRA_Train   ) {

            //train data
            String trainDepartureDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_TrainDepartureDateTime);
            String departureTrainStationText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureTrainStation);
            String departureTrainAddressText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureTrainAddress);
            String trainArrivalDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_TrainArrivalDateTime);
            String trainArrivalStationText = data.getStringExtra(TransportationSpinner.EXTRA_TrainArrivalStation);
            String trainTypeText = data.getStringExtra(TransportationSpinner.EXTRA_TrainType);
            String trainNumberText = data.getStringExtra(TransportationSpinner.EXTRA_TrainNumber);
            String trainCoachText = data.getStringExtra(TransportationSpinner.EXTRA_TrainCoach);
            String trainClassText = data.getStringExtra(TransportationSpinner.EXTRA_TrainClass);
            String trainSeatsText = data.getStringExtra(TransportationSpinner.EXTRA_TrainSeats);

            Trip trainTransportation = new Trip(3,trainDepartureDateTimeText, departureTrainStationText, departureTrainAddressText,
                    trainArrivalDateTimeText, trainArrivalStationText, trainTypeText, trainNumberText,trainCoachText, trainClassText, trainSeatsText);

            tripViewModel.insert(trainTransportation);

            Toast.makeText(this, "Train Transportation save", Toast.LENGTH_SHORT).show();
        }else if (requestCode == ADD_TRANSPORTATION_FRAGMENT && resultCode == EXTRA_Bus   ) {


            //bus data
            String busDepartureDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_BusDepartureDateTime);
            String busLicensePlateText = data.getStringExtra(TransportationSpinner.EXTRA_BusLicensePlate);
            String departureBusAddressText = data.getStringExtra(TransportationSpinner.EXTRA_DepartureBusAddress);
            String busArrivalDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_BusArrivalDateTime);
            String busArrivalAddressText = data.getStringExtra(TransportationSpinner.EXTRA_BusArrivalAddress);

            Trip busTransportation = new Trip(4,busDepartureDateTimeText, busLicensePlateText, departureBusAddressText,
                    busArrivalDateTimeText, busArrivalAddressText);


            tripViewModel.insert(busTransportation);

            Toast.makeText(this, "Bus Transportation save", Toast.LENGTH_SHORT).show();
        }else if (requestCode == ADD_TRANSPORTATION_FRAGMENT && resultCode == EXTRA_Boat   ) {


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
            String boatPhoneText = data.getStringExtra(TransportationSpinner.EXTRA_BoatPhone);

            Trip boatTransportation = new Trip(5,boatDepartureDateTimeText, boatNameText, departureBoatLocationText,
                    departureBoatAddressText, boatArrivalDateTimeText, arrivalBoatLocationText, arrivalBoatAddressText,
                    portNameText, portAddressText, cabinTypeText, cabinNumberText, boatDescriptionText,boatPhoneText);


            tripViewModel.insert(boatTransportation);

            Toast.makeText(this, "Boat Transportation save", Toast.LENGTH_SHORT).show();
        }else if (requestCode == ADD_TRANSPORTATION_FRAGMENT && resultCode == EXTRA_Car_rental   ) {


            //car rental data
            String rentalAgencyText = data.getStringExtra(TransportationSpinner.EXTRA_RentalAgency);
            String pickupDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_PickupDateTime);
            String pickupLocationText = data.getStringExtra(TransportationSpinner.EXTRA_PickupLocation);
            String carRentalPickupAddressText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalPickupAddress);
            String carRentalPhoneText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalPhone);
            String dropOffDateTimeText = data.getStringExtra(TransportationSpinner.EXTRA_DropOffDateTime);
            String dropOffLocationText = data.getStringExtra(TransportationSpinner.EXTRA_DropOffLocation);
            String dropOffAddressText = data.getStringExtra(TransportationSpinner.EXTRA_DropOffAddress);
            String carRentalWebsiteText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalWebsite);
            String carRentalEmailText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalEmail);
            String carRentalDescriptionText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalDescription);
            String carRentalConfirmationText = data.getStringExtra(TransportationSpinner.EXTRA_CarRentalConfirmation);


            Trip carRentalTransportation = new Trip(6,rentalAgencyText, pickupDateTimeText, pickupLocationText,
                    carRentalPickupAddressText, carRentalPhoneText, dropOffDateTimeText, dropOffLocationText,
                    dropOffAddressText, carRentalWebsiteText, carRentalEmailText, carRentalDescriptionText,
                    carRentalConfirmationText);



            tripViewModel.insert(carRentalTransportation);
            Toast.makeText(this, "Car Rental Transportation save", Toast.LENGTH_SHORT).show();

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
            String lodgingImagePath1 = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_IMAGE_PATH1);
            String lodgingImagePath2 = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_IMAGE_PATH1);
            String lodgingImagePath3 = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_IMAGE_PATH3);
            //imageUrisLodgingPath = data.getExtras().getStringArrayList("strLodgingImagePath");

            imageUrisLodgingPath = data.getExtras().getStringArrayList("strLodgingImagePath");
            //Log.e("hhhhhhhhh",imageUrisLodgingPath.get(0));
            Log.e("path add",imageUrisLodgingPath.toString());

            Trip lodging = new Trip(0,lodgingTitle, lodgingCheckInDateTime, lodgingCheckOutDateTime,lodgingDescription,
                    lodgingAddress, lodgingPhone, lodgingWebsite, lodgingEmail,lodgingImagePath1,lodgingImagePath2,
                    lodgingImagePath3,imageUrisLodgingPath);
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
            String lodgingImagePath1 = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_IMAGE_PATH1);
            String lodgingImagePath2 = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_IMAGE_PATH1);
            String lodgingImagePath3 = data.getStringExtra(LodgingEditActivity.EXTRA_LODGING_IMAGE_PATH3);
            imageUrisLodgingPath = data.getExtras().getStringArrayList("strLodgingImagePath");
            Log.e("path edit",imageUrisLodgingPath.toString());


            Trip editLodging = new Trip(0,lodgingTitle, lodgingCheckInDateTime, lodgingCheckOutDateTime
                    ,lodgingDescription, lodgingAddress, lodgingPhone, lodgingWebsite, lodgingEmail,lodgingImagePath1,
                    lodgingImagePath2,lodgingImagePath3,imageUrisLodgingPath);
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