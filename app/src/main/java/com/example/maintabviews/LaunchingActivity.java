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
                String title = "Ανθρωπιστικών, Νομικών και Κοινωνικών Επιστημών";

                ArrayList<String> mandatoryNames = new ArrayList<>();
                mandatoryNames.add("Αρχαία Ελληνικά");
                mandatoryNames.add("Ιστορία");
                mandatoryNames.add("Λατινικά");
                mandatoryNames.add("Νεοελληνική Γλώσσα και Λογοτεχνία");

                ArrayList<String> optionalNames = new ArrayList<>();
                optionalNames.add("Αγγλικά");
                optionalNames.add("Γαλλικά");
                optionalNames.add("Γερμανικά");

                android.content.Intent intent = new Intent(LaunchingActivity.this, MainActivity.class);
                intent.putExtra("title", title);
                intent.putStringArrayListExtra("mandatoryNames", mandatoryNames);
                intent.putStringArrayListExtra("optionalNames", optionalNames);
                startActivity(intent);
            }
        });

        field2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Θετικών και Τεχνολογικών Σπουδών";

                ArrayList<String> mandatoryNames = new ArrayList<>();
                mandatoryNames.add("Φυσική");
                mandatoryNames.add("Μαθηματικά");
                mandatoryNames.add("Χημεία");
                mandatoryNames.add("Νεοελληνική Γλώσσα και Λογοτεχνία");

                ArrayList<String> optionalNames = new ArrayList<>();
                optionalNames.add("Αγγλικά");
                optionalNames.add("Γαλλικά");
                optionalNames.add("Γερμανικά");

                android.content.Intent intent = new Intent(LaunchingActivity.this, MainActivity.class);
                intent.putExtra("title", title);
                intent.putStringArrayListExtra("mandatoryNames", mandatoryNames);
                intent.putStringArrayListExtra("optionalNames", optionalNames);
                startActivity(intent);
            }
        });

        field3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Επιστημών Υγείας και Ζωής";

                ArrayList<String> mandatoryNames = new ArrayList<>();
                mandatoryNames.add("Φυσική");
                mandatoryNames.add("Βιολογία");
                mandatoryNames.add("Χημεία");
                mandatoryNames.add("Νεοελληνική Γλώσσα και Λογοτεχνία");

                ArrayList<String> optionalNames = new ArrayList<>();
                optionalNames.add("Αγγλικά");
                optionalNames.add("Γαλλικά");
                optionalNames.add("Γερμανικά");

                android.content.Intent intent = new Intent(LaunchingActivity.this, MainActivity.class);
                intent.putExtra("title", title);
                intent.putStringArrayListExtra("mandatoryNames", mandatoryNames);
                intent.putStringArrayListExtra("optionalNames", optionalNames);
                startActivity(intent);
            }
        });

        field4button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Επιστημών Οικονομίας και Πληροφορικής";

                ArrayList<String> mandatoryNames = new ArrayList<>();
                mandatoryNames.add("Μαθηματικά");
                mandatoryNames.add("ΑΕΠΠ");
                mandatoryNames.add("ΑΟΘ");
                mandatoryNames.add("Νεοελληνική Γλώσσα και Λογοτεχνία");

                ArrayList<String> optionalNames = new ArrayList<>();
                optionalNames.add("Αγγλικά");
                optionalNames.add("Γαλλικά");
                optionalNames.add("Γερμανικά");

                android.content.Intent intent = new Intent(LaunchingActivity.this, MainActivity.class);
                intent.putExtra("title", title);
                intent.putStringArrayListExtra("mandatoryNames", mandatoryNames);
                intent.putStringArrayListExtra("optionalNames", optionalNames);
                startActivity(intent);
            }
        });
    }


}
