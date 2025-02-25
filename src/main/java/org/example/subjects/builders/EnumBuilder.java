package org.example.subjects.builders;

import org.example.subjects.FormOfEducation;
import org.example.subjects.Semester;

public class EnumBuilder {
    public static FormOfEducation  buildForm(String name){
        String typeString = name != null ? (String) name : null;
        FormOfEducation formOfEducation = typeString != null ? FormOfEducation.valueOf(typeString.toUpperCase()) : null;
    return formOfEducation;}

    public static Semester  buildSemestr(String name){
    String typeSemestrString = name != null && !name.trim().isEmpty()? (String) name : null;
    Semester semestrEnum = typeSemestrString != null && !name.trim().isEmpty() ? Semester.valueOf(typeSemestrString.toUpperCase()) : null;
    return semestrEnum;}

}
