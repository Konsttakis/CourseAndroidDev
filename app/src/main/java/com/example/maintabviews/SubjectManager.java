package com.example.maintabviews;

import java.util.ArrayList;
import java.util.List;

public class SubjectManager {

    private static SubjectManager instance;
    private List<Subject> field1;
    private List<Subject> field2;
    private List<Subject> field3;

    private List<Subject> field4;

    private SubjectManager() {
        field1 = new ArrayList<>();
        field2 = new ArrayList<>();
        field3 = new ArrayList<>();
        field4 = new ArrayList<>();
        initializeFields();
    }

    public static SubjectManager getInstance() {
        if (instance == null) {
            instance = new SubjectManager();
        }
        return instance;
    }

    private void initializeFields() {
        field1.add(new Subject(R.drawable.ic_launcher_foreground, "Item 1", "Description 1"));
        field1.add(new Subject(R.drawable.ic_launcher_foreground, "Item 2", "Description 2"));
        field1.add(new Subject(R.drawable.ic_launcher_foreground, "Item 3", "Description 3"));
        field1.add(new Subject(R.drawable.ic_launcher_foreground, "Item 4", "Description 4"));

        field2.add(new Subject(R.drawable.ic_launcher_foreground, "Item 1", "Description 1"));
        field2.add(new Subject(R.drawable.ic_launcher_foreground, "Item 2", "Description 2"));
        field2.add(new Subject(R.drawable.ic_launcher_foreground, "Item 3", "Description 3"));

        field3.add(new Subject(R.drawable.ic_launcher_foreground, "Item 1", "Description 1"));
        field3.add(new Subject(R.drawable.ic_launcher_foreground, "Item 2", "Description 2"));
        field3.add(new Subject(R.drawable.ic_launcher_foreground, "Item 3", "Description 3"));
        field3.add(new Subject(R.drawable.ic_launcher_foreground, "Item 4", "Description 4"));

        field4.add(new Subject(R.drawable.ic_launcher_foreground, "Item 1", "Description 1"));
        field4.add(new Subject(R.drawable.ic_launcher_foreground, "Item 2", "Description 2"));
    }

    public List<Subject> getField1() { return field1; }

    public List<Subject> getField2() { return field2; }

    public List<Subject> getField3() { return field3; }

    public List<Subject> getField4() { return field4; }


}
