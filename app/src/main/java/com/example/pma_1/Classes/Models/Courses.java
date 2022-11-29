package com.example.pma_1.Classes.Models;

public class Courses {
    private String code;
    private String name;
    private String url;

    public Courses(String code, String name, String url) {
        this.code = code;
        this.name = name;
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
