package com.example.maintabviews;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "";
    private static final String DATABASE_NAME = "chapters.db";
    private SQLiteDatabase chaptersDB;
    private final Context myContext;
    private SQLiteOpenHelper sqLiteOpenHelper;
    public static final String TABLE_COMPLETED_CHAPTERS = "CompletedChapters";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUBJECT_NAME = "subject_name";
    public static final String COLUMN_CHAPTER_NAME = "chapter_name";
    public static final String COLUMN_SUBCHAPTER_NAME = "subchapter_name";
    public static final String COLUMN_IS_COMPLETED = "is_completed";

    /**
     * Constructor
     * Takes and keeps a reference of
     * the passed context in order
     * to access the application assets and resources. */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DATABASE_NAME)
                .toString();
        System.out.println(DB_PATH);
    }

    // Creates an empty database
    // on the system and rewrites it
    // with your own database.
    public void createDataBase()  throws IOException {

        boolean dbExist = checkDataBase();
        System.out.println(dbExist);
        if (!dbExist) {
            // By calling this method and
            // the empty database will be
            // created into the default system
            // path of your application
            // so we are gonna be able
            // to overwrite that database
            // with our database.
            this.getWritableDatabase();
            try {
                copyDataBase();
                System.out.println("here");
            }
            catch (IOException e) {
                throw new Error(
                        "Error copying database");
            }
        }
    }

    // Check if the database already exist
    // to avoid re-copying the file each
    // time you open the application
    // return true if it exists
    // false if it doesn't.
    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            System.out.println(myPath);
            checkDB
                    = SQLiteDatabase
                    .openDatabase(
                            myPath, null,
                            SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {

            // database doesn't exist yet.
            Log.e("message", "" + e);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    /**
     * Copies your database from your
     * local assets-folder to the just
     * created empty database in the
     * system folder, from where it
     * can be accessed and handled.
     * This is done by transferring bytestream.
     * */
    private void copyDataBase()
            throws IOException
    {
        // Open your local db as the input stream
        InputStream myInput
                = myContext.getAssets()
                .open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream
        OutputStream myOutput
                = Files.newOutputStream(Paths.get(outFileName));

        // transfer bytes from the
        // inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    public void openDataBase()
            throws SQLException
    {
        // Open the database
        String myPath = DB_PATH;
        chaptersDB = SQLiteDatabase
                .openDatabase(
                        myPath, null,
                        SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        // close the database.
        if (chaptersDB != null)
            chaptersDB.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // It is an abstract method
        // but we define our own method here.

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLETED_CHAPTERS);
        onCreate(db);
    }


    public ArrayList<Chapter> getAllChaptersIn(String subject) {
        ArrayList<Chapter> chapters = new ArrayList<>();
        String selectQuery = "SELECT DISTINCT " + COLUMN_CHAPTER_NAME + " FROM " + TABLE_COMPLETED_CHAPTERS + " WHERE " + COLUMN_SUBJECT_NAME + " = '" + subject + "';";
        sqLiteOpenHelper = new DatabaseHandler(myContext);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Chapter chapter = new Chapter(cursor.getString(0), getSubChapters(cursor.getString(0)));
                chapters.add(chapter);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return chapters;
    }
    public ArrayList<SubChapter> getSubChapters(String chapter) {

        ArrayList<SubChapter> subChapters = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COMPLETED_CHAPTERS + " WHERE " + COLUMN_CHAPTER_NAME + " = '" + chapter + "';";

        SQLiteDatabase db
                = sqLiteOpenHelper
                .getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SubChapter subChapter = new SubChapter(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4) == 1, "sos");
                subChapters.add(subChapter);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return subChapters;
    }
}