package com.example.maintabviews;

import static com.example.maintabviews.DatabaseHandler.COLUMN_CHAPTER_NAME;
import static com.example.maintabviews.DatabaseHandler.TABLE_COMPLETED_CHAPTERS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private String title;

    private List<SubChapter> subChapters;
    private boolean isExpanded;

    public Chapter(String title, List<SubChapter> subChapters) {
        this.title = title;
        this.subChapters = subChapters;
        this.isExpanded = false;
    }

    public String getTitle() { return title; }

    public List<SubChapter> getSubChapters() {
        return subChapters;
    }

    public boolean isExpanded() { return isExpanded; }
    public void setExpanded(boolean expanded) { isExpanded = expanded; }

}