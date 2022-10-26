package com.example.pma_1;

import android.content.Intent;

public class Student {
    private String Name;
    private String Subject;

    public Student(String name, String subject) {
        Name = name;
        Subject = subject;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void exitSummary(SummaryActivity activity) {
        activity.finish();
        Intent intent = new Intent(activity, PersonalInfoActivity.class);
        activity.startActivity(intent);
    }
}
