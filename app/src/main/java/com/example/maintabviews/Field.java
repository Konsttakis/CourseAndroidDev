package com.example.maintabviews;

import java.io.Serializable;
import java.util.ArrayList;

public class Field implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fieldName;
    private ArrayList<Subject> subjects;

    public Field(String fieldName, ArrayList<Subject> subjects) {
        this.fieldName = fieldName;
        this.subjects = subjects;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
}
