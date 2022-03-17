package com.lairon.macsim.http.helper;

import android.os.AsyncTask;

import com.lairon.macsim.http.callback.HttpCallBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetHttpRequest extends AsyncTask<String, String, String> {

    private HttpCallBack callBack;
    private int code;
    private String request;


    public GetHttpRequest(HttpCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);

            StringBuilder result = new StringBuilder();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) result.append(line);

            code = connection.getResponseCode();

            request = result.toString();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        callBack.onCallBack(request, code);
        super.onPostExecute(s);
    }
}
