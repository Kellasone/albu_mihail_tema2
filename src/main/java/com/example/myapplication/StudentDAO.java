package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("SELECT * FROM student")
    List<Student> findAll();

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);
}