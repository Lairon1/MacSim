package com.lairon.macsim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lairon.macsim.http.helper.PostHttpRequest;
import com.lairon.macsim.http.utils.HttpServerULR;
import com.lairon.macsim.utils.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

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

    private void setButtonsListeners() {
        loginPageButton.setOnClickListener(v -> {
            instance.finish();
        });
        registerButton.setOnClickListener(v -> {

            String login = loginText.getText().toString();
            String password = passwordText.getText().toString();
            String firstname = firstnameText.getText().toString();
            String lastname = lastnameText.getText().toString();


            if(!login.matches("[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])")){
                ActivityUtils.sendError("Невалидный логин!\nЛогин должен быть не короче 3х символов и состоять только из латинских символов.", this);
                return;
            }

            if(!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}")){
                ActivityUtils.sendError("Слишком легкий пароль!\nПароль должен быть не короче 8ми символов, в пароле дожна быть хотябы 1 большая буква и хотябы 1 цыфра.", this);
                return;
            }

            if(firstname.length() <= 3) {
                ActivityUtils.sendError("Ваше имя не может быть короче 3х символов.", this);
                return;
            }

            if(lastname.length() <= 3){
                ActivityUtils.sendError("Ваша фамилия не может быть короче 4рех символов.", this);
            }

            JSONObject body = new JSONObject();
            try {
                body.put("Login", login);
                body.put("Password", password);
                body.put("Firstname", firstname);
                body.put("Lastname", lastname);
                new PostHttpRequest((request, code) -> {
                    try {
                        JSONObject result = new JSONObject(request);
                        if(code != 200){
                            String error = result.getString("ERROR");
                            ActivityUtils.sendError(error, this);
                            return;
                        }
                        ActivityUtils.sendInfo("Вы успешно создали аккаунт!", this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ActivityUtils.sendError("Неизвестная ошибка приложения.", this);
                    }
                }).execute(HttpServerULR.REGISTER, body.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                ActivityUtils.sendError("Неизвестная ошибка приложения.", this);
            }
        });

    }


}