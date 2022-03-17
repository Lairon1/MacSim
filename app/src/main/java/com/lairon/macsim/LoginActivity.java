package com.lairon.macsim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

public class LoginActivity extends AppCompatActivity {

    private EditText loginText, passwordText;
    private Button loginButton, registerPageButton;
    private ImageButton settingsButton;
    private LoginActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        initComponents();
    }

    private void initComponents() {
        //EditText
        loginText = findViewById(R.id.LoginEditText);
        passwordText = findViewById(R.id.PasswordEditText);

        //Buttons
        loginButton = findViewById(R.id.LoginButton);
        registerPageButton = findViewById(R.id.RegisterButton);
        settingsButton = findViewById(R.id.SettingsButton);
        setButtonsListeners();
    }

    @SuppressLint("StaticFieldLeak")
    private void setButtonsListeners() {
        settingsButton.setOnClickListener(v -> startActivity(new Intent(instance, SettingsActivity.class)));
        loginButton.setOnClickListener(v -> {

            loginButton.setEnabled(false);
            String login = loginText.getText().toString();
            String password = passwordText.getText().toString();
            MacSimWepApi wepApi = new MacSimWepApi();

            try {
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
                            ActivityUtils.sendError(exception.getMessage(), instance);
                            loginButton.setEnabled(true);
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
                        loginButton.setEnabled(true);

                        super.onPostExecute(client);
                    }
                }.execute();


            } catch (Exception e) {
                ActivityUtils.sendError(e.getMessage(), this);
            }
        });

        registerPageButton.setOnClickListener(v -> {
            Intent registerActivityInstance = new Intent(instance, RegisterActivity.class);
            startActivity(registerActivityInstance);
        });
    }
}