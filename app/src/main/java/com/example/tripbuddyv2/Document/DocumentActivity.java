package com.example.tripbuddyv2.Document;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andremion.louvre.home.GalleryActivity;
import com.andremion.louvre.util.ItemOffsetDecoration;
import com.example.tripbuddyv2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DocumentActivity extends AppCompatActivity {
    private static final int LOUVRE_REQUEST_CODE = 0;

    private DocumentAdapter mAdapter;
    private List<Uri> mSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final int spacing = getResources().getDimensionPixelSize(R.dimen.gallery_item_offset);
        final RecyclerView recyclerView =  findViewById(R.id.document_recycler_view);
        recyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter = new DocumentAdapter());
        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                int size = getResources().getDimensionPixelSize(R.dimen.gallery_item_size);
                int width = recyclerView.getMeasuredWidth();
                int columnCount = width / (size + spacing);
                recyclerView.setLayoutManager(new GridLayoutManager(DocumentActivity.this, columnCount));
                return false;
            }
        });

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberPickerDialog.show(getSupportFragmentManager(), LOUVRE_REQUEST_CODE, mSelection);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //noinspection unchecked
        mSelection = (List<Uri>) getLastCustomNonConfigurationInstance();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mSelection;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOUVRE_REQUEST_CODE && resultCode == RESULT_OK) {
            mAdapter.swapData(mSelection = GalleryActivity.getSelection(data));
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
