package org.example.subjects;

/**
 * Класс, перечисляющий типы формы обучения
 */
public enum FormOfEducation {
    DISTANCE_EDUCATION("DISTANCE_EDUCATION"),
    FULL_TIME_EDUCATION("FULL_TIME_EDUCATION"),
    EVENING_CLASSES("EVENING_CLASSES");
    public final String form;

    FormOfEducation(String form) {
        this.form = form;
    }

    @Override
    public String toString() {
        return form;
    }
}
