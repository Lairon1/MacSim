package com.macsim.http.server.handler;

import com.serializer.json.JSONException;
import com.serializer.json.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class HttpPrimitiveHandler implements HttpHandler {

    private HttpExchange httpExchange;
    private String postBody;

    @Override
    public void handle(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        if(httpExchange.getRequestMethod().equalsIgnoreCase("POST"))postBody = new Scanner(httpExchange.getRequestBody()).next();
        postHandle();
    }

    public abstract void postHandle();

    public void sendRequest(Object obj, int code){
        sendRequest(obj.toString(), code);
    }

    public void sendRequest(String request, int code){
        try {
            OutputStream outputStream = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(code, request.getBytes(StandardCharsets.UTF_8).length);
            outputStream.write(request.getBytes(StandardCharsets.UTF_8));
            httpExchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(byte[] bytes, int code){
        try {
            OutputStream outputStream = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(code, bytes.length);
            outputStream.write(bytes);
            httpExchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendError(String errorMessage, int code) {
        try {
            OutputStream outputStream = httpExchange.getResponseBody();
            JSONObject answer = new JSONObject();
            answer.put("ERROR", errorMessage);
            httpExchange.sendResponseHeaders(code, answer.toString().length());
            outputStream.write(answer.toString().getBytes());
            httpExchange.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public HttpExchange getHttpExchange() {
        return httpExchange;
    }

    public String getPostBody() {
        return postBody;
    }
}
