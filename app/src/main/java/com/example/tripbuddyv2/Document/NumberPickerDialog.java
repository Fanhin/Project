package com.example.tripbuddyv2.Document;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.tripbuddyv2.R;

import java.util.LinkedList;
import java.util.List;

public class NumberPickerDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String TAG = NumberPickerDialog.class.getSimpleName();
    private static final String ARG_REQUEST_CODE = "request_code_arg";
    private static final String ARG_SELECTION = "selection_arg";
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 100;
    private static final int INITIAL_VALUE = 10;

    public static void show(@NonNull FragmentManager fragmentManager, int requestCode, @Nullable List<Uri> selection) {
        NumberPickerDialog dialog = new NumberPickerDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_REQUEST_CODE, requestCode);
        if (selection != null) {
            args.putSerializable(ARG_SELECTION, new LinkedList<>(selection));
        }
        dialog.setArguments(args);
        dialog.show(fragmentManager, TAG);
    }

    private NumberPicker mNumberPicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mNumberPicker = new NumberPicker(getContext());
        mNumberPicker.setMinValue(MIN_VALUE);
        mNumberPicker.setMaxValue(MAX_VALUE);
        mNumberPicker.setValue(INITIAL_VALUE);
        mNumberPicker.setWrapSelectorWheel(true);
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.max_select_prompt)
                .setView(mNumberPicker)
                .setPositiveButton(android.R.string.ok, this)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        //noinspection unchecked
        MediaTypeFilterDialog.show(getFragmentManager(),
                getArguments().getInt(ARG_REQUEST_CODE),
                mNumberPicker.getValue(),
                (List<Uri>) getArguments().getSerializable(ARG_SELECTION));
    }
}
