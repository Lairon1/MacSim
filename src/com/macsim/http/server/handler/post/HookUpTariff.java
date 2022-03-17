package com.macsim.http.server.handler.post;

import com.macsim.http.server.databasecontroller.DataBaseController;
import com.macsim.http.server.handler.utils.HttpPrimitiveHandler;
import com.macsim.http.server.obj.Client;
import com.macsim.http.server.obj.Tariff;
import com.serializer.json.JSONException;
import com.serializer.json.JSONObject;
import com.serializer.parser.JsonObjectSerializer;
import com.sun.net.httpserver.HttpExchange;

public class HookUpTariff extends HttpPrimitiveHandler {

    private DataBaseController db = new DataBaseController();
    private JsonObjectSerializer serializer = new JsonObjectSerializer();

    @Override
    public void postHandle() {
        HttpExchange exchange = getHttpExchange();
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            sendError("Используйте POST запрос", 405);
            return;
        }

        try {
            JSONObject jsonObject = new JSONObject(getPostBody());
            Client client = db.tryClientLogin(jsonObject.getString("Login"), jsonObject.getString("Password"));
            if(client == null){
                sendError("Неправильный логин или пароль", 401);
                return;
            }
            Tariff tariff = db.getTariffByID(jsonObject.getInt("TariffID"));
            if(tariff == null){
                sendError("Тариф не найден", 401);
                return;
            }

            if(client.getBalance() < tariff.getPrice()){
                sendError("У вас недостаточно средств", 401);
                return;
            }

            client.setBalance((int) (client.getBalance() - tariff.getPrice()));
            client.setUsedTariff(tariff);
            client.setGigabytes(tariff.getGigabytes());
            client.setSms(tariff.getSms());
            client.setMinutes(tariff.getMinutes());

            if(db.updateClient(client)){
                JSONObject answer = new JSONObject();
                answer.put("Client", new JsonObjectSerializer().serializeObject(client));
                sendRequest(answer.toString(), 200);
                return;
            }

            sendError("Серверная ошибка", 500);
        }catch (JSONException e){
            sendError("Невалидное тело json запроса", 400);
        }

    }
}
