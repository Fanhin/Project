package com.example.tripbuddyv2.BottomNav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tripbuddyv2.MainActivity;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.Trip;
import com.example.tripbuddyv2.TripAdapter;

public class TransportationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String TRANSPORTATION_EXTRA_TITLE =
            "com.example.tripbuddyv2.EXTRA_TITLE";
    public static final String TRANSPORTATION_EXTRA_DESCRIPTION =
            "com.example.tripbuddyv2.EXTRA_DESCRIPTION";
    public static final String TRANSPORTATION_EXTRA_PRIORITY =
            "com.example.tripbuddyv2.EXTRA_PRIORITY";
    public static final String TRANSPORTATION_EXTRA_START_DATE =
            "com.example.tripbuddyv2.EXTRA_START_DATE_TIME";

    public EditText editTextTransportationDateTime;
    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);
        editTextTransportationDateTime = findViewById(R.id.edit_text_transportation_departure_date_time);

         spinner = findViewById(R.id.TransportationSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.transportation_menu,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);







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

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String textSelected = adapterView.getItemAtPosition(i).toString();
        if (i == 1){
            editTextTransportationDateTime.setVisibility(View.GONE);
        } else {
            Toast.makeText(adapterView.getContext(), textSelected, Toast.LENGTH_SHORT).show();

        }
//        if (textSelected == "Airplane"){
//            return;
//
//        }else if(textSelected == "Train"){
//            view.setVisibility(View.INVISIBLE);
//
//        }else if(textSelected == "Bus" ){
//            return;
//
//        }else if(textSelected =="Boat" ){
//            return;
//
//        }else if(textSelected =="Car Rental" ){
//            return;
//
//        }


        //Toast.makeText(adapterView.getContext(), textSelected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


//Init and assign variable

//BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        editTextHome = findViewById(R.id.edt_home);
//        saveHomeBtn = findViewById(R.id.btn_home);
//        saveHomeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (editTextHome.getText().toString().trim().isEmpty()){
//                    Toast.makeText(TransportationActivity.this, "cant empty", Toast.LENGTH_SHORT).show();
//                }else {
//                    String edtHomeText = editTextHome.getText().toString();
//
//                    Intent intent = new Intent();
//                    intent.putExtra("text",edtHomeText);
//                    setResult(RESULT_OK,intent);
//                    finish();
//
//                }
//            }
//        });


//set Home selected
//        bottomNavigationView.setSelectedItemId(R.id.home);
//
//        //perform ItemSelectedListener
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.dashboard:
//                        startActivity(new Intent(getApplicationContext(),
//                                DashBoard.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.home:
//                        return true;
//                    case R.id.about:
//                        startActivity(new Intent(getApplicationContext(),
//                                About.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                }
//                return false;
//
//            }
//        });

