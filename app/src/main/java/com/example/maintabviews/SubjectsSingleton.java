package com.example.maintabviews;


import java.util.ArrayList;

public class SubjectsSingleton {

    private static SubjectsSingleton instance;
    private ArrayList<Subject> mandatorySubjects;
    private ArrayList<Subject> optionalSubjects;

    private SubjectsSingleton() {
        mandatorySubjects = new ArrayList<>();
        optionalSubjects = new ArrayList<>();
    }

    public static SubjectsSingleton getInstance() {
        if (instance == null) {
            instance = new SubjectsSingleton();
        }
        return instance;
    }

    public void setMandatorySubjects(ArrayList<Subject> mandatorySubjects) {
        this.mandatorySubjects = mandatorySubjects;
    }

    public void setOptionalSubjects(ArrayList<Subject> optionalSubjects) {
        this.optionalSubjects = optionalSubjects;
    }

    public ArrayList<Subject> getMandatorySubjects() {
        return mandatorySubjects; }

    public ArrayList<Subject> getOptionalSubjects() { return optionalSubjects; }




}
