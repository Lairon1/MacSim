package com.lairon.macsim;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.Globals;

public class LoadActivity extends Activity {

    private LoadActivity instance = this;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserDataController userDataController = new UserDataController(this);
        Globals.setUserDataController(userDataController);
        String login = userDataController.getProperty("Login", null);
        String password = userDataController.getProperty("Password", null);
        MacSimWepApi wepApi = new MacSimWepApi();

        new AsyncTask<Void, Void, Client>() {

            private Exception exception;

            @Override
            protected Client doInBackground(Void... voids) {
                try {
                    return wepApi.loginUser(login, password);
                } catch (Exception e) {
                    exception = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Client client) {
                if (client == null) {
                    instance.startActivity(new Intent(instance, LoginActivity.class));
                    instance.finish();
                    return;
                }

                Globals.setCurrentClient(client);
                UserDataController userDataController = Globals.getUserDataController();
                userDataController.setProperty("Login", client.getLogin());
                userDataController.setProperty("Password", client.getPassword());
                userDataController.save();

                Intent intent = new Intent(instance, MainActivity.class);
                startActivity(intent);
                finish();
                super.onPostExecute(client);
            }
        }.execute();

    }



}