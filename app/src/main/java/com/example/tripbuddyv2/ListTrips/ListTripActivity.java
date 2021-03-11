package com.example.tripbuddyv2.ListTrips;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripbuddyv2.AddTripActivity;
import com.example.tripbuddyv2.BottomNav.LodgingEditActivity;
import com.example.tripbuddyv2.MainActivity;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.TransportationSpinner;
import com.example.tripbuddyv2.Trip;
import com.example.tripbuddyv2.TripAdapter;
import com.example.tripbuddyv2.TripDatabase;
import com.example.tripbuddyv2.TripViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListTripActivity extends AppCompatActivity implements DialogAddTripsList.DialogAddTripsListListener {
    private ListTripsViewModel listTripsViewModel;
    private List<Trip> trips = new ArrayList<>();

    //init variable for drawer
    DrawerLayout drawerLayout;
    ActionBar actionBar;


    public static final int LISTTRIP = 22;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);
        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_trip_list);
        drawerLayout = findViewById(R.id.drawer_layout);



        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTripsDialog();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_list_trips);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ListTripsAdapter listTripsAdapter = new ListTripsAdapter();
        recyclerView.setAdapter(listTripsAdapter);


        listTripsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ListTripsViewModel.class);
        listTripsViewModel.getAllListTrips().observe(this, new Observer<List<ListTrips>>() {
            @Override
            public void onChanged(List<ListTrips> listTrips) {

                listTripsAdapter.setListTrips(listTrips);
                Toast.makeText(ListTripActivity.this, "OnChange", Toast.LENGTH_SHORT).show();

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(viewHolder.itemView.getContext())
                        .setMessage("Are you sure ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listTripsViewModel.delete(listTripsAdapter.getListTripsAt(viewHolder.getAdapterPosition()));
                                listTripsViewModel.deleteAllTripsWithIdFK(listTripsAdapter.getListTripsAt(viewHolder.getAdapterPosition()));
                                Toast.makeText(ListTripActivity.this, "Trip deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listTripsAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                            }
                        })
                        .create()
                        .show();

                //tripViewModel.delete(adapter.getTripAt(viewHolder.getAdapterPosition()));


            }
        }).attachToRecyclerView(recyclerView);




        listTripsAdapter.setOnItemClickListener(new ListTripsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListTrips listTrips) {
                Intent intentTripTimeline = new Intent(ListTripActivity.this, MainActivity.class);
                intentTripTimeline.putExtra("idListTrip",listTrips.getIdListTrips());
                Log.e("Id List Trip",String.valueOf(listTrips.getIdListTrips()));

                startActivityForResult(intentTripTimeline, LISTTRIP);
            }

        });

        //Drawer


    }



    public void ClickMenu(View view){
        //open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Close drawer
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        recreate();
    }

    public void ClickDashboard(View view){
        redirectActivity(this, AddTripActivity.class);
    }

    public void ClickAboutUs(View view){
        redirectActivity(this, TransportationSpinner.class);
    }
    private static void logout(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Logout");

        builder.setMessage("Are you sure");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishAffinity();
                System.exit(0);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }




    private static void redirectActivity(Activity activity,Class aClass){
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    private void openAddTripsDialog() {
        DialogAddTripsList dialogAddTripsList =  new DialogAddTripsList();
        dialogAddTripsList.show(getSupportFragmentManager(),"Add trips Dialog");
    }

    @Override
    public void applyTexts(String tripsName, String tripsStartDate, String tripsEndDate, String tripsDescription) {

        ListTrips listTrips = new ListTrips(tripsName,tripsStartDate,tripsEndDate,tripsDescription);
        //ListTripsWithTrip listTripsWithTrip = new ListTripsWithTrip(new ListTrips(tripsName,tripsStartDate,tripsEndDate,tripsDescription),trips);
        //listTripsViewModel.insertListTripWithTrip(listTripsWithTrip);

        listTripsViewModel.insertListTrip(listTrips);
       

    }


}
