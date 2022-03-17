package com.lairon.macsim.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;

import com.lairon.macsim.R;

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

    public static Dialog startLoadingDialog(Context context){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }
}
