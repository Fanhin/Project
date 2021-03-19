package com.example.tripbuddyv2.Document;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.example.tripbuddyv2.R;

import java.util.ArrayList;
import java.util.List;


public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {

    private final List<Uri> mData;

    DocumentAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_main, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Uri data = mData.get(position);
        Glide.with(holder.mImageView.getContext())
                .load(data)
                .apply(RequestOptions.skipMemoryCacheOf(true)
                        .centerCrop()
                        .placeholder(com.andremion.louvre.R.color.gallery_item_background))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    void swapData(@Nullable List<Uri> data) {
        if (!mData.equals(data)) {
            mData.clear();
            if (data != null) {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        private ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView;
        }
    }

}
