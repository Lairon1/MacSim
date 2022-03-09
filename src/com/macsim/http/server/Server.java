package com.macsim.http.server;

import com.macsim.http.server.databasecontroller.DataBaseController;
import com.macsim.http.server.handler.get.ServerConnectionHandler;
import com.macsim.http.server.handler.get.TariffHandler;
import com.macsim.http.server.handler.post.LoginHandler;
import com.macsim.http.server.handler.post.RegisterHandler;
import com.macsim.http.server.handler.post.TopUpBalanceHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {

    private Logger logger = Logger.getLogger(Server.class.getSimpleName());
    private HttpServer server;

    public void start(int port){
        DataBaseController.getInstance();
        try {
            server = HttpServer.create(new InetSocketAddress(port), 1);
            server.setExecutor(Executors.newSingleThreadExecutor());
            server.start();
            defaultInit();
            logger.info("Server started on port " + port);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    private void defaultInit(){
        addHttpHandler("/login", new LoginHandler());
        addHttpHandler("/tariff", new TariffHandler());
        addHttpHandler("/register", new RegisterHandler());
        addHttpHandler("/server", new ServerConnectionHandler());
        addHttpHandler("/topUpBalance", new TopUpBalanceHandler());
    }

    public void addHttpHandler(String path, HttpHandler httpHandler){
        server.createContext(path, httpHandler);
        logger.info("Add response handler. Path: " + path);
    }

}
