package com.lairon.macsim.obj;

public class ClientBuilder{
    private int id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private Tariff usedTariff;
    private int minutes;
    private int gigabytes;
    private int sms;
    private int balance;
    private long phoneNumber;

    public Client build(){
        Client client = new Client();

        client.setId(id);
        client.setLogin(login);
        client.setPassword(password);
        client.setFirstname(firstname);
        client.setLastname(lastname);
        client.setUsedTariff(usedTariff);
        client.setMinutes(minutes);
        client.setGigabytes(gigabytes);
        client.setSms(sms);
        client.setBalance(balance);
        client.setPhoneNumber(phoneNumber);

        return client;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public ClientBuilder setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public ClientBuilder setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public ClientBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ClientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public ClientBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public ClientBuilder setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Tariff getUsedTariff() {
        return usedTariff;
    }

    public ClientBuilder setUsedTariff(Tariff usedTariff) {
        this.usedTariff = usedTariff;
        return this;
    }

    public int getMinutes() {
        return minutes;
    }

    public ClientBuilder setMinutes(int minutes) {
        this.minutes = minutes;
        return this;
    }

    public int getGigabytes() {
        return gigabytes;
    }

    public ClientBuilder setGigabytes(int gigabytes) {
        this.gigabytes = gigabytes;
        return this;
    }

    public int getSms() {
        return sms;
    }

    public ClientBuilder setSms(int sms) {
        this.sms = sms;
        return this;
    }
}
