package com.macsim.http.server.handler.get;

import com.macsim.http.server.handler.utils.HttpPrimitiveHandler;

public class ServerConnectionHandler extends HttpPrimitiveHandler {
    @Override
    public void postHandle(){
        sendRequest("Server running", 200);
    }
}
