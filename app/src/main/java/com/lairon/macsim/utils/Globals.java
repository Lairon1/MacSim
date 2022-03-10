package com.lairon.macsim.utils;

import com.lairon.macsim.obj.Client;

public class Globals {
    private static Client currentClient;

    public static Client getCurrentClient() {
        return currentClient;
    }

    public static void setCurrentClient(Client currentClient) {
        Globals.currentClient = currentClient;
    }
}
