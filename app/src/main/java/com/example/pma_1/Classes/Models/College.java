package com.example.pma_1.Classes.Models;

import java.util.ArrayList;

public class College {
    private ArrayList<Courses> courses = new ArrayList<Courses>();
    private Metadata metadata;

    public College() {

    }

    public College(ArrayList<Courses> coursesList, Metadata metadata) {
        this.courses = coursesList;
        this.metadata = metadata;
    }

    public ArrayList<Courses> getCoursesList() {
        return courses;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
