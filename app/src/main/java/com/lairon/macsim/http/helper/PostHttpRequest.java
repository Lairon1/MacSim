package com.lairon.macsim.http.helper;

import android.os.AsyncTask;

import com.lairon.macsim.http.callback.HttpCallBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PostHttpRequest extends AsyncTask<String, String, String> {


    private HttpCallBack callBack;
    private int code;
    private String request;

    public PostHttpRequest(HttpCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            String requestBody = strings[1];

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream(), true);
            printWriter.println(requestBody);

            code = connection.getResponseCode();
            StringBuilder result = new StringBuilder();
            if(code != 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) result.append(line);
                request = result.toString();
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) result.append(line);
            request = result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        callBack.onCallBack(request, code);
        super.onPostExecute(s);
    }
}
