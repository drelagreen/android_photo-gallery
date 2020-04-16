package com.crafsed.webant_practice;

import android.util.Log;

import com.crafsed.webant_practice.api_stuff.APIService;
import com.crafsed.webant_practice.api_stuff.Data;
import com.crafsed.webant_practice.api_stuff.PojoImage;
import com.crafsed.webant_practice.api_stuff.PojoList;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BASE_URL = "http://gallery.dev.webant.ru/";
    public static final String BASE_URL_API = "http://gallery.dev.webant.ru/api/";

    APIService mAPIService;

    static private API sAPI;
    private API(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL_API)
                .build();
        mAPIService = retrofit.create(APIService.class);
    }
    public static API getInstance() {
        if (sAPI==null){
            sAPI = new API();
        }
        return sAPI;
    }


    Data[] getList(int page, boolean type) {
        Response<PojoList> response;
        try {
            if (type){
                response = mAPIService.load10New(page).execute();
            } else {
                response = mAPIService.load10Popular(page).execute();
            }
            PojoList answer = checkResponse(response);
            if (answer == null){
                return null;
            } else {
                return answer.getData();
            }
        } catch (IOException e) {
            Log.w("POJO", "NO INTERNET CONNECTION");
            return null;
        }
    }

    String getBase64Image(int id){
        try{
            Response<PojoImage> response = mAPIService.loadBase64Image(id).execute();
            PojoImage answer = checkResponse(response);
            if (answer==null){
                return null;
            } else{
                return answer.getFile();
            }
        }catch (IOException e){
            Log.w("POJO", "NO INTERNET CONNECTION");
            return null;
        }

    }

    <T> T checkResponse(Response<T> response){
        if (response.isSuccessful()) {
            Log.w("GET", "OK - " + response.code());
            return response.body();

        } else {
            Log.w("GET", "ERROR - " + response.code());
            return null;
        }
    }
}
