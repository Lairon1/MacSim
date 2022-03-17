package com.lairon.macsim.utils;

import com.lairon.macsim.obj.Client;
import com.lairon.macsim.obj.ClientBuilder;
import com.lairon.macsim.obj.Tariff;

import org.json.JSONException;
import org.json.JSONObject;

public class Parser {

    public static Client parseJsonToClient(JSONObject clientObject) throws JSONException {
        Client client = new ClientBuilder()
                .setId(clientObject.getInt("ID"))
                .setLogin(clientObject.getString("Login"))
                .setPassword(clientObject.getString("Password"))
                .setFirstname(clientObject.getString("FirstName"))
                .setLastname(clientObject.getString("LastName"))
                .setMinutes(clientObject.getInt("Minutes"))
                .setGigabytes(clientObject.getInt("Gigabytes"))
                .setSms(clientObject.getInt("SMS"))
                .setBalance(clientObject.getInt("Balance"))
                .setPhoneNumber(clientObject.getLong("PhoneNumber"))
                .build();
        if(!clientObject.isNull("UsedTariff")){
            JSONObject tariffObject = clientObject.getJSONObject("UsedTariff");
            client.setUsedTariff(parseJsonToTariff(tariffObject));
        }
        return client;
    }

    public static Tariff parseJsonToTariff(JSONObject tariffObject) throws JSONException {
        Tariff tariff = new Tariff();
        tariff.setId(tariffObject.getInt("ID"));
        tariff.setName(tariffObject.getString("Name"));
        tariff.setPrice(tariffObject.getInt("Price"));
        if(!tariffObject.isNull("Description")) tariff.setDescription(tariffObject.getString("Description"));
        tariff.setMinutes(tariffObject.getInt("Minutes"));
        tariff.setGigabytes(tariffObject.getInt("Gigabytes"));
        tariff.setSms(tariffObject.getInt("SMS"));
        return tariff;
    }


}
