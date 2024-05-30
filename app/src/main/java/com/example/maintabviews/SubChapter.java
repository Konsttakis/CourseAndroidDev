package com.example.maintabviews;

public class SubChapter {

    private final String courseName;
    private final String chapterName;
    private final String name;
    private boolean isCompleted;
    private String description;
    private boolean isExpanded;
    public SubChapter(String courseName, String chapterName, String name, boolean isCompleted, String description) {
        this.courseName = courseName;
        this.chapterName = chapterName;
        this.name = name;
        this.isCompleted = isCompleted;
        this.description = description;
        this.isExpanded = false;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExpanded() { return isExpanded; }
    public void setExpanded(boolean expanded) { isExpanded = expanded; }
}
