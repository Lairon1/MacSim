package com.lairon.macsim;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

public class ProfileActivity extends AppCompatActivity {

    private EditText loginText, lastnameText, firstnameText;
    private Button changePasswordButton;
    private ProfileActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        init();


    }

    @SuppressLint("StaticFieldLeak")
    private void init() {
        loginText = findViewById(R.id.LoginText);
        lastnameText = findViewById(R.id.LastnameText);
        firstnameText = findViewById(R.id.FirstnameText);

        changePasswordButton = findViewById(R.id.ChangePasswordButton);

        changePasswordButton.setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.change_password_activity, null);
            new AlertDialog.Builder(this)
                    .setView(view)
                    .setPositiveButton("Изменить", (dialog, which) -> {
                        String newPass = ((EditText) view.findViewById(R.id.newPass)).getText().toString();
                        Dialog loadingDialog = ActivityUtils.startLoadingDialog(this);

                        new AsyncTask<Void, Void, Client>() {
                            private Exception exception;
                            @Override
                            protected Client doInBackground(Void... voids) {
                                MacSimWepApi wepApi = new MacSimWepApi();
                                try {
                                    return wepApi.changePassword(Globals.getCurrentClient(), newPass);
                                } catch (Exception e) {
                                    exception = e;
                                }
                                return null;
                            }
                            @Override
                            protected void onPostExecute(Client client) {
                                if(client == null){
                                    loadingDialog.hide();
                                    ActivityUtils.sendError(exception.getMessage(), instance);
                                    return;
                                }
                                Globals.setCurrentClient(client);
                                loadingDialog.hide();
                                ActivityUtils.sendInfo("Пароль успешно изменен.", instance);
                                super.onPostExecute(client);
                            }
                        }.execute();
                    })
                    .setNegativeButton("Отмена", (dialog, which) -> {
                    })
            .create().show();
        });
        loginText.setText(Globals.getCurrentClient().getLogin());
        lastnameText.setText(Globals.getCurrentClient().getLastname());
        firstnameText.setText(Globals.getCurrentClient().getFirstname());

    }
}