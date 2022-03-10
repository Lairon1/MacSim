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

    private void initComponents(){
        //EditText
        loginText = findViewById(R.id.LoginEditText);
        passwordText = findViewById(R.id.PasswordEditText);

        //Buttons
        loginButton = findViewById(R.id.LoginButton);
        registerPageButton = findViewById(R.id.RegisterButton);
        setButtonsListeners();
    }

    private void setButtonsListeners(){
        loginButton.setOnClickListener(v -> {
            String login = loginText.getText().toString();
            String password = passwordText.getText().toString();

            if(!login.matches("[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])")){
                ActivityUtils.sendError("Невалидный логин!\nЛогин должен быть не короче 3х символов и состоять только из латинских символов.", this);
                return;
            }

            if(!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}")){
                ActivityUtils.sendError("Слишком легкий пароль!\nПароль должен быть не короче 8ми символов, в пароле дожна быть хотябы 1 большая буква и хотябы 1 цыфра.", this);
                return;
            }

            JSONObject body = new JSONObject();
            try {
                body.put("Login", login);
                body.put("Password", password);
                new PostHttpRequest((request, code) -> {
                    try {
                        JSONObject result = new JSONObject(request);
                        if(code != 200){
                            String error = result.getString("ERROR");
                            ActivityUtils.sendError(error, this);
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
                        ActivityUtils.sendError("Неизвестная ошибка приложения.", this);
                    }
                }).execute(HttpServerULR.LOGIN, body.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                ActivityUtils.sendError("Неизвестная ошибка приложения.", this);
            }
        });

        registerPageButton.setOnClickListener(v -> {
            Intent registerActivityInstance = new Intent(instance, RegisterActivity.class);
            startActivity(registerActivityInstance);
        });
    }




}