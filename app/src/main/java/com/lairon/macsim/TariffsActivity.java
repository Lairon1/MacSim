package com.lairon.macsim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lairon.macsim.adapter.TariffAdapter;
import com.lairon.macsim.obj.Tariff;
import com.lairon.macsim.servlet.api.MacSimWepApi;

import java.util.List;

public class TariffsActivity extends AppCompatActivity {

    private RecyclerView tariffsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tariffs);
        tariffsRecycler = findViewById(R.id.TariffsRecyclerView);

        MacSimWepApi wepApi = new MacSimWepApi();
        wepApi.getAllTariffs(this::setTariffs);

    }

    public void setTariffs(List<Tariff> tariffs){
        TariffAdapter tariffAdapter = new TariffAdapter(this, tariffs);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        tariffsRecycler.setLayoutManager(llm);
        tariffsRecycler.setAdapter(tariffAdapter);
    }

}