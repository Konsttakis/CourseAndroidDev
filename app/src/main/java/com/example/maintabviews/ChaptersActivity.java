package com.example.maintabviews;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    SubjectsSingleton subjectsSingleton = SubjectsSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        // Receive subject name passed via Intent
        String subjectName = getIntent().getStringExtra("subjectName");

        List<Chapter> chapters = new ArrayList<>();
        for (Subject subject : subjectsSingleton.getMandatorySubjects()) {
            if (subject.getName().equals(subjectName)) {
                chapters = dbHandler.getAllChaptersIn(subjectName);
            }
        }
        for (Subject subject : subjectsSingleton.getOptionalSubjects()) {
            if (subject.getName().equals(subjectName)) {
                chapters = dbHandler.getAllChaptersIn(subjectName);
            }
        }

        // Set up RecyclerView
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(subjectName);
        }

        ChapterAdapter adapter = new ChapterAdapter(this, chapters);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // When the Up button is pressed
            finish(); // Finish this activity and go back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}