package com.lairon.macsim;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.obj.Tariff;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

public class MainActivity extends AppCompatActivity {

    private TextView fioText, phoneNumberText, balanceText, minutesText, gigabytesText, smsText;
    private Client currentClient = Globals.getCurrentClient();
    private ConstraintLayout topUbBalanceButton, tariffsButton, myTariffButton;
    private MainActivity instance = this;
    private ImageButton reloadButton, accountLeaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initComponents();
        if(Globals.getCurrentClient() == null){
            ActivityUtils.sendError("Пользователь равен null", this);
            return;
        }
        updateCurrentClientData();

    }

    private void updateCurrentClientData() {
        fioText.setText(currentClient.getFirstname() + " " + currentClient.getLastname());
        phoneNumberText.setText(currentClient.getPhoneNumber() + "");
        balanceText.setText(currentClient.getBalance() + " ₽");
        minutesText.setText(currentClient.getMinutes() == -1 ? "∞" : currentClient.getMinutes() + "");
        gigabytesText.setText(currentClient.getGigabytes() == -1 ? "∞" : currentClient.getGigabytes() + "");
        smsText.setText(currentClient.getSms() == -1 ? "∞" : currentClient.getSms() + "");
    }

    private void initComponents() {
        fioText = findViewById(R.id.FioTextView);
        phoneNumberText = findViewById(R.id.PhoneNumberTextView);
        balanceText = findViewById(R.id.BalanceTextView);
        minutesText = findViewById(R.id.MinutesTextView);
        gigabytesText = findViewById(R.id.GigabytesTextView);
        smsText = findViewById(R.id.SmsTextView);
        topUbBalanceButton = findViewById(R.id.TopUpBalanceButton);
        tariffsButton = findViewById(R.id.TariffsButton);
        reloadButton = findViewById(R.id.ReloadButton);
        accountLeaveButton = findViewById(R.id.AccountLeaveButton);
        myTariffButton = findViewById(R.id.MyTariffButton);

        initButtons();
    }

    @SuppressLint("StaticFieldLeak")
    private void initButtons(){
        myTariffButton.setOnClickListener(v -> {
            if(Globals.getCurrentClient().getUsedTariff() == null){
                ActivityUtils.sendError("У вас нет тарифа", this);
                return;
            }


            View view = LayoutInflater.from(this).inflate(R.layout.my_tariff_dialog, null);

            TextView tariffNameText, gigabytesText, smsText, minutesText, descriptionText, priceText;
            tariffNameText = view.findViewById(R.id.TariffNameText);
            smsText = view.findViewById(R.id.SMSText);
            gigabytesText = view.findViewById(R.id.GigabytesText);
            minutesText = view.findViewById(R.id.MinutesText);
            descriptionText = view.findViewById(R.id.DescriptionText);
            priceText = view.findViewById(R.id.PriceText);
            Tariff tariff = Globals.getCurrentClient().getUsedTariff();

            tariffNameText.setText(tariff.getName());
            gigabytesText.setText(tariff.getGigabytes() == -1 ? "Безлимит" : String.valueOf(tariff.getGigabytes()));
            smsText.setText(tariff.getSms() == -1 ? "Безлимит" : String.valueOf(tariff.getSms()));
            minutesText.setText(tariff.getMinutes() == -1 ? "Безлимит" : String.valueOf(tariff.getMinutes()));

            descriptionText.setText(tariff.getDescription());
            priceText.setText(String.valueOf(((int) tariff.getPrice())));


            new AlertDialog.Builder(this)
            .setView(view)
            .create()
            .show();



        });

        topUbBalanceButton.setOnClickListener(v -> {
            Intent intent = new Intent(instance, TopUpBalanceActivity.class);
            instance.startActivity(intent);
        });
        accountLeaveButton.setOnClickListener(v -> {

            startActivity(new Intent(instance, ProfileActivity.class));

        });

        tariffsButton.setOnClickListener(v -> startActivity(new Intent(instance, TariffsActivity.class)));



        reloadButton.setOnClickListener(v -> {
            Dialog alertDialog = ActivityUtils.startLoadingDialog(this);


            reloadButton.setEnabled(false);
            MacSimWepApi wepApi = new MacSimWepApi();

            new AsyncTask<Void, Void, Client>(){

                private Exception exception;

                @Override
                protected Client doInBackground(Void... voids) {
                    try {
                        return wepApi.loginUser(Globals.getCurrentClient().getLogin(), Globals.getCurrentClient().getPassword());
                    } catch (Exception e) {
                        exception = e;
                        return null;
                    }
                }
                @Override
                protected void onPostExecute(Client client) {
                    if(client == null){
                        ActivityUtils.sendError(exception.getMessage(), instance);
                        alertDialog.hide();
                        reloadButton.setEnabled(true);
                        return;
                    }
                    setClient(client);
                    alertDialog.hide();
                    reloadButton.setEnabled(true);
                    super.onPostExecute(client);
                }
            }.execute();
        });

    }




    private void setClient(Client client){
        currentClient = client;
        Globals.setCurrentClient(client);
        updateCurrentClientData();
    }
}