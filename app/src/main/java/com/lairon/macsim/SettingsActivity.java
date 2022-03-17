package com.lairon.macsim;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.lairon.macsim.http.utils.HttpServerULR;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activity_settings);

    }

    @Override
    protected void onDestroy() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String address = sp.getString("address", "http://192.168.0.10/");
        HttpServerULR.getInstance().setAddress(address);
        super.onDestroy();
    }

}
