package com.macsim.http.server.handler.post;

import com.macsim.http.server.databasecontroller.DataBaseController;
import com.macsim.http.server.handler.utils.HttpPrimitiveHandler;
import com.macsim.http.server.obj.Client;
import com.serializer.json.JSONException;
import com.serializer.json.JSONObject;
import com.serializer.parser.JsonObjectSerializer;
import com.sun.net.httpserver.HttpExchange;

public class ChangePasswordHandler extends HttpPrimitiveHandler {

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
            String changePass = requestJson.getString("ChangePassword");
            Client client = db.tryClientLogin(login, password);

            if (client == null) {
                sendError("Неверный логин или пароль", 401);
                return;
            }

            if(!changePass.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}")){
                sendError("Слишком легкий пароль! Пароль должен быть не короче 8ми символов, в пароле должна быть хотя-бы 1 большая буква и хотя-бы 1 цифра.", 401);
                return;
            }

            client.setPassword(changePass);

            if(db.updateClient(client)){
                answer.put("Client", serializer.serializeObject(db.tryClientLogin(login, changePass)));
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
