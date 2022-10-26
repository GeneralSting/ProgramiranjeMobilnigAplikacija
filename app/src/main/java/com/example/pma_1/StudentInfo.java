package com.example.pma_1;

import android.content.Intent;

public class StudentInfo {
    private String subject;

    public StudentInfo(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void openSummaryActivity(StudentInfoActivity activity, String subject, String name) {
        activity.finish();
        Intent intent = new Intent(activity, SummaryActivity.class);
        intent.putExtra("studentSubject", subject);
        intent.putExtra("studentName", name);
        activity.startActivity(intent);
    }
}
