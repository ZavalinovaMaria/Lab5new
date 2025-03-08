package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.FormOfEducation;
import org.example.subjects.Semester;

public class EnumBuilder {
    public static FormOfEducation  buildForm(String name) throws InvalidValueException {
        String typeString = ParserService.parseAndValidate(name,"name",String.class);
        FormOfEducation form = typeString != null ? FormOfEducation.valueOf(typeString.toUpperCase()) : null;
        return form;}

    public static Semester  buildSemestr(String name){
    String typeSemestrString = name != null && !name.trim().isEmpty()? (String) name : null;
    Semester semestrEnum = typeSemestrString != null && !name.trim().isEmpty() ? Semester.valueOf(typeSemestrString.toUpperCase()) : null;
    return semestrEnum;}

}
