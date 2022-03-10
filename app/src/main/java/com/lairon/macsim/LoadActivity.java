package com.lairon.macsim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lairon.macsim.http.helper.PostHttpRequest;
import com.lairon.macsim.http.utils.HttpServerULR;
import com.lairon.macsim.obj.Client;
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

        if(login != null && password != null){
            try {
                JSONObject body = new JSONObject();
                body.put("Login", login);
                body.put("Password", password);
                new PostHttpRequest((request, code) -> {
                    try {
                        JSONObject result = new JSONObject(request);
                        if(code != 200){

                            Intent loginIntent = new Intent(instance, LoginActivity.class);
                            startActivity(loginIntent);
                            instance.finish();
                            return;
                        }
                        JSONObject clientObject = result.getJSONObject("Client");
                        Client client = Parser.parseJsonToClient(clientObject);
                        Globals.setCurrentClient(client);

                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Intent loginIntent = new Intent(instance, LoginActivity.class);
                        startActivity(loginIntent);
                        instance.finish();
                        return;

                    }
                }).execute(HttpServerULR.LOGIN, body.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Intent loginIntent = new Intent(instance, LoginActivity.class);
                startActivity(loginIntent);
                instance.finish();
                return;
            }


            Intent loginIntent = new Intent(instance, MainActivity.class);
            startActivity(loginIntent);
            instance.finish();
            return;
        }

        Intent loginIntent = new Intent(instance, LoginActivity.class);
        startActivity(loginIntent);
        instance.finish();


    }



}