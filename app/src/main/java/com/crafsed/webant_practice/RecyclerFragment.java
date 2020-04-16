package com.crafsed.webant_practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private boolean mFragmentType;
    private MainActivityPresenter mPresenter;
    private final int SPAN_COUNT = 2;

    RecyclerFragment(MainActivityPresenter presenter, boolean type){
        mPresenter = presenter;
        mFragmentType = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        setRetainInstance(true);
        if (savedInstanceState == null) {
            mRecyclerView = v.findViewById(R.id.recycler);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0) {
                        int visible = 2;

                        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                        int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                        int currentTotalCount = layoutManager.getItemCount();

                        if (currentTotalCount <= lastItem + visible) {
                            mPresenter.getNewData(mFragmentType);
                        }
                    }
                }
            });
            MyAdapter adapter = new MyAdapter(mRecyclerView, mPresenter, mFragmentType);
            mRecyclerView.setAdapter(adapter);
            mAdapter = adapter;
            mPresenter.refreshData(mFragmentType);
        }
        return v;
    }

    public MyAdapter getAdapter() {
        return mAdapter;
    }
    public void putData(ArrayList<DataItem> list){
        getAdapter().getData().addAll(list);
        getAdapter().notifyDataSetChanged();
    }
    public void setData(ArrayList<DataItem> list){
        getAdapter().setData(list);
        getAdapter().notifyDataSetChanged();
    }
}
