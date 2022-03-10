package com.macsim.http.server.handler.post;

import com.macsim.http.server.databasecontroller.DataBaseController;
import com.macsim.http.server.handler.HttpPrimitiveHandler;
import com.macsim.http.server.obj.Client;
import com.macsim.http.server.obj.ClientBuilder;
import com.serializer.json.JSONException;
import com.serializer.json.JSONObject;
import com.serializer.parser.JsonObjectSerializer;
import com.sun.net.httpserver.HttpExchange;

public class RegisterHandler extends HttpPrimitiveHandler {

    private DataBaseController db = DataBaseController.getInstance();

    @Override
    public void postHandle() {
        HttpExchange exchange = getHttpExchange();
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            sendError("Use POST request method", 405);
            return;
        }

        try {
            JSONObject post = new JSONObject(getPostBody());

            String login = post.getString("Login");
            String password = post.getString("Password");
            String firstName = post.getString("Firstname");
            String lastname = post.getString("Lastname");

            if(!login.matches("[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])")){
                sendError("This login is not allowed", 401);
                return;
            }

            if(!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}")){
                sendError("This password is not allowed", 401);
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
            sendRequest(new JsonObjectSerializer().serializeObject(client), 200);
        } catch (JSONException e) {
            sendError("Invalid request json", 400);
        }
    }
}
