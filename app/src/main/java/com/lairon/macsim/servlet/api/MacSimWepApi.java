package com.lairon.macsim.servlet.api;

import com.lairon.macsim.http.callback.ClientCallBack;
import com.lairon.macsim.http.callback.HttpCallBack;
import com.lairon.macsim.http.callback.TariffsCallBack;
import com.lairon.macsim.http.helper.GetHttpRequest;
import com.lairon.macsim.http.helper.PostHttpRequest;
import com.lairon.macsim.http.utils.HttpServerULR;
import com.lairon.macsim.obj.Client;
import com.lairon.macsim.obj.Tariff;
import com.lairon.macsim.utils.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MacSimWepApi {

    public void registerUser(String login, String password, String firstname, String lastname, ClientCallBack callBack) {

        if (!login.matches("[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])"))
            throw new IllegalArgumentException("Невалидный логин!\nЛогин должен быть не короче 3х символов и состоять только из латинских символов.");

        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}"))
            throw new IllegalArgumentException("Слишком легкий пароль!\nПароль должен быть не короче 8ми символов, в пароле дожна быть хотябы 1 большая буква и хотябы 1 цыфра.");

        if (firstname.length() <= 3)
            throw new IllegalArgumentException("Ваше имя не может быть короче 3х символов.");

        if (lastname.length() <= 3)
            throw new IllegalArgumentException("Ваша фамилия не может быть короче 4рех символов.");

        JSONObject body = new JSONObject();
        try {
            body.put("Login", login);
            body.put("Password", password);
            body.put("Firstname", firstname);
            body.put("Lastname", lastname);
            new PostHttpRequest((request, code) -> {
                try {
                    if (code != 200) return;
                    JSONObject result = new JSONObject(request);
                    JSONObject clientObject = result.getJSONObject("Client");
                    callBack.onCallBack(Parser.parseJsonToClient(clientObject));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).execute(HttpServerULR.REGISTER, body.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getAllTariffs(TariffsCallBack tariffsCallBack){
        new GetHttpRequest((request, code) -> {
            if(code != 200) {
                tariffsCallBack.onCallBack(null);
                return;
            }
            try {
                JSONArray tariffs = new JSONObject(request).getJSONArray("Tariffs");
                List<Tariff> tariffList = new ArrayList<>();
                for (int i = 0; i < tariffs.length(); i++) {
                    JSONObject tariffObject = tariffs.getJSONObject(i);
                    Tariff tariff = Parser.parseJsonToTariff(tariffObject);
                    tariffList.add(tariff);
                }
                tariffsCallBack.onCallBack(tariffList);
            } catch (JSONException e) {
                tariffsCallBack.onCallBack(null);
                return;
            }
        }).execute(HttpServerULR.TARIFF);
    }

    public void topUpBalance(Client client, int countRubs, ClientCallBack callBack) {
        JSONObject body = new JSONObject();
        try {
            body.put("Login", client.getLogin());
            body.put("Password", client.getPassword());
            body.put("TopUpBalance", countRubs);
            new PostHttpRequest((request, code) -> {
                try {
                    JSONObject result = new JSONObject(request);
                    if (code != 200){
                        callBack.onCallBack(null);
                        return;
                    }
                    JSONObject clientObject = result.getJSONObject("Client");
                    callBack.onCallBack(Parser.parseJsonToClient(clientObject));
                } catch (JSONException e) {
                    callBack.onCallBack(null);
                }
            }).execute(HttpServerULR.TOP_UP_BALANCE, body.toString());
        } catch (JSONException e) {
            callBack.onCallBack(null);
            e.printStackTrace();
        }
    }


    public void loginUser(String login, String password, ClientCallBack callBack) {
        if (!login.matches("[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])"))
            throw new IllegalArgumentException("Невалидный логин!\nЛогин должен быть не короче 3х символов и состоять только из латинских символов.");

        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}"))
            throw new IllegalArgumentException("Слишком легкий пароль!\nПароль должен быть не короче 8ми символов, в пароле дожна быть хотябы 1 большая буква и хотябы 1 цыфра.");

        try {
            JSONObject body = new JSONObject();
            body.put("Login", login);
            body.put("Password", password);
            new PostHttpRequest((request, code) -> {
                try {
                    JSONObject result = new JSONObject(request);
                    if (code != 200) return;
                    JSONObject clientObject = result.getJSONObject("Client");
                    callBack.onCallBack(Parser.parseJsonToClient(clientObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }).execute(HttpServerULR.LOGIN, body.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
