package com.example.maintabviews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        // Receive subject name passed via Intent
        String subjectName = getIntent().getStringExtra("subject_name");

        // Retrieve chapters for the subjectName from your data source
        // Assuming you have a method to get chapters by subject name
        List<Chapter> chapterList = getChaptersForSubject(subjectName);

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChapterAdapter adapter = new ChapterAdapter(chapterList);
        recyclerView.setAdapter(adapter);
    }

    // Method to retrieve chapters for the subjectName from your data source
    private List<Chapter> getChaptersForSubject(String subjectName) {

        Chapter chapter1 = new Chapter("Μαθηματικά" , "Chapter 1", false);
        Chapter chapter2 = new Chapter("Μαθηματικά" , "Chapter 2", false);
        Chapter chapter3 = new Chapter("Μαθηματικά", "Chapter 3", true);
        return new ArrayList<>(); // Placeholder, replace with actual implementation
    }
}