package com.lairon.macsim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.lairon.macsim.adapter.TariffAdapter;
import com.lairon.macsim.obj.Tariff;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;

import java.util.List;

public class TariffsActivity extends AppCompatActivity {

    private RecyclerView tariffsRecycler;
    private TariffsActivity instance = this;
    private ProgressBar loadingBar;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tariffs);
        tariffsRecycler = findViewById(R.id.TariffsRecyclerView);
        loadingBar = findViewById(R.id.LoadingBar);

        MacSimWepApi wepApi = new MacSimWepApi();
        setEnableLoading(true);
        new AsyncTask<Void, Void, List<Tariff>>(){

            private Exception exception;

            @Override
            protected List<Tariff> doInBackground(Void... voids) {
                try {
                    return wepApi.getAllTariffs();
                } catch (Exception e) {
                    exception = e;
                }
                return null;
            }


            @Override
            protected void onPostExecute(List<Tariff> tariffs) {
                if(tariffs == null){
                    ActivityUtils.sendError(exception.getMessage(), instance);
                    return;
                }
                setTariffs(tariffs);
                setEnableLoading(false);
                super.onPostExecute(tariffs);
            }
        }.execute();

    }

    private void setEnableLoading(boolean b){
        if(b){
            tariffsRecycler.setVisibility(View.GONE);
            loadingBar.setVisibility(View.VISIBLE);
        }else{
            tariffsRecycler.setVisibility(View.VISIBLE);
            loadingBar.setVisibility(View.GONE);
        }
    }

    public void setTariffs(List<Tariff> tariffs){
        TariffAdapter tariffAdapter = new TariffAdapter(this, tariffs);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        tariffsRecycler.setLayoutManager(llm);
        tariffsRecycler.setAdapter(tariffAdapter);
    }

}