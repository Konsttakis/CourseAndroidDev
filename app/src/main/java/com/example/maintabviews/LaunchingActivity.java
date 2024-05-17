package com.example.maintabviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class LaunchingActivity extends AppCompatActivity {
    private static Field field;

    public static Field getField() {
        return field;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_launching);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button field1button = findViewById(R.id.humanStudiesButton);
        Button field2button = findViewById(R.id.scienceStudiesButton);
        Button field3button = findViewById(R.id.medicalStudiesButton);
        Button field4button = findViewById(R.id.financialCompSciStudiesButton);

        field1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Subject> subjects = new ArrayList<>();
                subjects.add(new Subject("Αρχαία Ελληνικά", new ArrayList<>()));
                subjects.add(new Subject("Ιστορία", new ArrayList<>()));
                subjects.add(new Subject("Λατινικά", new ArrayList<>()));
                subjects.add(new Subject("Νεοελληνική Γλώσσα και Λογοτεχνία", new ArrayList<>()));
                field = new Field("Ανθρωπιστικών, Νομικών και Κοινωνικών Επιστημών", new ArrayList<>(subjects));
                openMainActivity();
            }
        });

        field2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Subject> subjects = new ArrayList<>();
                subjects.add(new Subject("Φυσική", new ArrayList<>()));
                subjects.add(new Subject("Μαθηματικά", new ArrayList<>()));
                subjects.add(new Subject("Χημεία", new ArrayList<>()));
                subjects.add(new Subject("Νεοελληνική Γλώσσα και Λογοτεχνία", new ArrayList<>()));
                field = new Field("Θετικών και Τεχνολογικών Επιστημών", new ArrayList<>(subjects));
                openMainActivity();
            }
        });

        field3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Subject> subjects = new ArrayList<>();
                subjects.add(new Subject("Φυσική", new ArrayList<>()));
                subjects.add(new Subject("Βιολογία", new ArrayList<>()));
                subjects.add(new Subject("Χημεία", new ArrayList<>()));
                subjects.add(new Subject("Νεοελληνική Γλώσσα και Λογοτεχνία", new ArrayList<>()));
                field = new Field("Επιστημών Υγείας και Ζωής", new ArrayList<>(subjects));
                openMainActivity();
            }
        });

        field4button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Subject> subjects = new ArrayList<>();
                subjects.add(new Subject("Μαθηματικά", new ArrayList<>()));
                subjects.add(new Subject("ΑΕΠΠ", new ArrayList<>()));
                subjects.add(new Subject("ΑΟΘ", new ArrayList<>()));
                subjects.add(new Subject("Νεοελληνική Γλώσσα και Λογοτεχνία", new ArrayList<>()));
                field = new Field("Επιστημών Οικονομίας και Πληροφορικής", new ArrayList<>(subjects));
                openMainActivity();
            }
        });
    }

    public void openMainActivity() {
        //Create the Intent to start the main Activity
        Intent i = new Intent(this, MainActivity.class);


        //Ask Android to start the new Activity
        startActivity(i);

    }

    }
