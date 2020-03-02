package com.example.temperatureapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import static android.provider.Telephony.Carriers.SERVER;

public class HttpGetRequest extends AsyncTask<String, Void, String> {
    Context context;

    private static final String SERVER = "http://ec2-34-201-22-53.compute-1.amazonaws.com:3001/";
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

    public HttpGetRequest(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        TextView tempView = (TextView) ((Activity)context).findViewById(R.id.textViewHouse);
        tempView.setText(result + " C");
    }
}