package com.example.lastassignment;

import android.app.Application;

import com.example.lastassignment.database.DBManager;

public class myApp extends Application {
    private NetworkService networkingService = new NetworkService();
    private JsonService jsonService = new JsonService();
    private DBManager databaseManager = new DBManager();

    public DBManager getDatabaseManager() {
        return databaseManager;
    }

    public NetworkService getNetworkingService() {
        return networkingService;
    }

    public JsonService getJsonService() {
        return jsonService;
    }
}
