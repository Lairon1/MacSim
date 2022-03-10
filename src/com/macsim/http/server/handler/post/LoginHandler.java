package com.macsim.http.server.handler.post;

import com.macsim.http.server.databasecontroller.DataBaseController;
import com.macsim.http.server.handler.HttpPrimitiveHandler;
import com.macsim.http.server.obj.Client;
import com.serializer.json.JSONException;
import com.serializer.json.JSONObject;
import com.serializer.parser.JsonObjectSerializer;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;



public class LoginHandler extends HttpPrimitiveHandler {

    private DataBaseController db = DataBaseController.getInstance();
    private JsonObjectSerializer serializer = new JsonObjectSerializer();

    @Override
    public void postHandle() {
        HttpExchange exchange = getHttpExchange();
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            sendError("Use POST request method", 405);
            return;
        }

        JSONObject answer = new JSONObject();
        try {
            JSONObject requestJson = new JSONObject(getPostBody());
            String login = requestJson.getString("Login");
            String password = requestJson.getString("Password");
            Client client = db.tryClientLogin(login, password);
            if (client == null) {
                sendError("Неверный логин или пароль", 401);
                return;
            }
            answer.put("Client", serializer.serializeObject(client));


            sendRequest(answer.toString(), 200);
        } catch (JSONException e) {
            sendError( "Invalid request json", 400);
            return;
        }
    }


}
