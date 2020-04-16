package com.crafsed.webant_practice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.crafsed.webant_practice.api_stuff.Data;

class DataItem {
//    private Bitmap mImage;
//    private Bitmap mRawImage;
    private String mFile;
    private int mId;
    private String mName;
    private String mDescription;
    private String mDateCreation;
    private String mUser;

    public DataItem(String file, int id, String name, String description, String dateCreation, String user){
//        mRawImage = convertString64ToImage(file);
//        mImage = resizeBitmap(mRawImage);
        mFile = file.replaceAll(" ","_");
        mId = id;
        mName = name;
        mDescription = description;
        mDateCreation = dateCreation;
        mUser = user;
    }

    public String getImage() {
        return mFile;
    }

    public int getId() {
        return mId;
    }

    public String getDateCreation() {
        return mDateCreation;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public String getUser() {
        return mUser;
    }

    //    public Bitmap getImage() {
//        return mImage;
//    }
//    @Deprecated
//    private static Bitmap convertString64ToImage(String base64String) {
//        base64String = base64String.split(",")[1];
//        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//    }
//    @Deprecated
//    private static Bitmap resizeBitmap(Bitmap bitmap){
//        if (bitmap==null) return null;
//        return Bitmap.createScaledBitmap(bitmap, 180, 150, false);
//    }
}
