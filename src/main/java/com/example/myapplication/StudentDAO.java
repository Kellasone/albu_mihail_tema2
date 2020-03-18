package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("SELECT name, mark FROM student")
    List<Student> findAll();

    @Insert
    void insert(Student student);

    @Query("DELETE FROM student WHERE name = :studentName")
    int delete(String studentName);
}
