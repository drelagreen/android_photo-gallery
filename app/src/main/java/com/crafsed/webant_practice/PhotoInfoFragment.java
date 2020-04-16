package com.crafsed.webant_practice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PhotoInfoFragment extends Fragment {
    private MainActivityPresenter mPresenter;
    private ImageView mImage;
    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private String mName;
    private String mDescription;
    private String mURL;
    private ImageButton mBackButton;
    private boolean mViewType;

    PhotoInfoFragment(MainActivityPresenter presenter, boolean type){
        mPresenter = presenter;
        mViewType = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(mViewType ? R.layout.fragment_photo_info_new : R.layout.fragment_photo_info_popular, container, false);
        setRetainInstance(true);

        mImage = v.findViewById(mViewType ? R.id.image_view_holder_new : R.id.image_view_holder_popular);
        mNameTextView = v.findViewById(mViewType ? R.id.name_text_new : R.id.name_text_popular);
        mDescriptionTextView = v.findViewById(mViewType ? R.id.description_text_new : R.id.description_text_popular);
        mBackButton = v.findViewById(mViewType ? R.id.back_button_new : R.id.back_button_popular);

        Glide.with(v)
                .load(Uri.parse(mURL))
                .error(R.drawable.close)
                .thumbnail(Glide.with(v).load(R.drawable.loading4))
                .into(mImage);
        mNameTextView.setText(mName);
        mDescriptionTextView.setText(mDescription);
        mBackButton.setOnClickListener(v1 -> {
            mPresenter.removePhotoFragment(mViewType);
        });
        return v;
    }

    void setNameOfPhoto(String name){
        mName = name;
    }
    void setDescription(String description){
        mDescription = description;
    }
    void setImage(String url){
        mURL = url;
    }


}