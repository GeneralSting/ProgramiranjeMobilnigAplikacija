package com.example.pma_1;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.pma_1.Classes.StudentRecyclerList;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class Subject {
    private String name;
    private String surname;
    private int year;
    private int lectures;
    private int practices;

    public Subject(String name, String surname, int year, int lectures, int practices) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.lectures = lectures;
        this.practices = practices;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLectures() {
        return lectures;
    }

    public void setLectures(int lectures) {
        this.lectures = lectures;
    }

    public int getPractices() {
        return practices;
    }

    public void setPractices(int practices) {
        this.practices = practices;
    }

    public void openSummaryActivity(@NonNull SubjectInfoActivity activity, Subject subject, Student recivedStudent) {
        activity.finish();
        Intent intent = new Intent(activity, SummaryActivity.class);
        intent.putExtra("student", convertObject(recivedStudent));
        intent.putExtra("subject", convertObject(subject));
        activity.startActivity(intent);
    }

    public void openSummaryActivity2(@NonNull SubjectInfoActivity activity, Subject subject, Student recivedStudent, List<StudentRecyclerList> students) {
        activity.finish();
        Intent intent = new Intent(activity, SummaryActivity.class);
        intent.putExtra("student", convertObject(recivedStudent));
        intent.putExtra("subject", convertObject(subject));
        intent.putExtra("studentsList", (Serializable) students);
        activity.startActivity(intent);
    }

    public String convertObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson((object));
    }
}
