package com.crafsed.webant_practice.api_stuff;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("http://gallery.dev.webant.ru/api/photos?new=true&popular=false&limit=10")
    Call<PojoList> load10New(
            @Query("page") Integer page
    );

    @GET("http://gallery.dev.webant.ru/api/photos?new=false&popular=true&limit=10")
    Call<PojoList> load10Popular(
            @Query("page") Integer page
    );
    @Deprecated
    @GET("http://gallery.dev.webant.ru/api/media_objects/{id}")
    Call<PojoImage> loadBase64Image(
            @Path("id") Integer id
    );
}
