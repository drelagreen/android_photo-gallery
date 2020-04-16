package com.crafsed.webant_practice;

import java.util.ArrayList;

public class ListAdapter {
    private boolean isNull;
    private boolean mType;
    private ArrayList<DataItem> mItems;

    ListAdapter(boolean type, ArrayList<DataItem> items) {
        mType = type;
        mItems = items;
    }

    public ArrayList<DataItem> getItems() {
        return mItems;
    }

    public boolean isType() {
        return mType;
    }

    public void setNull() {
        isNull = true;
    }

    public boolean isNull() {
        return isNull;
    }
}