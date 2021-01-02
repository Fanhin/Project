package com.example.tripbuddyv2.Tab;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tripbuddyv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BoatFragment extends Fragment {

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

     EditText editTextBoatDepartureDateTime;
    EditText editTextBoatName;
    EditText  editTextDepartureBoatLocation;
    EditText editTextDepartureBoatAddress;
    EditText editTextBoatArrivalDateTime;
    EditText editTextArrivalBoatLocation;
    EditText editTextArrivalBoatAddress;
    EditText editTextPortName;
    EditText editTextPortAddress;
    EditText editTextCabinType;
    EditText editTextCabinNumber;
    EditText  editTextBoatDescription;

    Button boatSave;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_boat, container, false);

         editTextBoatDepartureDateTime=view.findViewById(R.id.edit_text_boat_departure_date_time);
         editTextBoatName=view.findViewById(R.id.edit_text_boat_name);
         editTextDepartureBoatLocation=view.findViewById(R.id.edit_text_departure_boat_location);
         editTextDepartureBoatAddress=view.findViewById(R.id.edit_text_departure_boat_address);
         editTextBoatArrivalDateTime=view.findViewById(R.id.edit_text_boat_arrival_date_time);
         editTextArrivalBoatLocation=view.findViewById(R.id.edit_text_arrival_boat_location);
         editTextArrivalBoatAddress=view.findViewById(R.id.edit_text_arrival_boat_address);
         editTextPortName=view.findViewById(R.id.edit_text_boat_port_name);
         editTextPortAddress=view.findViewById(R.id.edit_text_boat_port_address);
         editTextCabinType=view.findViewById(R.id.edit_text_cabin_type);
         editTextCabinNumber=view.findViewById(R.id.edit_text_cabin_number);
          editTextBoatDescription=view.findViewById(R.id.edit_text_boat_description);
          boatSave = view.findViewById(R.id.boatSave);

        boatSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBoat();
            }
        });

        editTextBoatDepartureDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextBoatDepartureDateTime);
            }
        });

        editTextBoatArrivalDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextBoatArrivalDateTime);
            }
        });

        return view;
    }

    private void saveBoat() {


         String boatDepartureDateTimeText = editTextBoatDepartureDateTime.getText().toString();
         String boatNameText = editTextBoatName.getText().toString();
         String departureBoatLocationText = editTextDepartureBoatLocation.getText().toString();
         String departureBoatAddressText = editTextDepartureBoatAddress.getText().toString();
         String boatArrivalDateTimeText = editTextBoatArrivalDateTime.getText().toString();
         String arrivalBoatLocationText = editTextArrivalBoatLocation.getText().toString();
         String arrivalBoatAddressText = editTextArrivalBoatAddress.getText().toString();
         String portNameText = editTextPortName.getText().toString();
         String portAddressText = editTextPortAddress.getText().toString();
         String cabinTypeText = editTextCabinType.getText().toString();
         String cabinNumberText = editTextCabinNumber.getText().toString();
         String boatDescriptionText = editTextBoatDescription.getText().toString();

        if (boatDepartureDateTimeText.isEmpty()) {
            Toast.makeText(getActivity(), " cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent boatData = new Intent();
        boatData.putExtra(EXTRA_BoatDepartureDateTime,boatDepartureDateTimeText);
        boatData.putExtra(EXTRA_BoatName,boatNameText);
        boatData.putExtra(EXTRA_DepartureBoatLocation,departureBoatLocationText);
        boatData.putExtra(EXTRA_DepartureBoatAddress,departureBoatAddressText);
        boatData.putExtra(EXTRA_BoatArrivalDateTime,boatArrivalDateTimeText);
        boatData.putExtra(EXTRA_ArrivalBoatLocation,arrivalBoatLocationText);
        boatData.putExtra(EXTRA_ArrivalBoatAddress,arrivalBoatAddressText);
        boatData.putExtra(EXTRA_PortName,portNameText);
        boatData.putExtra(EXTRA_PortAddress,portAddressText);
        boatData.putExtra(EXTRA_CabinType,cabinTypeText);
        boatData.putExtra(EXTRA_CabinNumber,cabinNumberText);
        boatData.putExtra(EXTRA_BoatDescription,boatDescriptionText);


        getActivity().setResult(Activity.RESULT_OK, boatData);
        getActivity().finish();


    }

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(getActivity(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(getActivity(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}