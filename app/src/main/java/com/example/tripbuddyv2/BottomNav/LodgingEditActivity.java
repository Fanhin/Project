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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tripbuddyv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LodgingEditActivity extends AppCompatActivity {
    public static final String EXTRA_LODGING_ID =
            "com.example.tripbuddyv2.EXTRA_LODGING_ID";

    public static final String EXTRA_LODGING_TITLE =
            "com.example.tripbuddyv2.EXTRA_LODGING_TITLE";
    public static final String EXTRA_LODGING_CHECK_IN_DATE_TIME =
            "com.example.tripbuddyv2.EXTRA_LODGING_CHECK_IN_DATE_TIME";
    public static final String EXTRA_LODGING_CHECK_OUT_DATE_TIME =
            "com.example.tripbuddyv2.EXTRA_LODGING_CHECK_OUT_DATE_TIME";
    public static final String EXTRA_LODGING_DESCRIPTION =
            "com.example.tripbuddyv2.EXTRA_LODGING_DESCRIPTION";
    public static final String EXTRA_LODGING_ADDRESS =
            "com.example.tripbuddyv2.EXTRA_LODGING_ADDRESS";
    public static final String EXTRA_LODGING_PHONE =
            "com.example.tripbuddyv2.EXTRA_LODGING_PHONE";
    public static final String EXTRA_LODGING_WEBSITE =
            "com.example.tripbuddyv2.EXTRA_LODGING_WEBSITE";
    public static final String EXTRA_LODGING_EMAIL =
            "com.example.tripbuddyv2.EXTRA_LODGING_EMAIL";

    private EditText editTextLodgingTitle;
    private EditText editTextLodgingCheckInDateTime;
    private EditText editTextLodgingCheckOutDateTime;
    private EditText editTextLodgingDescription;
    private EditText editTextLodgingAddress;
    private EditText editTextLodgingPhone;
    private EditText editTextLodgingWebsite;
    private EditText editTextLodgingEmail;

    private Button buttonSaveLodging;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodging);

        editTextLodgingTitle = findViewById(R.id.edit_text_lodging_title);
        editTextLodgingCheckInDateTime = findViewById(R.id.edit_text_lodging_check_in_date_time);
        editTextLodgingCheckOutDateTime = findViewById(R.id.edit_text_lodging_check_out_date_time);
        editTextLodgingDescription = findViewById(R.id.edit_text_lodging_description);
        editTextLodgingAddress = findViewById(R.id.edit_text_lodging_address);

        editTextLodgingPhone = findViewById(R.id.edit_text_lodging_phone);
        editTextLodgingWebsite = findViewById(R.id.edit_text_lodging_website);
        editTextLodgingEmail = findViewById(R.id.edit_text_lodging_email);
        buttonSaveLodging = findViewById(R.id.lodging_save_button);

        buttonSaveLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveLodging();
            }
        });


        editTextLodgingCheckInDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextLodgingCheckInDateTime);
            }
        });

        editTextLodgingCheckOutDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(editTextLodgingCheckOutDateTime);
            }
        });




        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_LODGING_ID)) {
            setTitle("Edit Lodging");
            editTextLodgingTitle.setText(intent.getStringExtra(EXTRA_LODGING_TITLE));
            editTextLodgingCheckInDateTime.setText(intent.getStringExtra(EXTRA_LODGING_CHECK_IN_DATE_TIME));
            editTextLodgingCheckOutDateTime.setText(intent.getStringExtra(EXTRA_LODGING_CHECK_OUT_DATE_TIME));
            editTextLodgingAddress.setText(intent.getStringExtra(EXTRA_LODGING_ADDRESS));

        } else {
            setTitle("Add Lodging ");

        }



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

                new TimePickerDialog(LodgingEditActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(LodgingEditActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }


    private void saveLodging() {
        String lodgingTitle = editTextLodgingTitle.getText().toString();
        String lodgingCheckInDateTime = editTextLodgingCheckInDateTime.getText().toString();
        String lodgingCheckOutDateTime = editTextLodgingCheckOutDateTime.getText().toString();
        String lodgingDescription = editTextLodgingDescription.getText().toString();
        String lodgingAddress = editTextLodgingAddress.getText().toString();
        String lodgingPhone = editTextLodgingPhone.getText().toString();
        String lodgingWebsite = editTextLodgingWebsite.getText().toString();
        String lodgingEmail = editTextLodgingEmail.getText().toString();

        if (lodgingTitle.trim().isEmpty()) {
            Toast.makeText(this, "title cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent lodgingData = new Intent();
        lodgingData.putExtra(EXTRA_LODGING_TITLE, lodgingTitle);
        lodgingData.putExtra(EXTRA_LODGING_CHECK_IN_DATE_TIME, lodgingCheckInDateTime);
        lodgingData.putExtra(EXTRA_LODGING_CHECK_OUT_DATE_TIME, lodgingCheckOutDateTime);
        lodgingData.putExtra(EXTRA_LODGING_DESCRIPTION, lodgingDescription);
        lodgingData.putExtra(EXTRA_LODGING_ADDRESS, lodgingAddress);
        lodgingData.putExtra(EXTRA_LODGING_PHONE, lodgingPhone);
        lodgingData.putExtra(EXTRA_LODGING_WEBSITE, lodgingWebsite);
        lodgingData.putExtra(EXTRA_LODGING_EMAIL, lodgingEmail);

        int id = getIntent().getIntExtra(EXTRA_LODGING_ID,-1);
        if (id != -1){
            lodgingData.putExtra(EXTRA_LODGING_ID,id);
        }


        setResult(RESULT_OK, lodgingData);
        finish();
    }

}