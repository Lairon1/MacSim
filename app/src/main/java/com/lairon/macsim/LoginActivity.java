package com.lairon.macsim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.lairon.macsim.http.helper.PostHttpRequest;
import com.lairon.macsim.http.utils.HttpServerULR;
import com.lairon.macsim.obj.Client;
import com.lairon.macsim.obj.ClientBuilder;
import com.lairon.macsim.obj.Tariff;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;
import com.lairon.macsim.utils.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Parameter;

public class LoginActivity extends AppCompatActivity {

    private EditText loginText, passwordText;
    private Button loginButton, registerPageButton;
    private LoginActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_logia);

        initComponents();


    }

    private void initComponents() {
        //EditText
        loginText = findViewById(R.id.LoginEditText);
        passwordText = findViewById(R.id.PasswordEditText);

        //Buttons
        loginButton = findViewById(R.id.LoginButton);
        registerPageButton = findViewById(R.id.RegisterButton);
        setButtonsListeners();
    }

    private void setButtonsListeners() {
        loginButton.setOnClickListener(v -> {
            String login = loginText.getText().toString();
            String password = passwordText.getText().toString();


            try{
                MacSimWepApi wepApi = new MacSimWepApi();
                wepApi.loginUser(login, password, this::callBackLogin);
            }catch (Exception e){
                ActivityUtils.sendError(e.getMessage(), this);
            }

        });

        registerPageButton.setOnClickListener(v -> {
            Intent registerActivityInstance = new Intent(instance, RegisterActivity.class);
            startActivity(registerActivityInstance);
        });
    }

    private void callBackLogin(Client client) {
        if (client == null) {
            ActivityUtils.sendError("Неправильный логин или пароль", this);
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