package com.crafsed.webant_practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crafsed.webant_practice.R;

public class ImageHolder extends RecyclerView.ViewHolder{
    public ImageView mImageView;

    public ImageHolder(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.itemImage);
    }
//    public ImageHolder(LayoutInflater inflater, ViewGroup parent) {
//        super(inflater.inflate(R.layout.view_item_rw, parent, false));
//        mImageView = itemView.findViewById(R.id.itemImage);
//    }

}