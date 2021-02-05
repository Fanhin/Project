package com.example.tripbuddyv2.Tab;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
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

import com.example.tripbuddyv2.BottomNav.AlertReceiver;
import com.example.tripbuddyv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CarRentalFragment extends Fragment {
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
    public static final  String EXTRA_CarRentalConfirmation="com.example.tripbuddyv2.EXTRA_CarRentalConfirmation";

    public static final int EXTRA_Car_rental = 5;

     EditText editTextRentalAgency;
    EditText editTextPickupDateTime;
    EditText editTextPickupLocation;
    EditText editTextCarRentalPickupAddress;
    EditText editTextCarRentalPhone;
    EditText editTextDropOffDateTime;
    EditText editTextDropOffLocation;
    EditText editTextDropOffAddress;
    EditText editTextCarRentalWebsite;
    EditText editTextCarRentalEmail;
    EditText editTextCarRentalDescription;
    EditText editTextCarRentalConfirmation;

    Button carRentalSave;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_rental, container, false);
         editTextRentalAgency=view.findViewById(R.id.edit_text_rental_agency);
         editTextPickupDateTime=view.findViewById(R.id.edit_text_pickup_date_time);
         editTextPickupLocation=view.findViewById(R.id.edit_text_pickup_location);
         editTextCarRentalPickupAddress=view.findViewById(R.id.edit_text_car_rental_pickup_address);
         editTextCarRentalPhone=view.findViewById(R.id.edit_text_car_rental_phone);
         editTextDropOffDateTime=view.findViewById(R.id.edit_text_drop_off_date_time);
         editTextDropOffLocation=view.findViewById(R.id.edit_text_drop_off_location);
         editTextDropOffAddress=view.findViewById(R.id.edit_text_drop_off_address);
         editTextCarRentalWebsite=view.findViewById(R.id.edit_text_car_rental_website);
         editTextCarRentalEmail=view.findViewById(R.id.edit_text_car_rental_email);
         editTextCarRentalDescription=view.findViewById(R.id.edit_text_car_rental_description);
        editTextCarRentalConfirmation=view.findViewById(R.id.edit_text_car_rental_confirmation);

         carRentalSave=view.findViewById(R.id.carRentalSave);

        carRentalSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCarRental();
            }
        });

        editTextPickupDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextPickupDateTime);
            }
        });

        editTextDropOffDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextDropOffDateTime);
            }
        });
        return view;
    }

    private void saveCarRental() {

         String rentalAgencyText = editTextRentalAgency.getText().toString();
         String pickupDateTimeText = editTextPickupDateTime.getText().toString();
         String pickupLocationText = editTextPickupLocation.getText().toString();
         String carRentalPickupAddressText = editTextCarRentalPickupAddress.getText().toString();
         String carRentalPhoneText = editTextCarRentalPhone.getText().toString();
         String dropOffDateTimeText = editTextDropOffDateTime.getText().toString();
         String dropOffLocationText = editTextDropOffLocation.getText().toString();
         String dropOffAddressText = editTextDropOffAddress.getText().toString();
         String carRentalWebsiteText = editTextCarRentalWebsite.getText().toString();
         String carRentalEmailText = editTextCarRentalEmail.getText().toString();
         String carRentalDescriptionText = editTextCarRentalDescription.getText().toString();
         String carRentalConfirmation = editTextCarRentalConfirmation.getText().toString();

        if (pickupDateTimeText.isEmpty()) {
            Toast.makeText(getActivity(), " cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent carRentalData = new Intent();
        carRentalData.putExtra(EXTRA_RentalAgency,rentalAgencyText);
        carRentalData.putExtra(EXTRA_PickupDateTime,pickupDateTimeText);
        carRentalData.putExtra(EXTRA_PickupLocation,pickupLocationText);
        carRentalData.putExtra(EXTRA_CarRentalPickupAddress,carRentalPickupAddressText);
        carRentalData.putExtra(EXTRA_CarRentalPhone,carRentalPhoneText);
        carRentalData.putExtra(EXTRA_DropOffDateTime,dropOffDateTimeText);
        carRentalData.putExtra(EXTRA_DropOffLocation,dropOffLocationText);
        carRentalData.putExtra(EXTRA_DropOffAddress,dropOffAddressText);
        carRentalData.putExtra(EXTRA_CarRentalWebsite,carRentalWebsiteText);
        carRentalData.putExtra(EXTRA_CarRentalEmail,carRentalEmailText);
        carRentalData.putExtra(EXTRA_CarRentalDescription,carRentalDescriptionText);
        carRentalData.putExtra(EXTRA_CarRentalConfirmation,carRentalConfirmation);

        getActivity().setResult(EXTRA_Car_rental, carRentalData);
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
                        calendar.set(Calendar.SECOND,0);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                        startAlarm(calendar);
                    }
                };

                new TimePickerDialog(getActivity(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(getActivity(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void startAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager)  getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlertReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }
}