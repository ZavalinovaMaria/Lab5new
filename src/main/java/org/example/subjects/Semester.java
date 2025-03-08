package org.example.subjects;

/**
 * Enum representing the semestr`s type.
 */
public enum Semester {
    FIRST("FIRST"),
    FIFTH("FIFTH"),
    SIXTH("SIXTH"),
    SEVENTH("SEVENTH");

    public final String semester;
    Semester(String semester) {
        this.semester = semester;
    }


    @Override
    public String toString(){
        return semester;}
}
