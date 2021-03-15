package com.example.tripbuddyv2.Expense;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripbuddyv2.ListTrips.DialogAddTripsList;
import com.example.tripbuddyv2.R;
import com.example.tripbuddyv2.TripAdapter;
import com.example.tripbuddyv2.TripViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

public class DialogExpenseDetail extends AppCompatDialogFragment {


    long expense;
    TextView textViewTotal;


    public DialogExpenseDetail( long expense) {

    this.expense = expense;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_expense_detail_trips,null);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_trips);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final ExpenseDetailAdapter expenseDetailAdapter = new ExpenseDetailAdapter();

        //adapter
        recyclerView.setAdapter(expenseDetailAdapter);

        textViewTotal = view.findViewById(R.id.text_view_expense_total);
        textViewTotal.setText(String.valueOf(expense));
        builder.setView(view)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });
        return builder.create();
    }







}
