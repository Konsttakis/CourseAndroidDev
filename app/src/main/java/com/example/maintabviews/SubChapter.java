package com.example.maintabviews;

public class SubChapter {

    private final String courseName;
    private final String chapterName;
    private final String name;
    private boolean isCompleted = false;

    public SubChapter(String courseName, String chapterName, String name, boolean isCompleted) {
        this.courseName = courseName;
        this.chapterName = chapterName;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getChapterName() {
        return chapterName;
    }
    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;

    }
}
