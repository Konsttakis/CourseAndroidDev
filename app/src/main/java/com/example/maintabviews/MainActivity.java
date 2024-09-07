package com.example.maintabviews;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private DatabaseHandler dbHelper;
    SubjectsSingleton subjectsSingleton = SubjectsSingleton.getInstance();
    private ArrayList<Subject> mandatorySubjects;
    private ArrayList<Subject> optionalSubjects;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("MainActivity", "onCreate: ");

        dbHelper = new DatabaseHandler(this);
        mandatorySubjects = new ArrayList<>();
        optionalSubjects = new ArrayList<>();

        TextView title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title"));

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(this));
        tabLayout = findViewById(R.id.tab_layout);

        ArrayList<String> mandatoryNames = getIntent().getStringArrayListExtra("mandatoryNames");
        ArrayList<String> optionalNames = getIntent().getStringArrayListExtra("optionalNames");
        assert mandatoryNames != null;
        assert optionalNames != null;

        loadSubjects(mandatoryNames, optionalNames);

        subjectsSingleton.setMandatorySubjects(mandatorySubjects);
        subjectsSingleton.setOptionalSubjects(optionalSubjects);

        List<String> tabNames = new ArrayList<>();
        tabNames.add("Υποχρεωτικά");
        tabNames.add("Επιλογής");
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabNames.get(position))
        ).attach();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void loadSubjects(List<String> mandatoryNames, List<String> optionalNames) {
        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (String subjectName : mandatoryNames) {
            ArrayList<Chapter> chapters = dbHelper.getAllChaptersIn(subjectName);
            for (Chapter chapter : chapters) {
                ArrayList<SubChapter> subChapters = dbHelper.getSubChapters(chapter.getTitle());
            }
            Subject subject = new Subject(subjectName, new ArrayList<>(chapters));
            mandatorySubjects.add(subject);
        }

        for (String subjectName : optionalNames) {
            ArrayList<Chapter> chapters = dbHelper.getAllChaptersIn(subjectName);
            Subject subject = new Subject(subjectName, chapters);
            optionalSubjects.add(subject);
        }
    }

}