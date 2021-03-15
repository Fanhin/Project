package com.example.tripbuddyv2.Expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tripbuddyv2.ListTrips.DialogAddTripsList;
import com.example.tripbuddyv2.ListTrips.ListTripActivity;
import com.example.tripbuddyv2.ListTrips.ListTrips;
import com.example.tripbuddyv2.ListTrips.ListTripsAdapter;
import com.example.tripbuddyv2.ListTrips.ListTripsViewModel;
import com.example.tripbuddyv2.MainActivity;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.TripViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class ExpenseActivity extends AppCompatActivity  {
     public static final int EXPENSE = 33;


    MaterialToolbar topAppBar;
    private ListTripsViewModel listTripsViewModel;
    private TripViewModel tripViewModel;
    public long totalExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setTitle("Expense");

        setSupportActionBar(topAppBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        topAppBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close));
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final RecyclerView expenseRecyclerView = findViewById(R.id.expense_recycler_view);
        expenseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseRecyclerView.setHasFixedSize(true);

        final ExpenseListTripAdapter expenseListAdapter = new ExpenseListTripAdapter();
        expenseRecyclerView.setAdapter(expenseListAdapter);
        tripViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TripViewModel.class);

        listTripsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ListTripsViewModel.class);
        listTripsViewModel.getAllListTrips().observe(this, new Observer<List<ListTrips>>() {
            @Override
            public void onChanged(List<ListTrips> listTrips) {

                expenseListAdapter.setListTrips(listTrips);
                Toast.makeText(ExpenseActivity.this, "ExpenseOnChange", Toast.LENGTH_SHORT).show();

            }
        });

        expenseListAdapter.setOnItemClickListener(new ExpenseListTripAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(ListTrips listTrips) {
//                Intent intentExpenseTrip = new Intent(ExpenseActivity.this, ExpenseSummeryActivity.class);
//                intentExpenseTrip.putExtra("idListTrip",listTrips.getIdListTrips());
//                Log.e("Id List Trip",String.valueOf(listTrips.getIdListTrips()));

//                startActivityForResult(intentExpenseTrip, EXPENSE);
                long idFk = listTrips.getIdListTrips();
                Log.e("idFk----",String.valueOf(idFk));
                long expenseTotal = tripViewModel.getTotalExpenseWithIdFk(idFk);
                Log.e("EXPENSE----",String.valueOf(expenseTotal));



                openExpenseDetailTripsDialog(expenseTotal);


            }
        });



    }

    private void calculateExpense(){

    }

    private void openExpenseDetailTripsDialog(long totalExpense) {
        DialogExpenseDetail dialogExpenseDetail =  new DialogExpenseDetail(totalExpense);
        dialogExpenseDetail.show(getSupportFragmentManager(),"dialog Expense Detail");
    }





}