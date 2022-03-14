package com.lairon.macsim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

public class TopUpBalanceActivity extends AppCompatActivity {

    private EditText topUpBalanceCountText;
    private Button topUpButton;

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

    private void topUpBalanceButtonPressListener(View view) {
        MacSimWepApi wepApi = new MacSimWepApi();
        String countString = topUpBalanceCountText.getText().toString();
        if(countString.length() == 0){
            ActivityUtils.sendError("Сумма пополнения не может быть пустой или 0", this);
            return;
        }
        int count = 0;
        try{
            count = Integer.parseInt(countString);
        }catch (Exception e){
            ActivityUtils.sendError("Сумма пополнения не может быть пустой или 0", this);
            return;
        }
        if(count <= 0){
            ActivityUtils.sendError("Сумма пополнения не может быть пустой или 0", this);
            return;
        }

        wepApi.topUpBalance(Globals.getCurrentClient(), count, client -> {
            this.finish();
        });



    }
}