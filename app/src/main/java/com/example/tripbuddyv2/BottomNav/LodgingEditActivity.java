package com.example.tripbuddyv2.BottomNav;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.TripAdapter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public static final String EXTRA_LODGING_IMAGE_PATH1 =
            "com.example.tripbuddyv2.EXTRA_LODGING_IMAGE_PATH1";
    public static final String EXTRA_LODGING_IMAGE_PATH2 =
            "com.example.tripbuddyv2.EXTRA_LODGING_IMAGE_PATH2";
    public static final String EXTRA_LODGING_IMAGE_PATH3 =
            "com.example.tripbuddyv2.EXTRA_LODGING_IMAGE_PATH3";
    public static final String EXTRA_LODGING_ARRAY_OF_IMAGE =
            "com.example.tripbuddyv2.EXTRA_LODGING_ARRAY_OF_IMAGE";



    private EditText editTextLodgingTitle;
    private EditText editTextLodgingCheckInDateTime;
    private EditText editTextLodgingCheckOutDateTime;
    private EditText editTextLodgingDescription;
    private EditText editTextLodgingAddress;
    private EditText editTextLodgingPhone;
    private EditText editTextLodgingWebsite;
    private EditText editTextLodgingEmail;

    private Button buttonSaveLodging;

    private ImageSwitcher imagesIS;
    private Button previousBtn,nextBtn,pickImagesBtn;

    private ArrayList<Uri> imageUris;
    private ArrayList<String> imageUrisPath;
    private ArrayList<String> imageUrisPath2;

    public static final int PICK_IMAGES_LODGING_CODE =0;

    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodging);



        imagesIS = findViewById(R.id.imageLodgingIs);
        previousBtn = findViewById(R.id.previousLodgingBtn);
        nextBtn = findViewById(R.id.nextLodgingBtn);
        pickImagesBtn = findViewById(R.id.pickImagesLodgingBtn);


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
                    Toast.makeText(LodgingEditActivity.this, "No Previous image", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(LodgingEditActivity.this, "No more image",  Toast.LENGTH_SHORT).show();
                }

            }
        });

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
            editTextLodgingDescription.setText(intent.getStringExtra(EXTRA_LODGING_DESCRIPTION));
            editTextLodgingCheckInDateTime.setText(intent.getStringExtra(EXTRA_LODGING_CHECK_IN_DATE_TIME));
            editTextLodgingCheckOutDateTime.setText(intent.getStringExtra(EXTRA_LODGING_CHECK_OUT_DATE_TIME));
            editTextLodgingAddress.setText(intent.getStringExtra(EXTRA_LODGING_ADDRESS));
            editTextLodgingPhone.setText(intent.getStringExtra(EXTRA_LODGING_PHONE));
            editTextLodgingWebsite.setText(intent.getStringExtra(EXTRA_LODGING_WEBSITE));
            editTextLodgingEmail.setText(intent.getStringExtra(EXTRA_LODGING_EMAIL));

            Log.e("path from edit",getIntent().getExtras().getStringArrayList((EXTRA_LODGING_ARRAY_OF_IMAGE)).toString());
            if (!getIntent().getExtras().getStringArrayList(EXTRA_LODGING_ARRAY_OF_IMAGE).isEmpty()){
                imageUrisPath2 = getIntent().getExtras().getStringArrayList(EXTRA_LODGING_ARRAY_OF_IMAGE);
                Log.e("title ",intent.getStringExtra(EXTRA_LODGING_TITLE));
                Log.e("path on edit lodging",imageUrisPath2.toString());
                restoreImage(imageUrisPath2);

            }
        } else {
            setTitle("Add Lodging ");

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




    private void pickImageIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent,"select Image(s)"),PICK_IMAGES_LODGING_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGES_LODGING_CODE){
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
                    else  {
                        //pick single image
                        Uri imageUri =data.getData();
                        //save uri as str to imageUrisPath
                        imageUrisPath.add(imageUri.toString());
                        //imageUrisPath.add(imageUri.toString());
                        imageUris.add(imageUri);

                    }
                    //set uri to Switch
                    //imagesIS.setImageURI(Uri.parse(imageUrisPath.get(0)));
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
        //Bundle lodgingImageBundle = new Bundle();
        //lodgingImageBundle.putSerializable("imageUrisPathBundle", imageUrisPath);


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
        //lodgingData.putExtra("imageUrisPath",lodgingImageBundle);
        lodgingData.putStringArrayListExtra("strLodgingImagePath",imageUrisPath);




        int id = getIntent().getIntExtra(EXTRA_LODGING_ID,-1);
        if (id != -1){
            lodgingData.putExtra(EXTRA_LODGING_ID,id);
            //lodgingData.putStringArrayListExtra(EXTRA_LODGING_ARRAY_OF_IMAGE,imageUrisPath);
        }


        setResult(RESULT_OK, lodgingData);
        finish();
    }

}