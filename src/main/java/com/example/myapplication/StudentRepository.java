package com.example.myapplication;

import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentRepository {
    private Database database;

    public StudentRepository() {
        database = ApplicationController.getDatabase();
    }

    public List<Student> findAll(){
        try {
            return new FindTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(final Student student, final OnStudentRepositoryActionListener listener){
        new RemoveTask(listener).execute(student);
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

    private class RemoveTask extends AsyncTask<Student, Void, Integer> {
        OnStudentRepositoryActionListener listener;
        public RemoveTask(OnStudentRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Integer doInBackground(Student... students) {
            return database.studentDAO().delete(students[0].getName());
        }

        @Override
        protected void onPostExecute(Integer response) {
            if (response == 1) {
                listener.actionSuccess();
            } else if (response == 0 ) {
                listener.actionFailed();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            listener.actionFailed();
        }
    }

    private class FindTask extends AsyncTask<Void, Void, List<Student>> {

        @Override
        protected List<Student> doInBackground(Void... voids) {
            return database.studentDAO().findAll();
        }

    }
}
