package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;

public class StudentRepository {
    private Database database;

    public StudentRepository() {
        database = ApplicationController.getDatabase();
    }

    public void insert(final Student student, final OnStudentRepositoryActionListener listener) {
        new InsertTask(listener).execute(student);
    }

    private class InsertTask extends AsyncTask<Student, Void, Void> {
        OnStudentRepositoryActionListener listener;

        InsertTask(OnStudentRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Student... students) {
            database.studentDAO().insert(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }
}
