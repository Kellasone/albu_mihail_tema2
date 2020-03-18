package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button save = (Button) findViewById(R.id.button);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameAndLastName = String.valueOf(((EditText) findViewById(R.id.editText)).getText());
                float mark = Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editText2)).getText()));
                OnStudentRepositoryActionListener listener = new OnStudentRepositoryActionListener() {
                    @Override
                    public void actionSuccess() {

                        Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_LONG);
                    }

                    @Override
                    public void actionFailed() {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG);
                    }
                };
                Student student = new Student();
                student.setName(nameAndLastName);
                student.setMark(mark);
                studentRepository.insert(student, listener);
            }
        });
    }

}