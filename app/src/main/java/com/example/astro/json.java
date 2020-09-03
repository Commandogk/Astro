package com.example.astro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface json {
    @GET("planetary/apod")
    Call<Apod> getapod(
            @Query("date") String date,
            @Query("hd") boolean boo,
            @Query("api_key") String key
    );
    @GET("search")
    Call<collectiond> getlistdata(
            @Query("q") String q
    );
    @GET("/asset/{nasa_id}")
    Call<obj> getimg(@Path("nasa_id") String id);

}


