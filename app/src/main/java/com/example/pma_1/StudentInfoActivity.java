package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class StudentInfoActivity extends AppCompatActivity {

    private String sendRecivedName;
    private TextInputLayout tilSubject;
    private Button btnSendSubject;
    private StudentInfo studentInfo = new StudentInfo("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sendRecivedName = extras.getString("studentName");
            tilSubject = findViewById(R.id.tilSubject);
            btnSendSubject = findViewById(R.id.btnSendSubject);
            btnSendSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tilSubject.getEditText().getText().toString().equals(""))
                        Toast.makeText(StudentInfoActivity.this, "To proceed, enter subject. ", Toast.LENGTH_SHORT).show();
                    else {
                        studentInfo.setSubject(tilSubject.getEditText().getText().toString());
                        studentInfo.openSummaryActivity(StudentInfoActivity.this, studentInfo.getSubject(), sendRecivedName);
                    }
                }
            });
        }
        else
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
    }
}