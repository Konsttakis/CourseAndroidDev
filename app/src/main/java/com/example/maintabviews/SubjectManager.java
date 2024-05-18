package com.example.maintabviews;

import java.util.ArrayList;
import java.util.List;

public class SubjectManager{

    private static SubjectManager instance;
    private ArrayList<Subject> mandatorySubjects;
    private ArrayList<Subject> optionalSubjects;

    private SubjectManager() {
        mandatorySubjects = new ArrayList<>();
        optionalSubjects = new ArrayList<>();
    }

    public static SubjectManager getInstance() {
        if (instance == null) {
            instance = new SubjectManager();
        }
        return instance;
    }

    public void setMandatorySubjects(ArrayList<Subject> mandatorySubjects) {
        this.mandatorySubjects = mandatorySubjects;
    }

    public void setOptionalSubjects(ArrayList<Subject> optionalSubjects) {
        this.optionalSubjects = optionalSubjects;
    }

    public ArrayList<Subject> getMandatorySubjects() { return mandatorySubjects; }

    public ArrayList<Subject> getOptionalSubjects() { return optionalSubjects; }




}
