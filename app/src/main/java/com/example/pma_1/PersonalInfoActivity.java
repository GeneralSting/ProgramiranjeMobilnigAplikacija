package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class PersonalInfoActivity extends AppCompatActivity {

    private Button btnSendName;
    private TextInputLayout tilName;
    private PersonalInfo personalInfo = new PersonalInfo("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info_activity);

        tilName = findViewById(R.id.tilName);
        btnSendName = findViewById(R.id.btnSendName);
        btnSendName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tilName.getEditText().getText().toString().equals(""))
                    Toast.makeText(PersonalInfoActivity.this, "To proceed, enter your name. ", Toast.LENGTH_SHORT).show();
                else {
                    personalInfo.setName(tilName.getEditText().getText().toString());
                    personalInfo.openStudentActivity(PersonalInfoActivity.this, personalInfo.getName());
                }
            }
        });
    }
}