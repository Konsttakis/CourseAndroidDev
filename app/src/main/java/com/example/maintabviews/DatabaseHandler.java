package com.example.maintabviews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "chapterCompleted";
    public static final String TABLE_COMPLETED_CHAPTERS = "CompletedChapters";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_CHAPTER_NAME = "chapter_name";
    public static final String COLUMN_IS_COMPLETED = "is_completed";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE =
                "CREATE TABLE " + TABLE_COMPLETED_CHAPTERS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_COURSE_NAME + " TEXT NOT NULL, " +
                        COLUMN_CHAPTER_NAME + " TEXT NOT NULL, " +
                        COLUMN_IS_COMPLETED + " INTEGER NOT NULL);";
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLETED_CHAPTERS);
        onCreate(db);
    }

    public void updateCompleted(Chapter chapter, boolean isCompleted) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_COMPLETED, isCompleted ? 1 : 0);
        db.update(TABLE_COMPLETED_CHAPTERS, values, COLUMN_COURSE_NAME + " = ? AND " + COLUMN_CHAPTER_NAME + " = ?", new String[]{chapter.getCourseName(), chapter.getName()});

        db.close();
    }

    public List<Chapter> getAllChaptersIn(String course) {
        List<Chapter> chapters = new ArrayList<Chapter>();
        String selectQuery = "SELECT * FROM " + TABLE_COMPLETED_CHAPTERS + " WHERE " + COLUMN_COURSE_NAME + " = '" + course + "';";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Chapter chapter = new Chapter(cursor.getString(1), cursor.getString(2), cursor.getInt(3) == 1);
                chapters.add(chapter);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return chapters;
    }
}