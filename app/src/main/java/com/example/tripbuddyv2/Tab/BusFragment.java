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

public class BusFragment extends Fragment {

    public static final  String EXTRA_BusDepartureDateTime="com.example.tripbuddyv2.EXTRA_BusDepartureDateTime";
    public static final  String  EXTRA_BusLicensePlate="com.example.tripbuddyv2.EXTRA_BusLicensePlate";
    public static final  String  EXTRA_DepartureBusAddress="com.example.tripbuddyv2.EXTRA_DepartureBusAddress";
    public static final  String  EXTRA_BusArrivalDateTime="com.example.tripbuddyv2.EXTRA_BusArrivalDateTime";
    public static final  String  EXTRA_BusArrivalAddress="com.example.tripbuddyv2.EXTRA_BusArrivalAddress";

    EditText editTextBusDepartureDateTime;
    EditText editTextBusLicensePlate;
    EditText editTextDepartureBusAddress;
    EditText editTextBusArrivalDateTime;
    EditText editTextBusArrivalAddress;

    Button busSave;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bus, container, false);
         editTextBusDepartureDateTime=view.findViewById(R.id.edit_text_bus_departure_date_time);
         editTextBusLicensePlate=view.findViewById(R.id.edit_text_bus_license_plate);
         editTextDepartureBusAddress=view.findViewById(R.id.edit_text_departure_bus_address);
         editTextBusArrivalDateTime=view.findViewById(R.id.edit_text_bus_arrival_date_time);
         editTextBusArrivalAddress=view.findViewById(R.id.edit_text_bus_arrival_address);

         busSave = view.findViewById(R.id.busSave);

        busSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBus();
            }
        });

        editTextBusDepartureDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextBusDepartureDateTime);
            }
        });

        editTextBusArrivalDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextBusArrivalDateTime);
            }
        });



        return view;

    }

    private void saveBus() {
         String busDepartureDateTimeText = editTextBusDepartureDateTime.getText().toString();
         String busLicensePlateText = editTextBusLicensePlate.getText().toString();
         String departureBusAddressText = editTextDepartureBusAddress.getText().toString();
         String busArrivalDateTimeText = editTextBusArrivalDateTime.getText().toString();
         String busArrivalAddressText = editTextBusArrivalAddress.getText().toString();

        if (busDepartureDateTimeText.isEmpty()) {
            Toast.makeText(getActivity(), " cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent busData = new Intent();
        busData.putExtra(EXTRA_BusDepartureDateTime,busDepartureDateTimeText);
        busData.putExtra(EXTRA_BusLicensePlate,busLicensePlateText);
        busData.putExtra(EXTRA_DepartureBusAddress,departureBusAddressText);
        busData.putExtra(EXTRA_BusArrivalDateTime,busArrivalDateTimeText);
        busData.putExtra(EXTRA_BusArrivalAddress,busArrivalAddressText);


        getActivity().setResult(Activity.RESULT_OK, busData);
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