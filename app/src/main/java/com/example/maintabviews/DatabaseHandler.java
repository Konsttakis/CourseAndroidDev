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
        DB_PATH = myContext.getDatabasePath(DATABASE_NAME).toString();
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDataBase() throws IOException {
        if (!checkDataBase()) {
            this.getWritableDatabase();
            try {
                copyDataBase();
                Log.d("DatabaseHandler", "Database copied successfully");
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        } else {
            Log.d("DatabaseHandler", "Database already exists");
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Log.e("DatabaseHandler", "Database does not exist yet.");
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        try (InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
             OutputStream myOutput = Files.newOutputStream(Paths.get(DB_PATH))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
        }
    }

    public void openDataBase() {
        try {
            chaptersDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade as needed
        if (oldVersion < newVersion) {
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
