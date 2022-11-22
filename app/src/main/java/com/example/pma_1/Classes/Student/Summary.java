package com.example.pma_1.Classes.Student;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pma_1.Classes.StudentsRV.StudentRecyclerList;
import com.example.pma_1.RecyclerViewStudents;
import com.example.pma_1.SummaryActivity;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class Summary {
    private String studentName;
    private String studentSurname;
    private String studentBirthDate;
    private String professorName;
    private String subjectName;
    private int subjectYear;
    private int subjectLectures;
    private int subjectPractices;

    public Summary(String studentName, String studentSurname, String studentBirthDate, String professorName, String professorSurname, int subjectYear, int subjectLectures, int subjectPractices) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentBirthDate = studentBirthDate;
        this.professorName = professorName;
        this.subjectName = professorSurname;
        this.subjectYear = subjectYear;
        this.subjectLectures = subjectLectures;
        this.subjectPractices = subjectPractices;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentBirthDate() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(String studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectYear() {
        return subjectYear;
    }

    public void setSubjectYear(int subjectYear) {
        this.subjectYear = subjectYear;
    }

    public int getSubjectLectures() {
        return subjectLectures;
    }

    public void setSubjectLectures(int subjectLectures) {
        this.subjectLectures = subjectLectures;
    }

    public int getSubjectPractices() {
        return subjectPractices;
    }

    public void setSubjectPractices(int subjectPractices) {
        this.subjectPractices = subjectPractices;
    }

    public void exitSummary(@NonNull Activity activity, Student student, Subject subject) {
        activity.finish();
        Intent intent = new Intent(activity, RecyclerViewStudents.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("student", convertObject(student));
        intent.putExtra("subject", convertObject(subject));
        activity.startActivity(intent);
    }

    public void exitSummary2(@NonNull Activity activity, Student student, Subject subject, List<StudentRecyclerList> students) {
        activity.finish();
        Intent intent = new Intent(activity, RecyclerViewStudents.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("student", convertObject(student));
        intent.putExtra("subject", convertObject(subject));
        intent.putExtra("oldStudentsList", (Serializable) students);
        activity.startActivity(intent);
    }

    public String convertObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson((object));
    }
}
