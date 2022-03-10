package com.lairon.macsim.utils;

import com.lairon.macsim.UserDataController;
import com.lairon.macsim.obj.Client;

public class Globals {

    private static Client currentClient;
    private static UserDataController userDataController;

    public static Client getCurrentClient() {
        return currentClient;
    }

    public static void setCurrentClient(Client currentClient) {
        Globals.currentClient = currentClient;
    }

    public static UserDataController getUserDataController() {
        return userDataController;
    }

    public static void setUserDataController(UserDataController userDataController) {
        Globals.userDataController = userDataController;
    }
}
