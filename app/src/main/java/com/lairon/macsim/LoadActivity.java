package com.lairon.macsim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadActivity extends Activity {

    private LoadActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Intent loginIntent = new Intent(instance, LoginActivity.class);
        startActivity(loginIntent);
        instance.finish();


    }



}