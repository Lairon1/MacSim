package com.lairon.macsim;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDataController extends Properties {


    private static final String DATA_FILE_PATH = "UserData.properties";
    private Activity activity;

    public UserDataController(Activity activity) {
        this.activity = activity;
        try {
            FileInputStream fileInputStream = activity.openFileInput(DATA_FILE_PATH);
            loadFromXML(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void save() {
        try {
            FileOutputStream outputStream = activity.openFileOutput(DATA_FILE_PATH, Context.MODE_PRIVATE);
            storeToXML(outputStream, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
