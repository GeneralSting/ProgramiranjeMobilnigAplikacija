package com.example.pma_1;

import android.content.Intent;

import com.example.pma_1.Classes.StudentRecyclerList;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class Student {
    private String name;
    private String surname;
    private String birthDate;

    public Student() {
    }

    public Student(String name, String surname, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void openStudentActivity(PersonalInfoActivity activity, Student student ) {
        activity.finish();
        Intent intent = new Intent(activity, SubjectInfoActivity.class);
        intent.putExtra("studentObj", convertObject(student));
        activity.startActivity(intent);
    }

    public void openStudentActivity2(PersonalInfoActivity activity, Student student, List<StudentRecyclerList> students) {
        activity.finish();
        Intent intent = new Intent(activity, SubjectInfoActivity.class);
        intent.putExtra("studentObj", convertObject(student));
        intent.putExtra("studentsList", (Serializable) students);
        activity.startActivity(intent);
    }

    public String convertObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson((object));
    }
}
