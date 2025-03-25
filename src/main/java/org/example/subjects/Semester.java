package org.example.subjects;

/**
 * Класс, перечисляющий типы семестра
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
    public String toString() {
        return semester;
    }
}
