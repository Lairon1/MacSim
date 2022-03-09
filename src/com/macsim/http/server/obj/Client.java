package com.macsim.http.server.obj;

import com.serializer.annotation.JsonName;

public class Client {

    @JsonName("ID")
    private int id;
    @JsonName("Login")
    private String login;
    @JsonName("Password")
    private String password;
    @JsonName("FirstName")
    private String firstname;
    @JsonName("LastName")
    private String lastname;
    @JsonName("UsedTariff")
    private Tariff usedTariff;
    @JsonName("Minutes")
    private int minutes;
    @JsonName("Gigabytes")
    private int gigabytes;
    @JsonName("SMS")
    private int sms;
    @JsonName("Balance")
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tariff getUsedTariff() {
        return usedTariff;
    }

    public void setUsedTariff(Tariff usedTariff) {
        this.usedTariff = usedTariff;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getGigabytes() {
        return gigabytes;
    }

    public void setGigabytes(int gigabytes) {
        this.gigabytes = gigabytes;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}


