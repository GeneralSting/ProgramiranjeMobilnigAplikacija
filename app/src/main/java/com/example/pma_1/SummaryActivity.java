package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pma_1.Classes.StudentRecyclerList;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class SummaryActivity extends AppCompatActivity implements Serializable{

    private EditText etStudentName;
    private EditText etStudentSurname;
    private EditText etStudentBirthDate;
    private EditText etProfessorName;
    private EditText etProfessorSurname;
    private EditText etsubjectYear;
    private EditText etSubjectLectures;
    private EditText etSubjectPractices;
    private Button btnExitSummary;
    private boolean recivedList = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        if(getIntent().hasExtra("studentsList"))
            recivedList = true;

        this.setTitle("PMA - Summary");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Gson gson = new Gson();
            Student recivedStudent = gson.fromJson(getIntent().getStringExtra("student"), Student.class);
            Subject recivedSubject = gson.fromJson(getIntent().getStringExtra("subject"), Subject.class);

            Summary summary = new Summary(recivedStudent.getName().toString(), recivedStudent.getSurname().toString(), recivedStudent.getBirthDate().toString(),
                    recivedSubject.getName().toString(), recivedSubject.getSurname().toString(), recivedSubject.getYear(), recivedSubject.getLectures(),
                    recivedSubject.getPractices());

            btnExitSummary = findViewById(R.id.btnExitSummary);
            etStudentName = findViewById(R.id.tvStudentName);
            etStudentSurname = findViewById(R.id.tvStudentSurname);
            etStudentBirthDate = findViewById(R.id.tvStudentBirthDate);
            etProfessorName = findViewById(R.id.tvProfessorName);
            etProfessorSurname = findViewById(R.id.tvProfessorSurname);
            etsubjectYear = (EditText) findViewById(R.id.tvCollegeYear);
            etSubjectLectures = (EditText) findViewById(R.id.tvLectures);
            etSubjectPractices = (EditText) findViewById(R.id.tvPractices);

            etStudentName.setText(summary.getStudentName());
            etStudentSurname.setText(summary.getStudentSurname());
            etStudentBirthDate.setText(summary.getStudentBirthDate());
            etProfessorName.setText(summary.getProfessorName());
            etProfessorSurname.setText(summary.getProfessorSurname());
            etsubjectYear.setText(""+ summary.getSubjectYear());
            etSubjectLectures.setText(""+ summary.getSubjectLectures());
            etSubjectPractices.setText(""+ summary.getSubjectPractices());

            btnExitSummary = findViewById(R.id.btnExitSummary);
            btnExitSummary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    summary.exitSummary(SummaryActivity.this, recivedStudent, recivedSubject);
                    if(recivedList)
                        summary.exitSummary2(SummaryActivity.this, recivedStudent, recivedSubject, (List<StudentRecyclerList>) getIntent().getSerializableExtra("studentsList"));
                    else
                        summary.exitSummary(SummaryActivity.this, recivedStudent, recivedSubject);
                }
            });
        }
        else
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
    }

    public String convertObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson((object));
    }
}