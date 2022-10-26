package com.example.pma_1;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class PersonalInfo {
    private String name;

    public PersonalInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void openStudentActivity(PersonalInfoActivity activity, String name ) {
        activity.finish();
        Intent intent = new Intent(activity, StudentInfoActivity.class);
        intent.putExtra("studentName", name);
        activity.startActivity(intent);
    }
}
