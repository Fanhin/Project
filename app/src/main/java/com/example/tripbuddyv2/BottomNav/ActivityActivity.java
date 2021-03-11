package com.example.tripbuddyv2.BottomNav;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
    public static final String EXTRA_ACTIVITY_ID_FK =
            "com.example.tripbuddyv2.EXTRA_ACTIVITY_ID_FK";


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

   String activityImagePath1;
    String activityImagePath2;
    String activityImagePath3;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;


    private ImageSwitcher imagesIS;
    private Button previousBtn,nextBtn,pickImagesBtn;

    private ArrayList<Uri> imageUris;
    private ArrayList<String> imageUrisPath;
    private ArrayList<String> imageUrisPath2;

    public static final int PICK_IMAGES_ACTIVITY_CODE =0;

    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        imagesIS = findViewById(R.id.imageActivityIs);
        previousBtn = findViewById(R.id.previousActivityBtn);
        nextBtn = findViewById(R.id.nextActivityBtn);
        pickImagesBtn = findViewById(R.id.pickImagesActivityBtn);


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
        editTextActivityPriority.setMinValue(1);
        editTextActivityPriority.setMaxValue(10);
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
        if (intent.hasExtra(EXTRA_ACTIVITY_ID)&&intent.hasExtra(EXTRA_ACTIVITY_ID_FK)) {
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

//           if (!getIntent().getExtras().getStringArrayList(EXTRA_ACTIVITY_ARRAY_OF_IMAGE).isEmpty()){
//               imageUrisPath2 = getIntent().getExtras().getStringArrayList(EXTRA_ACTIVITY_ARRAY_OF_IMAGE);
//               restoreImage(imageUrisPath2);
//           }
            Log.e("path from edit",getIntent().getExtras().getStringArrayList((EXTRA_ACTIVITY_ARRAY_OF_IMAGE)).toString());

            if (!getIntent().getExtras().getStringArrayList(EXTRA_ACTIVITY_ARRAY_OF_IMAGE).isEmpty()){
                imageUrisPath2 = getIntent().getExtras().getStringArrayList(EXTRA_ACTIVITY_ARRAY_OF_IMAGE);
                Log.e("title ",intent.getStringExtra(EXTRA_ACTIVITY_TITLE));
                Log.e("path on edit lodging",imageUrisPath2.toString());
                restoreImage(imageUrisPath2);

            }
        } else {
            setTitle("Add Activity ");

        }
    }

    private void restoreImage(ArrayList<String> imageUrisPath) {
        if (imageUrisPath != null){
            //picked multiple images
            int count = imageUrisPath.size();
            for (int i=0;i<count;i++){
                //get image uri at specific index
                Uri imageUri = Uri.parse(imageUrisPath.get(i));
                //save uri as str to imageUrisPath
                //imageUrisPath.add(imageUri.toString());
                imageUris.add(imageUri);
            }
        }
        else {

            //pick single image
            Uri imageUri = Uri.parse(imageUrisPath.get(0));
            //imageUrisPath.add(imageUri.toString());
            imageUris.add(imageUri);
            //save uri as str to imageUrisPath
            //imageUrisPath.add(imageUri.toString());

        }
        //set uri to Switch
        //imagesIS.setImageURI(Uri.parse(imageUrisPath.get(0)));
        imagesIS.setImageURI(imageUris.get(0));
        position = 0;
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
                if (imageUrisPath2 != null){
                    Log.e("image2",imageUrisPath2.toString());
                    for (int j=0 ; j < imageUrisPath2.size();j++){
                        imageUrisPath.add(imageUrisPath2.get(j));
                        imageUris.add(Uri.parse(imageUrisPath.get(j)));
                    }
                }
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
                Log.e("data in onAcitivityResu",imageUrisPath.toString());
                imagesIS.setImageURI(imageUris.get(0));
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
                        calendar.set(Calendar.SECOND,0);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                        startAlarm(calendar);
                    }
                };

                new TimePickerDialog(ActivityActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(ActivityActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void startAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
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
        activityData.putStringArrayListExtra("strActivityImagePath",imageUrisPath);

        int id = getIntent().getIntExtra(EXTRA_ACTIVITY_ID,-1);
       long idFk = getIntent().getLongExtra(EXTRA_ACTIVITY_ID_FK,-2);
        Log.e("id fk after update",String.valueOf(idFk));
        if (id != -1 ){
            activityData.putExtra(EXTRA_ACTIVITY_ID,id);

            activityData.putExtra(EXTRA_ACTIVITY_ID_FK,idFk);
        }



        setResult(RESULT_OK, activityData);
        finish();


    }





}