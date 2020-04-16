package com.crafsed.webant_practice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class MainActivityPresenter {
    private MainActivity mActivity;
    private static MainActivityPresenter mInstance;
    private AtomicInteger pageNew = new AtomicInteger(1);
    private AtomicInteger pagePopular = new AtomicInteger(1);
    private MainFragment mFragmentNew;
    private MainFragment mFragmentPopular;
    private RecyclerFragment mRecyclerFragmentNew;
    private RecyclerFragment mRecyclerFragmentPopular;
    private NoInternetFragment mNoInternetFragmentNew;
    private NoInternetFragment mNoInternetFragmentPopular;
    private boolean isRefreshNew = false;
    private boolean isRefreshPopular = false;

    private boolean mNoInternetNew = false;
    private boolean mNoInternetPopular = false;

    private MainActivityPresenter(){
        mFragmentNew = new MainFragment(true, this);
        mFragmentPopular = new MainFragment(false, this);
        mRecyclerFragmentNew = new RecyclerFragment(this,true);
        mRecyclerFragmentPopular = new RecyclerFragment(this,false);
        mNoInternetFragmentNew = new NoInternetFragment();
        mNoInternetFragmentPopular = new NoInternetFragment();
    }

    void mainFragmentCreated(boolean type) {
        if (type) {
            mFragmentNew.setFrameFragment(mRecyclerFragmentNew);
        }
            else{
            mFragmentPopular.setFrameFragment(mRecyclerFragmentPopular);
        }
    }

    Fragment getFragment(int pagerPosition){
        return pagerPosition == 0 ? mFragmentNew : mFragmentPopular;
    }

    void attachView(MainActivity activity){
        mActivity = activity;
    }

    void detachView(){
       mActivity = null;
    }


    void onPageSelected(int index){
        mActivity.selectBottomMenuItem(index);
    }

    void onMenuBottomItemSelected(MenuItem item){
        mActivity.selectPage(item.getItemId() == R.id.action_new ? 0 : 1);
    }

    static MainActivityPresenter getInstance() {
        if (mInstance == null){
         mInstance = new MainActivityPresenter();
        }
        return mInstance;
    }

    private ListAdapter newDataCallable(int page, boolean type) {
        ListAdapter list = ImageFactory.getInstance().getNewImages(page,type);
        if (list == null){
            list = new ListAdapter(type, null);
            list.setNull();
        }
        return list;
    }

    class CallableNewData implements Callable<ListAdapter> {
        boolean mType;
        CallableNewData(boolean type){
            this.mType = type;
        }
        @Override
        public ListAdapter call(){
            return newDataCallable(mType ? pageNew.getAndAdd(1) : pagePopular.getAndAdd(1), mType);
        }
    }


    void refreshData(boolean type){
        if (type) {
            pageNew.set(1);
            isRefreshNew = true;
        }
        else {
            pagePopular.set(1);
            isRefreshPopular = true;
        }
        getNewData(type);
    }

    @SuppressLint("CheckResult")
    void getNewData(boolean type){
        if (type) mFragmentNew.showProgressBar();
        else mFragmentPopular.showProgressBar();
        Observer<ListAdapter> observer = new Observer<ListAdapter>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(ListAdapter arrayList) {
                onDataSetReady(arrayList);
            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {}
        };

        Observable.fromCallable(new CallableNewData(type))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        /*
        TODO Включаем прогрессбар - кидаем асинхронный запрос в АПИ с колбэком онДатаСетРэди
         */
    }

    private void onDataSetReady(ListAdapter list){
        ArrayList<DataItem> data;
        boolean type;
        if (list.isNull())     {
            data = null;
        } else {
            data = list.getItems();
        }
        type = list.isType();

        if (data == null){
            if (type){
                if (!mNoInternetNew){
                    mNoInternetNew = true;
                    mFragmentNew.replaceFrameFragment(mNoInternetFragmentNew);
                }
                hideProgressBar(true);
                isRefreshNew = false;
            }else{
                if (!mNoInternetPopular){
                    mNoInternetPopular = true;
                    mFragmentPopular.replaceFrameFragment(mNoInternetFragmentPopular);
                }
                hideProgressBar(false);
                isRefreshPopular = false;
            }
        }else{
            if (type){
                if (mNoInternetNew){
                    mNoInternetNew = false;
                    mFragmentNew.replaceFrameFragment(mRecyclerFragmentNew);
                    mRecyclerFragmentNew.setData(data);
                    isRefreshNew = false;
                } else {
                    if (isRefreshNew) {
                        mRecyclerFragmentNew.setData(data);
                        isRefreshNew = false;
                    } else {
                        mRecyclerFragmentNew.putData(data);
                    }
                }
                hideProgressBar(true);
            } else {
                if (mNoInternetPopular){
                    mNoInternetPopular = false;
                    mFragmentPopular.replaceFrameFragment(mRecyclerFragmentPopular);
                    mRecyclerFragmentPopular.setData(data);
                    isRefreshPopular = false;
                } else {
                    if (isRefreshPopular) {
                        mRecyclerFragmentPopular.setData(data);
                        isRefreshPopular = false;
                    } else {
                        mRecyclerFragmentPopular.putData(data);
                    }
                }
                hideProgressBar(false);
            }
        }
    }

    private void hideProgressBar(boolean type){
        if (type)
        mFragmentNew.hideProgressBar();
        else
        mFragmentPopular.hideProgressBar();
    }

    void addPhotoFragment(DataItem dataItem, boolean type){
        PhotoInfoFragment photoInfoFragment = new PhotoInfoFragment(this, type);
        photoInfoFragment.setDescription(dataItem.getDescription());
        photoInfoFragment.setNameOfPhoto(dataItem.getName());
        photoInfoFragment.setImage(dataItem.getImage());

        if (type){
            mFragmentNew.replaceFrameFragment(photoInfoFragment);
            mFragmentNew.hideHeader();
        } else {
            mFragmentPopular.replaceFrameFragment(photoInfoFragment);
            mFragmentPopular.hideHeader();
        }
    }

    void removePhotoFragment(boolean type){
        if (type){
            mFragmentNew.showHeader();
            mFragmentNew.replaceFrameFragment(mRecyclerFragmentNew);
        } else{
            mFragmentPopular.showHeader();
            mFragmentPopular.replaceFrameFragment(mRecyclerFragmentPopular);
        }
    }

    public MainActivity getActivity() {
        return mActivity;
    }
}
