package com.lairon.macsim.servlet.api;

import com.lairon.macsim.http.utils.HttpServerULR;
import com.lairon.macsim.obj.Client;
import com.lairon.macsim.obj.Tariff;
import com.lairon.macsim.utils.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MacSimWepApi {

    public Client registerUser(String login, String password, String firstname, String lastname) throws Exception {

        if (!login.matches("[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])"))
            throw new IllegalArgumentException("Невалидный логин!\nЛогин должен быть не короче 3х символов и состоять только из латинских символов.");

        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}"))
            throw new IllegalArgumentException("Слишком легкий пароль!\nПароль должен быть не короче 8ми символов, в пароле дожна быть хотябы 1 большая буква и хотябы 1 цыфра.");

        if (firstname.length() <= 3)
            throw new IllegalArgumentException("Ваше имя не может быть короче 3х символов.");

        if (lastname.length() <= 3)
            throw new IllegalArgumentException("Ваша фамилия не может быть короче 4рех символов.");

        JSONObject body = new JSONObject();
        body.put("Login", login);
        body.put("Password", password);
        body.put("Firstname", firstname);
        body.put("Lastname", lastname);

        HttpURLConnection connection = (HttpURLConnection) new URL(HttpServerULR.getInstance().getRegister()).openConnection();

        connection.setRequestMethod("POST");

        connection.setDoInput(true);
        connection.setDoOutput(true);

        new PrintWriter(connection.getOutputStream(), true).println(body.toString());

        int code = connection.getResponseCode();

        if (code != 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            throw new Exception(jsonObject.getString("ERROR"));
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JSONObject clientObject = new JSONObject(bufferedReader.readLine());
        Client client = Parser.parseJsonToClient(clientObject.getJSONObject("Client"));
        return client;
    }

    public Client hookUpTariff(Client client, Tariff tariff) throws Exception {
        JSONObject body = new JSONObject();

        body.put("Login", client.getLogin());
        body.put("Password", client.getPassword());
        body.put("TariffID", tariff.getId());
        HttpURLConnection connection = (HttpURLConnection) new URL(HttpServerULR.getInstance().getHookUpTariff()).openConnection();

        connection.setRequestMethod("POST");

        connection.setDoInput(true);
        connection.setDoOutput(true);

        new PrintWriter(connection.getOutputStream(), true).println(body.toString());

        int code = connection.getResponseCode();

        if (code != 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            throw new Exception(jsonObject.getString("ERROR"));
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JSONObject clientObject = new JSONObject(bufferedReader.readLine());
        Client clientRequest = Parser.parseJsonToClient(clientObject.getJSONObject("Client"));

        return clientRequest;
    }

    public List<Tariff> getAllTariffs() throws Exception {
        List<Tariff> tariffs = new ArrayList<>();
        HttpURLConnection connection = (HttpURLConnection) new URL(HttpServerULR.getInstance().getTariff()).openConnection();
        connection.setDoInput(true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        if (connection.getResponseCode() != 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            throw new Exception(jsonObject.getString("ERROR"));
        }


        JSONObject result = new JSONObject(reader.readLine());

        JSONArray tariffsJsonArray = result.getJSONArray("Tariffs");

        for (int i = 0; i < tariffsJsonArray.length(); i++) {
            JSONObject tariffObject = tariffsJsonArray.getJSONObject(i);
            Tariff tariff = Parser.parseJsonToTariff(tariffObject);
            tariffs.add(tariff);
        }

        return tariffs;
    }


    public Client topUpBalance(Client client, int countRubs) throws Exception {
        JSONObject body = new JSONObject();

        body.put("Login", client.getLogin());
        body.put("Password", client.getPassword());
        body.put("TopUpBalance", countRubs);
        HttpURLConnection connection = (HttpURLConnection) new URL(HttpServerULR.getInstance().getTopUpBalance()).openConnection();

        connection.setRequestMethod("POST");

        connection.setDoInput(true);
        connection.setDoOutput(true);

        new PrintWriter(connection.getOutputStream(), true).println(body.toString());

        int code = connection.getResponseCode();

        if (code != 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            throw new Exception(jsonObject.getString("ERROR"));
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JSONObject clientObject = new JSONObject(bufferedReader.readLine());
        Client clientRequest = Parser.parseJsonToClient(clientObject.getJSONObject("Client"));

        return clientRequest;
    }

    public Client changePassword(Client client, String newPass) throws Exception {
        if (!newPass.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}"))
            throw new IllegalArgumentException("Слишком легкий пароль! Пароль должен быть не короче 8ми символов, в пароле должна быть хотя-бы 1 большая буква и хотя-бы 1 цифра.");


        JSONObject body = new JSONObject();

        body.put("Login", client.getLogin());
        body.put("Password", client.getPassword());
        body.put("ChangePassword", newPass);


        HttpURLConnection connection = (HttpURLConnection) new URL(HttpServerULR.getInstance().getChangePassword()).openConnection();

        connection.setRequestMethod("POST");

        connection.setDoInput(true);
        connection.setDoOutput(true);

        new PrintWriter(connection.getOutputStream(), true).println(body.toString());

        int code = connection.getResponseCode();

        if (code != 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            throw new Exception(jsonObject.getString("ERROR"));
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JSONObject clientObject = new JSONObject(bufferedReader.readLine());
        return Parser.parseJsonToClient(clientObject.getJSONObject("Client"));
    }

    public Client loginUser(String login, String password) throws Exception {
        if (!login.matches("[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])"))
            throw new IllegalArgumentException("Невалидный логин!\nЛогин должен быть не короче 3х символов и состоять только из латинских символов.");

        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}"))
            throw new IllegalArgumentException("Слишком легкий пароль! Пароль должен быть не короче 8ми символов, в пароле должна быть хотя-бы 1 большая буква и хотя-бы 1 цифра.");


        JSONObject body = new JSONObject();
        body.put("Login", login);
        body.put("Password", password);

        HttpURLConnection connection = (HttpURLConnection) new URL(HttpServerULR.getInstance().getLogin()).openConnection();

        connection.setRequestMethod("POST");

        connection.setDoInput(true);
        connection.setDoOutput(true);

        new PrintWriter(connection.getOutputStream(), true).println(body.toString());

        int code = connection.getResponseCode();

        if (code != 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            throw new Exception(jsonObject.getString("ERROR"));
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JSONObject clientObject = new JSONObject(bufferedReader.readLine());
        Client client = Parser.parseJsonToClient(clientObject.getJSONObject("Client"));

        return client;

    }
}
