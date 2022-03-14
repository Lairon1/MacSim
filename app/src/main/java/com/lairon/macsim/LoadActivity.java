package com.lairon.macsim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lairon.macsim.http.helper.PostHttpRequest;
import com.lairon.macsim.http.utils.HttpServerULR;
import com.lairon.macsim.obj.Client;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;
import com.lairon.macsim.utils.Parser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoadActivity extends Activity {

    private LoadActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UserDataController userDataController = new UserDataController(this);
        Globals.setUserDataController(userDataController);
        String login = userDataController.getProperty("Login", null);
        String password = userDataController.getProperty("Password", null);



        Intent loginIntent = new Intent(instance, LoginActivity.class);
        startActivity(loginIntent);
        instance.finish();

        try{
            MacSimWepApi wepApi = new MacSimWepApi();
            wepApi.loginUser(login, password, this::callBackLogin);
        }catch (Exception e){
            ActivityUtils.sendError(e.getMessage(), this);
        }
    }

    private void callBackLogin(Client client) {
        if (client == null) {
            Intent loginIntent = new Intent(instance, LoginActivity.class);
            startActivity(loginIntent);
            instance.finish();
            return;
        }

        Globals.setCurrentClient(client);
        UserDataController userDataController = Globals.getUserDataController();
        userDataController.setProperty("Login", client.getLogin());
        userDataController.setProperty("Password", client.getPassword());
        userDataController.save();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}