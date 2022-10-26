package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editSubject;
    private Button btnExitSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editName = findViewById(R.id.ptxtName);
            editSubject = findViewById(R.id.ptxtSubject);
            Student student = new Student(extras.getString("studentName"), extras.getString("studentSubject"));
            editName.setText(student.getName());
            editSubject.setText(student.getSubject());

            btnExitSummary = findViewById(R.id.btnExitSummary);
            btnExitSummary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    student.exitSummary(SummaryActivity.this);
                }
            });
        }
        else
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
    }
}