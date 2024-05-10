package com.example.maintabviews;

public class Chapter {
    private String name;
    private boolean isCompleted = false;

    public Chapter(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
