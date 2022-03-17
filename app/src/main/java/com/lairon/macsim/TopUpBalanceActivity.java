package com.lairon.macsim;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

public class TopUpBalanceActivity extends AppCompatActivity {

    private EditText topUpBalanceCountText;
    private Button topUpButton;
    private TopUpBalanceActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_top_up_balance);

        initComponents();
    }

    private void initComponents() {
        topUpBalanceCountText = findViewById(R.id.TopUpBalanceCountText);
        topUpButton = findViewById(R.id.TopUpButton);
        topUpButton.setOnClickListener(this::topUpBalanceButtonPressListener);
    }

    @SuppressLint("StaticFieldLeak")
    private void topUpBalanceButtonPressListener(View view) {
        Dialog alertDialog = ActivityUtils.startLoadingDialog(this);


        topUpButton.setEnabled(false);
        String countString = topUpBalanceCountText.getText().toString();
        if (countString.length() == 0) {
            ActivityUtils.sendError("Сумма пополнения не может быть пустой или 0", this);
            alertDialog.hide();
            return;
        }
        int count = 0;
        try {
            count = Integer.parseInt(countString);
        } catch (Exception e) {
            ActivityUtils.sendError("Сумма пополнения не может быть пустой или 0", this);
            alertDialog.hide();
            return;
        }
        if (count <= 0) {
            ActivityUtils.sendError("Сумма пополнения не может быть пустой или 0", this);
            alertDialog.hide();
            return;
        }


        MacSimWepApi wepApi = new MacSimWepApi();

        int finalCount = count;
        new AsyncTask<Void, Void, Client>() {

            private Exception exception;

            @Override
            protected Client doInBackground(Void... voids) {
                try {
                    return wepApi.topUpBalance(Globals.getCurrentClient(), finalCount);
                } catch (Exception e) {
                    e.printStackTrace();
                    exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Client client) {
                if (client == null) {
                    ActivityUtils.sendError(exception.getMessage(), instance);
                    return;
                }
                Globals.setCurrentClient(client);
                alertDialog.hide();
                finish();
                super.onPostExecute(client);
            }
        }.execute();

    }
}