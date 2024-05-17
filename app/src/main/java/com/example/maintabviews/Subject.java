package com.example.maintabviews;

import java.util.ArrayList;

public class Subject {

    private String name;
    private ArrayList<Chapter> chapters;

    public Subject(String name, ArrayList<Chapter> chapters) {
        this.name = name;
        this.chapters = chapters;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }
}