package com.lairon.macsim.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class ActivityUtils {
    public static void sendError(String error, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Ошибка!")
                .setMessage(error)
                .create()
                .show();
    }

    public static void sendInfo(String info, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Информация!")
                .setMessage(info)
                .create()
                .show();
    }
}
