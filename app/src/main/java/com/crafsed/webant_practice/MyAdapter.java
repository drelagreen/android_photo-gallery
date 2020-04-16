package com.crafsed.webant_practice;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MyAdapter extends RecyclerView.Adapter<ImageHolder>{
    private List <DataItem> data;
    private RecyclerView mRecyclerView;
    MainActivityPresenter mPresenter;
    boolean mViewType;
    MyAdapter(RecyclerView context, MainActivityPresenter presenter, boolean type){
        mRecyclerView = context;
        data = new ArrayList<>();
        mPresenter = presenter;
        mViewType = type;
    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mRecyclerView.getContext());
        View v  = layoutInflater.inflate(R.layout.view_item_rw, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mRecyclerView.getChildLayoutPosition(v);
                startPhotoFragment(data.get(position), mViewType);
            }
        });
        return new ImageHolder(v);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Glide.with(mPresenter.getActivity())
                .load(Uri.parse(data.get(position).getImage()))
                .centerCrop()
                .thumbnail(Glide.with(mPresenter.getActivity()).load(R.drawable.loading4))
                .error(R.drawable.close)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public List<DataItem> getData() {
        return data;
    }

    class CallableLoadImage implements Callable<Boolean> {
        ImageHolder mImageHolder;
        Bitmap mBitmap;
        CallableLoadImage(ImageHolder holder, Bitmap bitmap){
            mImageHolder = holder;
            mBitmap = bitmap;
        }
        @Override
        public Boolean call(){
            mImageHolder.mImageView.setImageBitmap(mBitmap);
            return true;
        };
        }
        void startPhotoFragment(DataItem dataItem, boolean type){
            mPresenter.addPhotoFragment(dataItem, type);
        }
    }
