package com.example.myapplication;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Student.class}, version=1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
