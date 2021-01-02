package com.example.tripbuddyv2.BottomNav;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tripbuddyv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActivityActivity extends AppCompatActivity {

    public static final String EXTRA_ACTIVITY_TITLE =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_TITLE";
    public static final String EXTRA_ACTIVITY_DESTINATION =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_DESTINATION";
    public static final String EXTRA_ACTIVITY_DESCRIPTION =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_DESCRIPTION";
    public static final String EXTRA_ACTIVITY_START_DATE_TIME =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_START_DATE_TIME";
    public static final String EXTRA_ACTIVITY_END_DATE_TIME =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_END_DATE_TIME";
    public static final String EXTRA_ACTIVITY_ADDRESS =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_ADDRESS";
    public static final String EXTRA_ACTIVITY_PHONE =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_PHONE";
    public static final String EXTRA_WEBSITE_WEBSITE =
            "com.example.tripbuddyv2.EXTRA_WEBSITE_WEBSITE";
    public static final String EXTRA_ACTIVITY_EMAIL =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_EMAIL";
    public static final String EXTRA_PRIORITY =
            "com.example.tripbuddyv2.EXTRA_PRIORITY";

    private EditText editTextActivityTitle;
    private EditText editTextActivityDestination;
    private EditText editTextActivityDescription;
    private EditText editTextActivityStartDateTime;
    private EditText editTextActivityEndDateTime;
    private EditText editTextActivityAddress;
    private EditText editTextActivityPhone;
    private EditText editTextActivityWebsite;
    private EditText editTextActivityEmail;
    private NumberPicker editTextActivityPriority;

    private Button buttonSaveActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        editTextActivityTitle = findViewById(R.id.edit_text_activity_title);
        editTextActivityDestination = findViewById(R.id.edit_text_activity_destination);
        editTextActivityDescription= findViewById(R.id.edit_text_activity_description);
        editTextActivityStartDateTime= findViewById(R.id.edit_text_activity_start_date_time);
        editTextActivityEndDateTime= findViewById(R.id.edit_text_activity_end_date_time);
        editTextActivityAddress= findViewById(R.id.edit_text_activity_address);
        editTextActivityPhone= findViewById(R.id.edit_text_activity_phone);
        editTextActivityWebsite= findViewById(R.id.edit_text_activity_website);
        editTextActivityEmail= findViewById(R.id.edit_text_activity_email);
        editTextActivityPriority = findViewById(R.id.edit_text_activity_number_picker_priority);
        buttonSaveActivity = findViewById(R.id.activity_save_button);

        buttonSaveActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveActivity();
            }
        });

        editTextActivityStartDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextActivityStartDateTime);
            }
        });

        editTextActivityEndDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextActivityEndDateTime);
            }
        });





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

                new TimePickerDialog(ActivityActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(ActivityActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void saveActivity() {

        String activityTitle = editTextActivityTitle.getText().toString();
        String activityDestination = editTextActivityDestination.getText().toString();
        String activityDescription = editTextActivityDescription.getText().toString();
        String activityStartDateTime = editTextActivityStartDateTime.getText().toString();
        String activityEndDateTime = editTextActivityEndDateTime.getText().toString();
        String activityAddress = editTextActivityAddress.getText().toString();
        String activityPhone = editTextActivityPhone.getText().toString();
        String activityWebsite = editTextActivityWebsite.getText().toString();
        String activityEmail = editTextActivityEmail.getText().toString();
        int activityPriority = editTextActivityPriority.getValue();

        if (activityTitle.trim().isEmpty()) {
            Toast.makeText(this, "title cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent activityData = new Intent();
        activityData.putExtra(EXTRA_ACTIVITY_TITLE,activityTitle);
        activityData.putExtra(EXTRA_ACTIVITY_DESTINATION,activityDestination);
        activityData.putExtra(EXTRA_ACTIVITY_DESCRIPTION,activityDescription);
        activityData.putExtra(EXTRA_ACTIVITY_START_DATE_TIME,activityStartDateTime);
        activityData.putExtra(EXTRA_ACTIVITY_ADDRESS,activityAddress);
        activityData.putExtra(EXTRA_ACTIVITY_END_DATE_TIME,activityEndDateTime);
        activityData.putExtra(EXTRA_ACTIVITY_PHONE,activityPhone);
        activityData.putExtra(EXTRA_WEBSITE_WEBSITE,activityWebsite);
        activityData.putExtra(EXTRA_ACTIVITY_EMAIL,activityEmail);
        activityData.putExtra(EXTRA_PRIORITY,activityPriority);

        setResult(RESULT_OK, activityData);
        finish();


    }



}