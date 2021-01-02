package com.example.tripbuddyv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tripbuddyv2.DateTimeFragment.DatePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTripActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.tripbuddyv2.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.tripbuddyv2.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.tripbuddyv2.EXTRA_PRIORITY";
    public static final String EXTRA_START_DATE =
            "com.example.tripbuddyv2.EXTRA_START_DATE";
    public static final String EXTRA_START_TIME =
            "com.example.tripbuddyv2.  EXTRA_START_TIME";
    public static final String EXTRA_START_DATE_TIME =
            "com.example.tripbuddyv2.  EXTRA_START_DATE_TIME";


    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    private EditText editTextStartDate;
    private EditText editTextStartTime;
    private EditText editTextStartDateTime;

    TimePickerDialog timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);


        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        editTextStartDateTime = findViewById(R.id.edit_text_start_date_time);

        editTextStartDate = findViewById(R.id.edit_text_start_date);
        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        editTextStartTime = findViewById(R.id.edit_text_start_time);
        editTextStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogFragment timePicker = new TimePickerFragment();
//                timePicker.show(getSupportFragmentManager(), "time picker");
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minutes = c.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(AddTripActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int sHour, int sMinute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, sHour);
                        c.set(Calendar.MINUTE, sMinute);
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        String selectedTime = timeFormat.format(c.getTime());


                        editTextStartTime.setText(selectedTime);
                    }
                }, hour, minutes, true);
                timePicker.show();
            }
        });

        editTextStartDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextStartDateTime);
            }
        });


        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Trip ");


    }

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(AddTripActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
            }
        };

        new DatePickerDialog(AddTripActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }



    private void saveTrip() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();
        String startDate = editTextStartDate.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String startDateTime = editTextStartDateTime.getText().toString();


        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        data.putExtra(EXTRA_START_DATE, startDate);
        data.putExtra(EXTRA_START_TIME, startTime);
        data.putExtra(EXTRA_START_DATE_TIME,startDateTime);


        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_trip_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_trip:
                saveTrip();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}