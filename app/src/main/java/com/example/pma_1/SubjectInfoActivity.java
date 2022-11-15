package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pma_1.Classes.StudentRecyclerList;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.List;

public class SubjectInfoActivity extends AppCompatActivity {

    private TextInputLayout tilName;
    private TextInputLayout tilSurname;
    private EditText tilYear;
    private EditText tilLectures;
    private EditText tilPractices;
    private Button btnSendSubject;
    private boolean recivedList = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_info_activity);

        if(getIntent().hasExtra("studentsList"))
            recivedList = true;

        this.setTitle("PMA - Add Subject ");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Gson gson = new Gson();
            Student recivedStudent = gson.fromJson(getIntent().getStringExtra("studentObj"), Student.class);

            tilName = findViewById(R.id.tvStudentName);
            tilSurname = findViewById(R.id.tvStudentSurname);
            tilYear = findViewById(R.id.etCollegeYear);
            tilLectures = findViewById(R.id.etLectures);
            tilPractices = findViewById(R.id.etPractices);
            btnSendSubject = findViewById(R.id.btnSendProfessor);
            btnSendSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tilName.getEditText().getText().toString().equals("") ||
                        tilSurname.getEditText().getText().toString().equals("") ||
                        tilYear.getText().toString().equals("") ||
                        tilPractices.getText().toString().equals("") ||
                        tilLectures.getText().toString().equals(""))
                        Toast.makeText(SubjectInfoActivity.this, "To proceed, fill all fields ", Toast.LENGTH_SHORT).show();
                    else {
                        Subject subject = new Subject(tilName.getEditText().getText().toString(), tilSurname.getEditText().getText().toString(),
                                Integer.parseInt(tilYear.getText().toString()), Integer.parseInt(tilPractices.getText().toString()),
                                Integer.parseInt(tilLectures.getText().toString()));
                        if(recivedList)
                            subject.openSummaryActivity2(SubjectInfoActivity.this, subject, recivedStudent, (List<StudentRecyclerList>) getIntent().getSerializableExtra("studentsList"));
                        else
                            subject.openSummaryActivity(SubjectInfoActivity.this, subject, recivedStudent);
                    }
                }
            });
        }
        else
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
    }
}