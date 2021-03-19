package com.example.tripbuddyv2.Document;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.andremion.louvre.Louvre;
import com.example.tripbuddyv2.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static android.content.ContentValues.TAG;
public class MediaTypeFilterDialog extends DialogFragment implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnClickListener {

    private static final String ARG_REQUEST_CODE = "request_code_arg";
    private static final String ARG_MAX_SELECTION = "max_selection_arg";
    private static final String ARG_SELECTION = "selection_arg";

    public static void show(@NonNull FragmentManager fragmentManager, int requestCode, int maxSelection, @Nullable List<Uri> selection) {
        MediaTypeFilterDialog dialog = new MediaTypeFilterDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_REQUEST_CODE, requestCode);
        args.putInt(ARG_MAX_SELECTION, maxSelection);
        if (selection != null) {
            args.putSerializable(ARG_SELECTION, new LinkedList<>(selection));
        }
        dialog.setArguments(args);
        dialog.show(fragmentManager, TAG);
    }

    private final SparseBooleanArray mSelectedTypes;

    public MediaTypeFilterDialog() {
        mSelectedTypes = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.media_type_prompt)
                .setMultiChoiceItems(Louvre.IMAGE_TYPES, null, this)
                .setPositiveButton(android.R.string.ok, this)
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (isChecked) {
            mSelectedTypes.put(which, true);
        } else {
            mSelectedTypes.put(which, false);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        //noinspection WrongConstant,ConstantConditions,unchecked
        Louvre.init(getActivity())
                .setRequestCode(getArguments().getInt(ARG_REQUEST_CODE))
                .setMaxSelection(getArguments().getInt(ARG_MAX_SELECTION))
                .setSelection((List<Uri>) getArguments().get(ARG_SELECTION))
                .setMediaTypeFilter(parseToArray(mSelectedTypes))
                .open();
    }

    @NonNull
    private String[] parseToArray(@NonNull SparseBooleanArray selectedTypes) {
        List<String> selectedTypeList = new ArrayList<>();
        for (int i = 0; i < selectedTypes.size(); i++) {
            int key = selectedTypes.keyAt(i);
            if (selectedTypes.get(key, false)) {
                selectedTypeList.add(Louvre.IMAGE_TYPES[key]);
            }
        }
        String[] array = new String[selectedTypeList.size()];
        selectedTypeList.toArray(array);
        return array;
    }
}