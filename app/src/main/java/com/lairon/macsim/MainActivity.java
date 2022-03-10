package com.lairon.macsim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

public class MainActivity extends AppCompatActivity {

    private TextView fioText, phoneNumberText, balanceText, minutesText, gigabytesText, smsText;
    private Client currentClient = Globals.getCurrentClient();

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
    }


}