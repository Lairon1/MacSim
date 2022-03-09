package com.macsim.http.server.starter;

import com.macsim.http.server.Server;

public class ServerStarter {

    public static void main(String[] args) {
        Server server = new Server();
        server.start(80);
    }

}
