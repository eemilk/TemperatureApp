package com.example.temperatureapp;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import static android.provider.Telephony.Carriers.SERVER;

public class HttpGetOpenWeatherIcon extends AsyncTask<String, Void, String> {
    Context context;

    private static final String SERVER = "http://ec2-34-201-22-53.compute-1.amazonaws.com:3001/openweather/icon";
    static final String REQUEST_METHOD = "GET";
    static final int READ_TIMEOUT = 15000;
    static final int CONNECTION_TIMEOUT = 15000;


    @Override
    protected String doInBackground(String... params) {
        String result;
        String inputLine;

        try {
            URL myUrl = new URL(SERVER);
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();

            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            connection.connect();

            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            reader.close();
            streamReader.close();

            result = stringBuilder.toString();
        }
        catch(IOException e) {
            e.printStackTrace();
            result = "error";
        }

        return result;
    }

    public HttpGetOpenWeatherIcon(Context current) {
        this.context = current;
    }



    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ImageView weatherImg = (ImageView) ((Activity)context).findViewById(R.id.imageView);
        String imgSrc = ("i" + result);
        int imgId = ((Activity)context).getResources().getIdentifier(imgSrc, "drawable", ((Activity)context).getPackageName());
        weatherImg.setImageResource(imgId);
    }
}