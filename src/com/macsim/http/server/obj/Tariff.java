package com.macsim.http.server.obj;

import com.serializer.annotation.JsonName;

public class Tariff {

    @JsonName("ID")
    private int id;
    @JsonName("Name")
    private String name;
    @JsonName("Price")
    private double price;
    @JsonName("Description")
    private String description;
    @JsonName("Minutes")
    private int minutes;
    @JsonName("Gigabytes")
    private int gigabytes;
    @JsonName("SMS")
    private int sms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
