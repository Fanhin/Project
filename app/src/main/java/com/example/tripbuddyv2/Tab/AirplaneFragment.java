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

import com.example.tripbuddyv2.BottomNav.LodgingEditActivity;
import com.example.tripbuddyv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AirplaneFragment extends Fragment {

    public static final String EXTRA_AirplaneDepartureDateTime =
            "com.example.tripbuddyv2.EXTRA_AirplaneDepartureDateTime";
    public static final String EXTRA_DepartureAirplaneAirline =
            "com.example.tripbuddyv2.EXTRA_DepartureAirplaneAirline";
    public static final String EXTRA_DepartureAirplaneFlightNumber =
            "com.example.tripbuddyv2.EXTRA_DepartureAirplaneFlightNumber";
    public static final String EXTRA_DepartureAirplaneSeats =
            "com.example.tripbuddyv2.EXTRA_DepartureAirplaneSeats";
    public static final String EXTRA_DepartureAirplaneTerminal =
            "com.example.tripbuddyv2.EXTRA_DepartureAirplaneTerminal";
    public static final String EXTRA_DepartureAirplaneGate =
            "com.example.tripbuddyv2.EXTRA_DepartureAirplaneGate";
    public static final String EXTRA_AirplaneArrivalDateTime =
            "com.example.tripbuddyv2.EXTRA_AirplaneArrivalDateTime";
    public static final String EXTRA_ArrivalArrivingCityOrAirport =
            "com.example.tripbuddyv2.EXTRA_ArrivalArrivingCityOrAirport";
    public static final String EXTRA_ArrivalTerminal =
            "com.example.tripbuddyv2.EXTRA_ArrivalTerminal";
    public static final String EXTRA_ArrivalGate =
            "com.example.tripbuddyv2.EXTRA_ArrivalGate";
    public static final String EXTRA_AirplaneDescription =
            "com.example.tripbuddyv2.EXTRA_AirplaneDescription";

    EditText editTextAirplaneDepartureDateTime;
    EditText editTextDepartureAirplaneAirline;
    EditText editTextDepartureAirplaneFlightNumber;
    EditText editTextDepartureAirplaneSeats;
    EditText editTextDepartureAirplaneTerminal;
    EditText editTextDepartureAirplaneGate;

    EditText editTextAirplaneArrivalDateTime;
    EditText editTextArrivalArrivingCityOrAirport;
    EditText editTextArrivalTerminal;
    EditText editTextArrivalGate;
    EditText editTextAirplaneDescription;

    Button airplaneSave;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_airplane, container, false);
        editTextAirplaneDepartureDateTime = view.findViewById(R.id.edit_text_airplane_departure_date_time);
        editTextDepartureAirplaneAirline = view.findViewById(R.id.edit_text_departure_airplane_airline);
        editTextDepartureAirplaneFlightNumber = view.findViewById(R.id.edit_text_departure_airplane_flight_number);
        editTextDepartureAirplaneSeats = view.findViewById(R.id.edit_text_departure_airplane_seats);
        editTextDepartureAirplaneTerminal = view.findViewById(R.id.edit_text_departure_airplane_terminal);
        editTextDepartureAirplaneGate = view.findViewById(R.id.edit_text_departure_airplane_gate);

        editTextAirplaneArrivalDateTime = view.findViewById(R.id.edit_text_airplane_arrival_date_time);
        editTextArrivalArrivingCityOrAirport = view.findViewById(R.id.edit_text_arrival_arriving_city_or_airport);
        editTextArrivalTerminal = view.findViewById(R.id.edit_text_arrival_terminal);
        editTextArrivalGate = view.findViewById(R.id.edit_text_arrival_gate);
        editTextAirplaneDescription = view.findViewById(R.id.edit_text_airplane_description);

        airplaneSave = view.findViewById(R.id.airplaneSave);

        airplaneSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAirplane();
            }
        });

        editTextAirplaneDepartureDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextAirplaneDepartureDateTime);
            }
        });

        editTextAirplaneArrivalDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextAirplaneArrivalDateTime);
            }
        });



        return view;

    }

    private void saveAirplane() {
        String airplaneDepartureDateTimeText = editTextAirplaneDepartureDateTime.getText().toString();
        String departureAirplaneAirlineText= editTextDepartureAirplaneAirline.getText().toString();;
        String departureAirplaneFlightNumberText= editTextDepartureAirplaneFlightNumber.getText().toString();;
        String departureAirplaneSeatsText= editTextDepartureAirplaneSeats.getText().toString();;
        String departureAirplaneTerminalText= editTextDepartureAirplaneTerminal.getText().toString();;
        String departureAirplaneGateText= editTextDepartureAirplaneGate.getText().toString();;

        String airplaneArrivalDateTimeText= editTextAirplaneArrivalDateTime.getText().toString();;
        String arrivalArrivingCityOrAirportText= editTextArrivalArrivingCityOrAirport.getText().toString();;
        String arrivalTerminalText= editTextArrivalTerminal.getText().toString();;
        String arrivalGateText= editTextArrivalGate.getText().toString();;

        if (airplaneDepartureDateTimeText.isEmpty()) {
            Toast.makeText(getActivity(), " cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_AirplaneDepartureDateTime,airplaneDepartureDateTimeText);
        data.putExtra(EXTRA_DepartureAirplaneAirline,departureAirplaneAirlineText);
        data.putExtra(EXTRA_DepartureAirplaneFlightNumber,departureAirplaneFlightNumberText);
        data.putExtra(EXTRA_DepartureAirplaneSeats,departureAirplaneSeatsText);
        data.putExtra(EXTRA_DepartureAirplaneTerminal,departureAirplaneTerminalText);
        data.putExtra(EXTRA_DepartureAirplaneGate,departureAirplaneGateText);

        data.putExtra(EXTRA_AirplaneArrivalDateTime,airplaneArrivalDateTimeText);
        data.putExtra(EXTRA_ArrivalArrivingCityOrAirport,arrivalArrivingCityOrAirportText);
        data.putExtra(EXTRA_ArrivalTerminal,arrivalTerminalText);
        data.putExtra(EXTRA_ArrivalGate,arrivalGateText);

        getActivity().setResult(Activity.RESULT_OK, data);
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