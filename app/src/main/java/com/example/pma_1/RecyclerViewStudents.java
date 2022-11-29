package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pma_1.Classes.LanguageSpinner.CustomSpinnerAdapter;
import com.example.pma_1.Classes.LanguageSpinner.CustomSpinnerItem;
import com.example.pma_1.Classes.Student.Student;
import com.example.pma_1.Classes.Student.Subject;
import com.example.pma_1.Classes.StudentsRV.StudentRecyclerList;
import com.example.pma_1.Classes.StudentsRV.recyclerViewAdapter;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerViewStudents extends AppCompatActivity implements Serializable {

    private Spinner customSpinner;
    private boolean studentsListEmpty = true;
    private TextView tvStudents;
    private Locale locale;
    private Button btnNext;
    private String currentLanguage = "en", currentLang;
    private List<StudentRecyclerList> studentsList = new ArrayList<>();
    boolean skip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_students);

        tvStudents = findViewById(R.id.tvStudents);
        btnNext = findViewById(R.id.btnNext);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Gson gson = new Gson();
            if(getIntent().hasExtra("oldStudentsList")) {
                Toast.makeText(this, "ima", Toast.LENGTH_SHORT).show();
                tvStudents.setText(R.string.tv_students);
                skip = true;
                studentsListEmpty = false;
                Student recivedStudent = gson.fromJson(getIntent().getStringExtra("student"), Student.class);
                Subject recivedSubject = gson.fromJson(getIntent().getStringExtra("subject"), Subject.class);
                studentsList = (ArrayList<StudentRecyclerList>) getIntent().getSerializableExtra("oldStudentsList");
                studentsList.add(new StudentRecyclerList(recivedStudent.getName(), recivedStudent.getSurname(), recivedSubject.getSubject(), R.drawable.profile_image));
                RecyclerView recyclerView = findViewById(R.id.studentsRecyclerView);
                recyclerViewAdapter myAdapter = new recyclerViewAdapter(this, studentsList);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
            if(getIntent().hasExtra("student") && !skip) {
                tvStudents.setText(R.string.tv_students);
                studentsListEmpty = false;
                Student recivedStudent = gson.fromJson(getIntent().getStringExtra("student"), Student.class);
                Subject recivedSubject = gson.fromJson(getIntent().getStringExtra("subject"), Subject.class);
                studentsList.add(new StudentRecyclerList(recivedStudent.getName(), recivedStudent.getSurname(), recivedSubject.getSubject(), R.drawable.profile_image));
                RecyclerView recyclerView = findViewById(R.id.studentsRecyclerView);
                recyclerViewAdapter myAdapter = new recyclerViewAdapter(this, studentsList);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
            if(getIntent().hasExtra("studentsList")) {
                tvStudents.setText(R.string.tv_students);
                studentsListEmpty = false;
                studentsList = (ArrayList<StudentRecyclerList>) getIntent().getSerializableExtra("studentsList");
                RecyclerView recyclerView = findViewById(R.id.studentsRecyclerView);
                recyclerViewAdapter myAdapter = new recyclerViewAdapter(this, studentsList);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        }

        currentLanguage = getIntent().getStringExtra(currentLang);
        customSpinner = findViewById(R.id.languageSpinner);

        ArrayList<CustomSpinnerItem> customSpinnerItemsList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("Select Language");
        list.add("English");
        list.add("Hungaryan");
        list.add("Croatian");

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this, customSpinnerItemsList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customSpinner.setAdapter(adapter);

        customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("hu");
                        break;
                    case 3:
                        setLocale("hr");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateNewRecordActivity.class);
                if(!studentsListEmpty)
                    intent.putExtra("studentsList", (Serializable) studentsList);
                startActivity(intent);
                /*Intent intent = new Intent(getApplicationContext(), CreateNewRecordActivity.class);
                startActivity(intent);*/
            }
        });
    }

    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            locale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);
            this.finish();
            Intent refresh = new Intent(this, RecyclerViewStudents.class);
            if(!studentsListEmpty) {
//                String listSerializedToJson = new Gson().toJson(postsList);
                refresh.putExtra("studentsList", (Serializable) studentsList);
            }
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(RecyclerViewStudents.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }
}