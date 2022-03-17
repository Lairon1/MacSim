package com.lairon.macsim.http.utils;

public class HttpServerULR {

    private static HttpServerULR instacne = new HttpServerULR();

    public static HttpServerULR getInstance() {
        return instacne;
    }

    private String address = "http://192.168.0.10/";

    private String login = "login";
    private String tariff = "tariff";
    private String register = "register";
    private String server = "server";
    private String topUpBalance = "topUpBalance";
    private String hookUpTariff = "hookUpTariff";
    private String changePassword = "changePassword";

    public String getChangePassword() {
        return getAddress() + changePassword;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getLogin() {
        return getAddress() + login;
    }

    public String getTariff() {
        return getAddress() + tariff;
    }

    public String getRegister() {
        return getAddress() + register;
    }

    public String getServer() {
        return getAddress() + server;
    }

    public String getTopUpBalance() {
        return getAddress() + topUpBalance;
    }

    public String getHookUpTariff() {
        return getAddress() + hookUpTariff;
    }
}
