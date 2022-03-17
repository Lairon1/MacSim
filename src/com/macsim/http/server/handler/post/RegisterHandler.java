package com.macsim.http.server.handler.post;

import com.macsim.http.server.databasecontroller.DataBaseController;
import com.macsim.http.server.handler.utils.HttpPrimitiveHandler;
import com.macsim.http.server.obj.Client;
import com.macsim.http.server.obj.ClientBuilder;
import com.serializer.json.JSONException;
import com.serializer.json.JSONObject;
import com.serializer.parser.JsonObjectSerializer;
import com.sun.net.httpserver.HttpExchange;

public class RegisterHandler extends HttpPrimitiveHandler {

    private DataBaseController db = new DataBaseController();

    @Override
    public void postHandle() {
        HttpExchange exchange = getHttpExchange();
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            sendError("Используйте POST запрос", 405);
            return;
        }

        try {
            JSONObject post = new JSONObject(getPostBody());

            String login = post.getString("Login");
            String password = post.getString("Password");
            String firstName = post.getString("Firstname");
            String lastname = post.getString("Lastname");

            if(!login.matches("[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])")){
                sendError("Невалидный логин! Логин должен быть не короче 3х символов и состоять только из латинских символов.", 401);
                return;
            }

            if(!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}")){
                sendError("Слишком легкий пароль! Пароль должен быть не короче 8ми символов, в пароле должна быть хотя-бы 1 большая буква и хотя-бы 1 цифра.", 401);
                return;
            }

            if(!db.userCanRegister(login)){
                sendError("Этот логин уже используется!", 401);
                return;
            }

            Client client = new ClientBuilder()
                    .setLogin(login)
                    .setPassword(password)
                    .setFirstname(firstName)
                    .setLastname(lastname)
                    .build();
            db.registerClient(client);

            client = db.tryClientLogin(login, password);

            JSONObject answer = new JSONObject();
            answer.put("Client", new JsonObjectSerializer().serializeObject(client));

            sendRequest(answer, 200);
        } catch (JSONException e) {
            sendError("Невалидное тело json запроса", 400);
            e.printStackTrace();
        }
    }
}
