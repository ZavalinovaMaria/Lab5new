package org.example.subjects;

public enum Semester {
    FIRST("FIRST"),
    FIFTH("FIFTH"),
    SIXTH("SIXTH"),
    SEVENTH("SEVENTH");

    public String semester;
    Semester(String semester) {
        this.semester = semester;
    }


    @Override
    public String toString(){
        return semester;}
}
