package com.example.myapplication;

import androidx.room.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("SELECT name, mark FROM student")
    LiveData<List<Student>> findAll();

    @Insert
    void insert(Student student);

    @Query("DELETE FROM student WHERE name = :studentName")
    int delete(String studentName);
}
