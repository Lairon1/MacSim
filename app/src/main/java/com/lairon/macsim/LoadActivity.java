package com.lairon.macsim;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.lairon.macsim.callback.HttpCallBack;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class LoadActivity extends Activity {

    private LoadActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        ServerConnectionTest serverConnectionTest = new ServerConnectionTest(request -> {

            Dialog dialog = new Dialog(instance);
            dialog.setTitle("Невозможно присоеденится к серверу.");
            if(request == null){
                dialog.show();
                return;
            }

            if(!request.equalsIgnoreCase("Server running")){
                dialog.show();
                return;
            }
            Intent loginIntent = new Intent(instance, LoginActivity.class);
            startActivity(loginIntent);
            instance.finish();
        });
        serverConnectionTest.execute();


    }


    private class ServerConnectionTest extends AsyncTask<Void, Void, Void>{

        private volatile String request;
        private HttpCallBack callBack;

        public ServerConnectionTest(HttpCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.0.10/server");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                Scanner scanner = new Scanner(connection.getInputStream());
                request = scanner.next();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);
            callBack.onCallBack(request);
        }
    }
}