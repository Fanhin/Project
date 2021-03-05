package com.example.tripbuddyv2.ListTrips;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tripbuddyv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DialogAddTripsList extends AppCompatDialogFragment {

    private EditText editTextTripsName;
    private EditText editTextTripsStartDate;
    private EditText editTextTripsEndDate;
    private EditText editTextTripsDescription;
    private DialogAddTripsListListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_trips_list,null);

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tripsName = editTextTripsName.getText().toString();
                        String tripsStartDate = editTextTripsStartDate.getText().toString();
                        String tripsEndDate = editTextTripsEndDate.getText().toString();
                        String tripsDescription = editTextTripsDescription.getText().toString();

                        listener.applyTexts(tripsName,tripsStartDate,tripsEndDate,tripsDescription);
                    }
                });

        editTextTripsName= view.findViewById(R.id.edit_text_trips_list_name);
        editTextTripsStartDate = view.findViewById(R.id.edit_text_trips_list_start_date_trips);
        editTextTripsEndDate = view.findViewById(R.id.edit_text_trips_list_end_date_trips);
        editTextTripsDescription = view.findViewById(R.id.edit_text_trips_list_description);

        editTextTripsStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextTripsStartDate);
            }
        });

        editTextTripsEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextTripsEndDate);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach( Context context) {
        super.onAttach(context);

        try {
            listener = (DialogAddTripsListListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement ExampleDialogListener");

        }
    }
    public interface DialogAddTripsListListener {
        void applyTexts(String tripsName,String tripsStartDate,String tripsEndDate,String tripsDescription);
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
                    }
                };

                new TimePickerDialog(getActivity(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(getActivity(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}
