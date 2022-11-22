package com.example.pma_1.Classes.StudentsRV;

import android.graphics.Bitmap;

import java.io.Serializable;

public class StudentRecyclerList implements Serializable {

    private String name;
    private String surname;
    private String subject;
    private Bitmap image;

    public StudentRecyclerList(String name, String surname, String subject, Bitmap image) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.image = image;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
