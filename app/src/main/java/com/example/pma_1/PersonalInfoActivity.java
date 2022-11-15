package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pma_1.Classes.StudentRecyclerList;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PersonalInfoActivity extends AppCompatActivity {

    private Button btnSendStudent;
    private TextInputLayout tilName;
    private TextInputLayout tilSurname;
    private boolean recivedList = false;

    final Calendar myCalendar = Calendar.getInstance();
    private EditText birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info_activity);



        if(getIntent().hasExtra("studentsList"))
            recivedList = true;

        this.setTitle("PMA - Add Student ");

        tilName = findViewById(R.id.tvStudentName);
        tilSurname = findViewById(R.id.tvStudentSurname);
        btnSendStudent = findViewById(R.id.btnSendProfessor);
        birthDate = (EditText) findViewById(R.id.etBirthDate);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(PersonalInfoActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSendStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tilName.getEditText().getText().toString().equals("") || tilSurname.getEditText().getText().toString().equals("") || birthDate.getText().toString().equals(""))
                    Toast.makeText(PersonalInfoActivity.this, "To proceed, fill all fields. ", Toast.LENGTH_SHORT).show();
                else {
                    Student student = new Student(tilName.getEditText().getText().toString(), tilSurname.getEditText().getText().toString(), birthDate.getText().toString());
                    if(recivedList)
                        student.openStudentActivity2(PersonalInfoActivity.this, student, (List<StudentRecyclerList>) getIntent().getSerializableExtra("studentsList"));
                    else
                        student.openStudentActivity(PersonalInfoActivity.this, student);
                }
            }
        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.GERMANY);
        birthDate.setText(dateFormat.format(myCalendar.getTime()));
    }

}