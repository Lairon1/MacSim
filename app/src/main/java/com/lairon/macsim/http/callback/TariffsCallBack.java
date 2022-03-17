package com.lairon.macsim.http.callback;

import com.lairon.macsim.obj.Tariff;

import java.util.List;

public interface TariffsCallBack {
    void onCallBack(List<Tariff> tariffList);
}
