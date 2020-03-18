package com.example.myapplication;

import android.app.Application;

import androidx.room.Room;

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static Database database;

    public static ApplicationController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), Database.class, "Student").build();
    }

    public static Database getDatabase() {
        return database;
    }
}
