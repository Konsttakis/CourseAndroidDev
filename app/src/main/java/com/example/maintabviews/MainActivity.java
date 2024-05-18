package com.example.maintabviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    SubjectManager subjectManager = SubjectManager.getInstance();
    private ArrayList<String> fieldList;
    private ArrayList<Subject> mandatorySubjects;
    private ArrayList<Subject> optionalSubjects;

    private Field field;

    private DatabaseHandler dbHelper;

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

        dbHelper = new DatabaseHandler(this);

        //Obtain references to objects
        TextView title = findViewById(R.id.title);

//        //Retrieve data passed in the Intent
//        field = LaunchingActivity.getField();
        title.setText(getIntent().getStringExtra("title"));

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(this));
        tabLayout = findViewById(R.id.tab_layout);

//        fieldList = new ArrayList<>();
//        for (Subject subject : field.getSubjects())
//            fieldList.add(subject.getName());

        ArrayList<String> mandatoryNames = getIntent().getStringArrayListExtra("mandatoryNames");
        ArrayList<String> optionalNames = getIntent().getStringArrayListExtra("optionalNames");
        assert mandatoryNames != null;
        assert optionalNames != null;
        loadSubjects(mandatoryNames, optionalNames);


        List<String> tabNames = new ArrayList<>();
        tabNames.add("Υποχρεωτικά");
        tabNames.add("Επιλογής");

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabNames.get(position))
        ).attach();
    }

    public void loadSubjects(List<String> mandatoryNames, List<String> optionalNames) {
        mandatorySubjects = new ArrayList<>();
        optionalSubjects = new ArrayList<>();

        for (String subjectName : mandatoryNames) {
            ArrayList<Chapter> chapters = dbHelper.getAllChaptersIn(subjectName);
            Subject subject = new Subject(subjectName, new ArrayList<>(chapters));
            mandatorySubjects.add(subject);
            subjectManager.setMandatorySubjects(mandatorySubjects);
        }

        for (String subjectName : optionalNames) {
            ArrayList<Chapter> chapters = dbHelper.getAllChaptersIn(subjectName);
            Subject subject = new Subject(subjectName, chapters);
            optionalSubjects.add(subject);
            subjectManager.setOptionalSubjects(optionalSubjects);
        }
    }

}