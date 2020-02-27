package com.example.temperatureapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleGetTempDialog();

    }

    private void handleGetTempDialog() {
        final TextView tempHouse = (TextView) findViewById(R.id.textViewHouse);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://localhost:3001")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RetrofitInterface client = retrofit.create(RetrofitInterface.class);
        Call<List<TemperatureResult>> call = client.myTemp("temperature");

        call.enqueue(new Callback<List<TemperatureResult>>() {
            @Override
            public void onResponse(Call<List<TemperatureResult>> call, Response<List<TemperatureResult>> response) {
                List <TemperatureResult> temperature = response.body();
                tempHouse.setText(temperature.toString());

            }

            @Override
            public void onFailure(Call<List<TemperatureResult>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
