package com.example.maintabviews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    SubjectManager subjectManager = SubjectManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        // Receive subject name passed via Intent
        String subjectName = getIntent().getStringExtra("subject_name");

        ArrayList<Chapter> chapterList = new ArrayList<>();
        for (Subject subject : subjectManager.getMandatorySubjects()) {
            if (subject.getName().equals(subjectName)) {
                chapterList.addAll(subject.getChapters());
            }
        }

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChapterAdapter adapter = new ChapterAdapter(chapterList);
        recyclerView.setAdapter(adapter);
    }

    // Method to retrieve chapters for the subjectName from data source
    private List<Chapter> getChaptersForSubject(String subjectName) {

        Chapter chapter1 = new Chapter("Μαθηματικά" , "Chapter 1", false);
        Chapter chapter2 = new Chapter("Μαθηματικά" , "Chapter 2", false);
        Chapter chapter3 = new Chapter("Μαθηματικά", "Chapter 3", true);

        List<Chapter> chapters = new ArrayList<>();
        chapters.add(chapter1);
        chapters.add(chapter2);
        chapters.add(chapter3);

        return chapters; // Placeholder, replace with actual implementation
    }


    
}