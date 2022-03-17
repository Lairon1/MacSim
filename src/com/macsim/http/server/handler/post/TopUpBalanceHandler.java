package com.macsim.http.server.handler.post;

import com.macsim.http.server.databasecontroller.DataBaseController;
import com.macsim.http.server.handler.utils.HttpPrimitiveHandler;
import com.macsim.http.server.obj.Client;
import com.serializer.json.JSONException;
import com.serializer.json.JSONObject;
import com.serializer.parser.JsonObjectSerializer;
import com.sun.net.httpserver.HttpExchange;

public class TopUpBalanceHandler extends HttpPrimitiveHandler {

    private DataBaseController db = new DataBaseController();
    private JsonObjectSerializer serializer = new JsonObjectSerializer();

    @Override
    public void postHandle() {
        HttpExchange exchange = getHttpExchange();
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            sendError("Используйте POST запрос", 405);
            return;
        }

        JSONObject answer = new JSONObject();
        try {
            JSONObject requestJson = new JSONObject(getPostBody());
            String login = requestJson.getString("Login");
            String password = requestJson.getString("Password");
            int topUpBalance = requestJson.getInt("TopUpBalance");
            Client client = db.tryClientLogin(login, password);

            if(topUpBalance <= 0){
                sendError("Невалидная сума пополнения", 401);
                return;
            }

            if (client == null) {
                sendError("Неверный логин или пароль", 401);
                return;
            }

            client.setBalance(client.getBalance() + topUpBalance);

            if(db.updateClient(client)){
                answer.put("Client", serializer.serializeObject(db.tryClientLogin(login, password)));
                sendRequest(answer.toString(), 200);
                return;
            }
            sendError("Серверная ошибка", 500);
        } catch (JSONException e) {
            sendError( "Невалидное тело json запроса", 400);
            return;
        }
    }
}
