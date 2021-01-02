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


public class TrainFragment extends Fragment {
    public static final  String EXTRA_TrainDepartureDateTime="com.example.tripbuddyv2.EXTRA_TrainDepartureDateTime";
    public static final  String EXTRA_DepartureTrainStation="com.example.tripbuddyv2.EXTRA_DepartureTrainStation";
    public static final  String EXTRA_DepartureTrainAddress="com.example.tripbuddyv2.EXTRA_DepartureTrainAddress";
    public static final  String EXTRA_TrainArrivalDateTime="com.example.tripbuddyv2.EXTRA_TrainArrivalDateTime";
    public static final  String EXTRA_TrainArrivalStation="com.example.tripbuddyv2.EXTRA_TrainArrivalStation";
    public static final  String EXTRA_TrainType="com.example.tripbuddyv2.EXTRA_TrainType";
    public static final  String EXTRA_TrainNumber="com.example.tripbuddyv2.EXTRA_TrainNumber";
    public static final  String EXTRA_TrainClass="com.example.tripbuddyv2.EXTRA_TrainClass";
    public static final  String EXTRA_TrainSeats="com.example.tripbuddyv2.EXTRA_TrainSeats";

    EditText  editTextTrainDepartureDateTime;
    EditText  editTextDepartureTrainStation;
    EditText  editTextDepartureTrainAddress;
    EditText  editTextTrainArrivalDateTime;
    EditText  editTextTrainArrivalStation;
    EditText  editTextTrainType;
    EditText  editTextTrainNumber;
    EditText  editTextTrainClass;
    EditText  editTextTrainSeats;

    Button trainSave;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_train, container, false);
          editTextTrainDepartureDateTime=view.findViewById(R.id.edit_text_train_departure_date_time);
          editTextDepartureTrainStation=view.findViewById(R.id.edit_text_departure_train_station);
          editTextDepartureTrainAddress=view.findViewById(R.id.edit_text_departure_train_address);
          editTextTrainArrivalDateTime=view.findViewById(R.id.edit_text_train_arrival_date_time);
          editTextTrainArrivalStation=view.findViewById(R.id.edit_text_train_arrival_station);
          editTextTrainType=view.findViewById(R.id.edit_text_train_type);
          editTextTrainNumber=view.findViewById(R.id.edit_text_train_number);
          editTextTrainClass=view.findViewById(R.id.edit_text_train_class);
          editTextTrainSeats=view.findViewById(R.id.edit_text_train_seats);

          trainSave = view.findViewById(R.id.trainSave);

        trainSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTrain();
            }
        });

        editTextTrainDepartureDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextTrainDepartureDateTime);
            }
        });

        editTextTrainArrivalDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextTrainArrivalDateTime);
            }
        });

        return view;
    }

    private void saveTrain() {

         String trainDepartureDateTimeText = editTextTrainDepartureDateTime.getText().toString();
         String departureTrainStationText = editTextDepartureTrainStation.getText().toString();
         String departureTrainAddressText= editTextDepartureTrainAddress.getText().toString();
         String trainArrivalDateTimeText= editTextTrainArrivalDateTime.getText().toString();
         String trainArrivalStationText= editTextTrainArrivalStation.getText().toString();
         String trainTypeText= editTextTrainType.getText().toString();
         String trainNumberText= editTextTrainNumber.getText().toString();
         String trainClassText= editTextTrainClass.getText().toString();
         String trainSeatsText= editTextTrainSeats.getText().toString();

        if (trainDepartureDateTimeText.isEmpty()) {
            Toast.makeText(getActivity(), " cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent trainData = new Intent();
        trainData.putExtra(EXTRA_TrainDepartureDateTime,trainDepartureDateTimeText);
        trainData.putExtra(EXTRA_DepartureTrainStation,departureTrainStationText);
        trainData.putExtra(EXTRA_DepartureTrainAddress,departureTrainAddressText);
        trainData.putExtra(EXTRA_TrainArrivalDateTime,trainArrivalDateTimeText);
        trainData.putExtra(EXTRA_TrainArrivalStation,trainArrivalStationText);
        trainData.putExtra(EXTRA_TrainType,trainTypeText);
        trainData.putExtra(EXTRA_TrainNumber,trainNumberText);
        trainData.putExtra(EXTRA_TrainClass,trainClassText);
        trainData.putExtra(EXTRA_TrainSeats,trainSeatsText);

        getActivity().setResult(Activity.RESULT_OK, trainData);
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