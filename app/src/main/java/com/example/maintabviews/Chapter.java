package com.example.maintabviews;

public class Chapter {

    private String courseName;
    private String name;
    private boolean isCompleted = false;

    public Chapter(String courseName, String name, boolean isCompleted) {
        this.courseName = courseName;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getCourseName() {
        return courseName;
    }
    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
