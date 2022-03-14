package com.lairon.macsim.http.callback;

import org.json.JSONException;

public interface HttpCallBack {
    void onCallBack(String request, int code);
}
