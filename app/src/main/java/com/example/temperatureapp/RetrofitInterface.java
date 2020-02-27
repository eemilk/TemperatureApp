package com.example.temperatureapp;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @GET("/temperature")
    Call<List<TemperatureResult>> myTemp(@Path("temperature") String temperature);
}