package com.crafsed.webant_practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class MainFragment extends Fragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mFragmentType;
    private TextView mHeader;
    private MainActivityPresenter mPresenter;
    public MainFragment(boolean type, MainActivityPresenter presenter){
        mFragmentType = type;
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(mFragmentType ? R.layout.fragment_new : R.layout.fragment_popular, container, false);
        if (savedInstanceState == null) {
            mPresenter.mainFragmentCreated(mFragmentType);
            mSwipeRefreshLayout = view.findViewById(mFragmentType ? R.id.swipeLayoutNew : R.id.swipeLayoutPopular);
            mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.refreshData(mFragmentType));
            mHeader = view.findViewById(mFragmentType ? R.id.headerNew : R.id.headerPopular);
        }
        return view;
    }


    public void setFrameFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(mFragmentType ? R.id.frameLayoutNew : R.id.frameLayoutPopular, fragment).commit();
    }

    public void removeFrameFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().remove(fragment).commit();
    }

    public void replaceFrameFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(mFragmentType ? R.id.frameLayoutNew : R.id.frameLayoutPopular, fragment).commit();
    }
    void showProgressBar(){
        mSwipeRefreshLayout.setRefreshing(true);
    }

    void hideProgressBar(){
        mSwipeRefreshLayout.setRefreshing(false);
    }

    void hideHeader(){
        mHeader.setVisibility(View.INVISIBLE);
    }
    void showHeader(){
        mHeader.setVisibility(View.VISIBLE);
    }
}
