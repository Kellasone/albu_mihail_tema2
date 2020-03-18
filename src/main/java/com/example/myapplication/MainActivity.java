package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String emptyString = "";
    StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button save = (Button) findViewById(R.id.button);
        final Button remove = (Button) findViewById(R.id.button2);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String nameAndLastName = String.valueOf(((EditText) findViewById(R.id.editText)).getText());
                float mark;
                String markString = String.valueOf(((EditText) findViewById(R.id.editText2)).getText());
                if (markString.equals(emptyString))
                    mark = 0;
                else
                    mark = Float.parseFloat(markString);
                OnStudentRepositoryActionListener listener = new OnStudentRepositoryActionListener() {
                    @Override
                    public void actionSuccess() {
                        Toast.makeText(getApplicationContext(), "Successfuly inserted entry!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void actionFailed() {
                    }
                };
                Student student = new Student();
                student.setName(nameAndLastName);
                student.setMark(mark);
                studentRepository.insert(student, listener);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnStudentRepositoryActionListener listener = new OnStudentRepositoryActionListener() {
                    @Override
                    public void actionSuccess() {
                        Toast.makeText(getApplicationContext(), "Successfuly removed entry!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void actionFailed() {
                        Toast.makeText(getApplicationContext(), "Student not in database!", Toast.LENGTH_SHORT).show();
                    }
                };
                String nameAndLastName = String.valueOf(((EditText) findViewById(R.id.editText)).getText());
                Student student = new Student();
                student.setName(nameAndLastName);
                studentRepository.remove(student, listener);

            }
        });

    }

}
