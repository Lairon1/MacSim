package com.lairon.macsim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

public class RegisterActivity extends AppCompatActivity {

    private EditText loginText, passwordText, firstnameText, lastnameText;
    private Button loginPageButton, registerButton;
    private RegisterActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        initComponents();
    }

    private void initComponents() {
        loginText = findViewById(R.id.LoginEditText);
        passwordText = findViewById(R.id.PasswordEditText);
        firstnameText = findViewById(R.id.FirstnameEditText);
        lastnameText = findViewById(R.id.LastnameEditText);
        loginPageButton = findViewById(R.id.LoginButton);
        registerButton = findViewById(R.id.RegisterButton);
        setButtonsListeners();
    }

    @SuppressLint("StaticFieldLeak")
    private void setButtonsListeners() {
        loginPageButton.setOnClickListener(v -> {
            instance.finish();
        });
        registerButton.setOnClickListener(v -> {
            String login = loginText.getText().toString();
            String password = passwordText.getText().toString();
            String firstname = firstnameText.getText().toString();
            String lastname = lastnameText.getText().toString();
            MacSimWepApi wepApi = new MacSimWepApi();


            new AsyncTask<Void, Void, Client>(){

                private Exception exception;

                @Override
                protected Client doInBackground(Void... voids) {
                    try {
                        return wepApi.registerUser(login, password, firstname, lastname);
                    } catch (Exception e) {
                        exception = e;
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Client client) {
                    if(client == null){
                        ActivityUtils.sendError(exception.getMessage(), instance);
                        return;
                    }
                    loginUser(client);
                    super.onPostExecute(client);
                }
            }.execute();

        });
    }

    private void loginUser(Client client) {

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