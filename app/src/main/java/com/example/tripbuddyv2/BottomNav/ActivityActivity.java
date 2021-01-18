package com.example.tripbuddyv2.BottomNav;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.TripAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ActivityActivity extends AppCompatActivity {
    public static final String EXTRA_ACTIVITY_ID =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_ID";

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
    public static final String EXTRA_ACTIVITY_WEBSITE =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_WEBSITE";
    public static final String EXTRA_ACTIVITY_EMAIL =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_EMAIL";
    public static final String EXTRA_ACTIVITY_PRIORITY =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_PRIORITY";
    public static final String EXTRA_ACTIVITY_IMAGE_PATH1 =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_IMAGE_PATH1";
    public static final String EXTRA_ACTIVITY_IMAGE_PATH2 =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_IMAGE_PATH2";
    public static final String EXTRA_ACTIVITY_IMAGE_PATH3 =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_IMAGE_PATH3";
    public static final String EXTRA_ACTIVITY_ARRAY_OF_IMAGE =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_ARRAY_OF_IMAGE";



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
    private Button buttonAddImagePath;
    public static final int PICK_IMAGES_ACTIVITY_CODE =0;
   String activityImagePath1;
    String activityImagePath2;
    String activityImagePath3;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    TripAdapter argActivityType;

    private ImageSwitcher imagesIS;
    private Button previousBtn,nextBtn,pickImagesBtn;
    private ArrayList<Uri> imageUris;
    private ArrayList<String> imageUrisPath;



    int position = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        imagesIS = findViewById(R.id.imageActivityIs);
        previousBtn = findViewById(R.id.previousActivityBtn);
        nextBtn = findViewById(R.id.nextActivityBtn);
        pickImagesBtn = findViewById(R.id.pickImagesActivityBtn);

        if(savedInstanceState != null){
            imageUris = savedInstanceState.getParcelableArrayList("imageUri");
            position = savedInstanceState.getInt("position");

        }

        //init list
        imageUris = new ArrayList<>();
        imageUrisPath = new ArrayList<>();

        //setup image switcher
        imagesIS.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });

        pickImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageIntent();
            }
        });


        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >0){
                    position--;
                    imagesIS.setImageURI(imageUris.get(position));
                }else {
                    Toast.makeText(ActivityActivity.this, "No Previous image", Toast.LENGTH_SHORT).show();
                }


            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < imageUris.size()-1){
                    position++;
                    imagesIS.setImageURI(imageUris.get(position));

                }else{
                    Toast.makeText(ActivityActivity.this, "No more image",  Toast.LENGTH_SHORT).show();
                }

            }
        });




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

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ACTIVITY_ID)) {
            setTitle("Edit Activity");
            editTextActivityTitle.setText(intent.getStringExtra(EXTRA_ACTIVITY_TITLE));
            editTextActivityDestination.setText(intent.getStringExtra(EXTRA_ACTIVITY_DESTINATION));
            editTextActivityDescription.setText(intent.getStringExtra(EXTRA_ACTIVITY_DESCRIPTION));
            editTextActivityStartDateTime.setText(intent.getStringExtra(EXTRA_ACTIVITY_START_DATE_TIME));
            editTextActivityEndDateTime.setText(intent.getStringExtra(EXTRA_ACTIVITY_END_DATE_TIME));
            editTextActivityAddress.setText(intent.getStringExtra(EXTRA_ACTIVITY_ADDRESS));
            editTextActivityPhone.setText(intent.getStringExtra(EXTRA_ACTIVITY_PHONE));
            editTextActivityWebsite.setText(intent.getStringExtra(EXTRA_ACTIVITY_WEBSITE));
            editTextActivityEmail .setText(intent.getStringExtra(EXTRA_ACTIVITY_EMAIL));
            editTextActivityPriority.setValue(intent.getIntExtra(EXTRA_ACTIVITY_PRIORITY, 1));



        } else {
            setTitle("Add Activity ");

        }







    }

    private void pickImageIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent,"select Image(s)"),PICK_IMAGES_ACTIVITY_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGES_ACTIVITY_CODE){
            if (resultCode == Activity.RESULT_OK){
                if (data.getClipData() != null){
                    //picked multiple images
                    int count = data.getClipData().getItemCount();
                    for (int i=0;i<count;i++){
                        //get image uri at specific index
                        Uri imageUri =data.getClipData().getItemAt(i).getUri();
                        //save uri as str to imageUrisPath
                        imageUrisPath.add(imageUri.toString());
                        imageUris.add(imageUri);
                    }

                }
                else {
                    //pick single image
                    Uri imageUri =data.getData();
                    imageUrisPath.add(imageUri.toString());
                    imageUris.add(imageUri);

                }
                //set uri to Switch
                imagesIS.setImageURI(Uri.parse("content://com.android.providers.media.documents/document/image%3A2448"));
                //imagesIS.setImageURI(imageUris.get(0));
                position = 0;
            }
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
        activityData.putExtra(EXTRA_ACTIVITY_WEBSITE,activityWebsite);
        activityData.putExtra(EXTRA_ACTIVITY_EMAIL,activityEmail);
        activityData.putExtra(EXTRA_ACTIVITY_PRIORITY,activityPriority);
        activityData.putExtra(EXTRA_ACTIVITY_IMAGE_PATH1,activityImagePath1);
        activityData.putExtra(EXTRA_ACTIVITY_IMAGE_PATH2,activityImagePath2);
        activityData.putExtra(EXTRA_ACTIVITY_IMAGE_PATH3,activityImagePath3);

        int id = getIntent().getIntExtra(EXTRA_ACTIVITY_ID,-1);
        if (id != -1){
            activityData.putExtra(EXTRA_ACTIVITY_ID,id);
        }



        setResult(RESULT_OK, activityData);
        finish();


    }





}