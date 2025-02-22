package org.example.subjects;

public enum FormOfEducation {
    DISTANCE_EDUCATION("DISTANSE_EDUCATION"),
    FULL_TIME_EDUCATION("FULL_TIME_EDUCATION"),
    EVENING_CLASSES("EVENING_CLASSES");
    public String form;
     FormOfEducation(String form){
        this.form = form;
    }

    @Override
    public String toString(){
        return form;}
}
