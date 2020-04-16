package com.crafsed.webant_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity{
    MainActivityPresenter mPresenter;
    ViewPager mViewPager;
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }


    @SuppressLint("SourceLockedOrientationActivity")
    void initialization(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initViewPager();
        initBottomNavigationView();
        mPresenter = MainActivityPresenter.getInstance();
        mPresenter.attachView(this);
    }


    void initBottomNavigationView(){
        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            mPresenter.onMenuBottomItemSelected(item);
            return false;
        });
    }


    void initViewPager(){
        mViewPager = findViewById(R.id.viewPager);

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Log.i("LOG","getFragment - "+position);
                return mPresenter.getFragment(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mPresenter.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    public void selectPage(int pageIndex){
        mViewPager.setCurrentItem(pageIndex);
    }

    public void selectBottomMenuItem(int menuIndex){
        mBottomNavigationView.getMenu().getItem(menuIndex).setChecked(true);
    }
}
