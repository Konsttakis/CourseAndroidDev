package com.example.maintabviews;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH;
    private static final String DATABASE_NAME = "chapters.db";
    private SQLiteDatabase chaptersDB;
    private final Context myContext;
    public static final String TABLE_COMPLETED_CHAPTERS = "CompletedChapters";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUBJECT_NAME = "subject_name";
    public static final String COLUMN_CHAPTER_NAME = "chapter_name";
    public static final String COLUMN_SUBCHAPTER_NAME = "subchapter_name";
    public static final String COLUMN_IS_COMPLETED = "is_completed";
    public static final String COLUMN_DESCRIPTION = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;

    }


    @Override
    public synchronized void close() {
        if (chaptersDB != null) {
            chaptersDB.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No need to implement as we are using a pre-populated database
        String CREATE_TABLE = "CREATE TABLE " +
        TABLE_COMPLETED_CHAPTERS + "("+ COLUMN_ID + " INTEGER PRIMARY KEY," +
        COLUMN_SUBJECT_NAME + " TEXT," + COLUMN_CHAPTER_NAME + " TEXT," + COLUMN_SUBCHAPTER_NAME +
                " TEXT," + COLUMN_IS_COMPLETED + " INTEGER," + COLUMN_DESCRIPTION + " TEXT)";
                db.execSQL (CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade as needed
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_COMPLETED_CHAPTERS );
        onCreate(db);

    }

    public ArrayList<Chapter> getAllChaptersIn(String subject) {
        ArrayList<Chapter> chapters = new ArrayList<>();
        String selectQuery = "SELECT DISTINCT " + COLUMN_CHAPTER_NAME + " FROM " + TABLE_COMPLETED_CHAPTERS + " WHERE " + COLUMN_SUBJECT_NAME + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(selectQuery, new String[]{subject})) {
            if (cursor.moveToFirst()) {
                do {
                    Chapter chapter = new Chapter(cursor.getString(0), getSubChapters(cursor.getString(0)));
                    chapters.add(chapter);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHandler", "Error while getting chapters: " + e.getMessage());
        }
        return chapters;
    }

    public ArrayList<SubChapter> getSubChapters(String chapter) {
        ArrayList<SubChapter> subChapters = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COMPLETED_CHAPTERS + " WHERE " + COLUMN_CHAPTER_NAME + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(selectQuery, new String[]{chapter})) {
            if (cursor.moveToFirst()) {
                do {
                    SubChapter subChapter = new SubChapter(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4) == 1, cursor.getString(5));
                    subChapters.add(subChapter);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHandler", "Error while getting subchapters: " + e.getMessage());
        }
        return subChapters;
    }
}
