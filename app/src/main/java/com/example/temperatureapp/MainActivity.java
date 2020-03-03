package com.example.temperatureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private TextView tempHouse;
    private TextView tempOutside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempHouse = (TextView) findViewById(R.id.textHouseValue);
        tempOutside = (TextView) findViewById(R.id.textOutsideValue);

        HttpGetRequest requestTemperature = new HttpGetRequest(this);
        requestTemperature.execute();

        HttpGetOpenWeather requestWeather = new HttpGetOpenWeather(this);
        requestWeather.execute();

        HttpGetOpenWeatherIcon requestIcon = new HttpGetOpenWeatherIcon(this);
        requestIcon.execute();
    }

}

