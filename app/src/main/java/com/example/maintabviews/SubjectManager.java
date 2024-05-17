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
        field1.add(new Subject("Item 1", new ArrayList<>()));
        field1.add(new Subject("Item 1", new ArrayList<>()));
        field1.add(new Subject("Item 1", new ArrayList<>()));
        field1.add(new Subject("Item 1", new ArrayList<>()));

        field2.add(new Subject("Item 1", new ArrayList<>()));
        field2.add(new Subject("Item 1", new ArrayList<>()));
        field2.add(new Subject("Item 1", new ArrayList<>()));

        field3.add(new Subject("Item 1", new ArrayList<>()));
        field3.add(new Subject("Item 1", new ArrayList<>()));
        field3.add(new Subject("Item 1", new ArrayList<>()));
        field3.add(new Subject("Item 1", new ArrayList<>()));

        field4.add(new Subject("Item 1", new ArrayList<>()));
        field4.add(new Subject("Item 1", new ArrayList<>()));
    }

    public List<Subject> getField1() { return field1; }

    public List<Subject> getField2() { return field2; }

    public List<Subject> getField3() { return field3; }

    public List<Subject> getField4() { return field4; }


}
