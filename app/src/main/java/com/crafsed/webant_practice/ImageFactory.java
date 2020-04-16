package com.crafsed.webant_practice;

import android.util.Log;

import com.crafsed.webant_practice.api_stuff.Data;

import java.util.ArrayList;

import static com.crafsed.webant_practice.API.BASE_URL;

public class ImageFactory {
    private static ImageFactory sInstance = new ImageFactory();

    public static ImageFactory getInstance() {
        return sInstance;
    }

    public ListAdapter getNewImages(int page, boolean type) {
        ArrayList<DataItem> items = new ArrayList<>();
        Data[] data = API.getInstance().getList(page, type);
        if (data == null){
            return null;
        }
        for (Data datum : data) {
//            String file = API.getInstance().getBase64Image(Integer.parseInt(datum.getId()));
             String file = BASE_URL+"media/"+datum.getImage().getName();
             Log.d("IMG ","FILE - "+file);
            if (file != null) {
                DataItem dataItem = new DataItem(file, Integer.parseInt(datum.getId()), datum.getName(), datum.getDescription(), datum.getDateCreate(), datum.getUser());
                items.add(dataItem);
            } else {
                items.add(null);
            }
        }
        return new ListAdapter(type, items);
    }

}
