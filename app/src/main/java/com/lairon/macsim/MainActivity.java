package com.lairon.macsim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

public class MainActivity extends AppCompatActivity {

    private TextView fioText, phoneNumberText, balanceText, minutesText, gigabytesText, smsText;
    private Client currentClient = Globals.getCurrentClient();
    private ConstraintLayout topUbBalanceButton, tariffsButton;
    private MainActivity instance = this;
    private ImageButton reloadButton;

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
        minutesText.setText(currentClient.getMinutes() + "");
        gigabytesText.setText(currentClient.getGigabytes() + "");
        smsText.setText(currentClient.getSms() + "");
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
        initButtons();
    }

    private void initButtons(){
        topUbBalanceButton.setOnClickListener(v -> {
            Intent intent = new Intent(instance, TopUpBalanceActivity.class);
            instance.startActivity(intent);
        });
        reloadButton.setOnClickListener(v -> new MacSimWepApi().loginUser(currentClient.getLogin(), currentClient.getPassword(), this::setClient));
    }

    private void setClient(Client client){
        currentClient = client;
        Globals.setCurrentClient(client);
        updateCurrentClientData();
    }
}