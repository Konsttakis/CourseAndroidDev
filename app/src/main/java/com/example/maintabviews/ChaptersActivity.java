package com.example.maintabviews;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChaptersActivity extends AppCompatActivity {

    SubjectsSingleton subjectsSingleton = SubjectsSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        // Receive subject name passed via Intent
        String subjectName = getIntent().getStringExtra("subjectName");

        ArrayList<Chapter> chapterList = new ArrayList<>();
        for (Subject subject : subjectsSingleton.getMandatorySubjects()) {
            if (subject.getName().equals(subjectName)) {
                chapterList.addAll(subject.getChapters());
            }
        }
        for (Subject subject : subjectsSingleton.getOptionalSubjects()) {
            if (subject.getName().equals(subjectName)) {
                chapterList.addAll(subject.getChapters());
            }
        }

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChapterAdapter adapter = new ChapterAdapter(this, chapterList);
        recyclerView.setAdapter(adapter);
    }

}