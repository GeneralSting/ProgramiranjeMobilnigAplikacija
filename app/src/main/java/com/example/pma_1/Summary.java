package com.example.pma_1;

import android.content.Intent;

import androidx.annotation.NonNull;

public class Summary {
    private String studentName;
    private String studentSurname;
    private String studentBirthDate;
    private String professorName;
    private String professorSurname;
    private int subjectYear;
    private int subjectLectures;
    private int subjectPractices;

    public Summary(String studentName, String studentSurname, String studentBirthDate, String professorName, String professorSurname, int subjectYear, int subjectLectures, int subjectPractices) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentBirthDate = studentBirthDate;
        this.professorName = professorName;
        this.professorSurname = professorSurname;
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

    public String getProfessorSurname() {
        return professorSurname;
    }

    public void setProfessorSurname(String professorSurname) {
        this.professorSurname = professorSurname;
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

    public void exitSummary(@NonNull SummaryActivity activity) {
        activity.finish();
        Intent intent = new Intent(activity, PersonalInfoActivity.class);
        activity.startActivity(intent);
    }
}
