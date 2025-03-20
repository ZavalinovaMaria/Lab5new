package org.example.subjects.creators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.*;

import java.time.ZonedDateTime;

import static org.example.subjects.parsers.FieldParserUtil.parseField;
import static org.example.subjects.parsers.FieldParserUtil.parseFieldFromConsole;

public class StudyGroupsCreator {
    PersonCreator personCreator;
    FormOfEducationCreator formOfEducationCreator;
    SemesterCreator semesterCreator;

    public StudyGroupsCreator(){
        this.personCreator = new PersonCreator();
        this.formOfEducationCreator = new FormOfEducationCreator();
        this.semesterCreator = new SemesterCreator();
    }
    private String getSafeValue(String[] array, int index) {
        return (index >= 0 && index < array.length) ? array[index] : null;
    }
    public  StudyGroup createGroup(String[] builder,Boolean isScriptWorking) throws InvalidValueException {
        StudyGroup group = new StudyGroup();
        int startIndex = 0;
        if(isScriptWorking){
            startIndex-=1;
            group.setId(-1);
        }
        else{
            group.setId(parseField(builder[startIndex], "id", Integer.class));
        }
           fillGroup(group,builder,startIndex);
        return group;
    }
    public void fillGroup(StudyGroup group,String[] builder,int startIndex) throws InvalidValueException{
        group.setName(parseField(builder[startIndex+1], "name", String.class));
        group.setCoordinates(new Coordinates(parseField(builder[startIndex+2], "x", Double.class),parseField(builder[startIndex+3], "y", Integer.class)));
        group.setCreationDate(parseField(builder[startIndex+4], "creationDate", ZonedDateTime.class));
        group.setStudentsCount(parseField(builder[startIndex+5], "studentsCount", Integer.class));
        group.setTransferredStudents(parseField(builder[startIndex+6], "transferredStudents", Integer.class));
        group.setFormOfEducation(formOfEducationCreator.createForm(builder[startIndex+7]));
        group.setSemesterEnum(semesterCreator.createSemester(getSafeValue(builder,startIndex+8)));
        group.setGroupAdmin(personCreator.createPerson(getSafeValue(builder,startIndex+9), getSafeValue(builder,startIndex+10), getSafeValue(builder,startIndex+11), getSafeValue(builder,startIndex+12), getSafeValue(builder,startIndex+13), getSafeValue(builder,startIndex+14)));
    }
    public  StudyGroup createGroupFromConsole() {
        StudyGroup group = new StudyGroup();
        fillGroupFromConsole(group);
        return group;
    }
    public void fillGroupFromConsole(StudyGroup group) {
        try {
            group.setName(parseFieldFromConsole("Введите имя", "name", String.class));
            group.setCoordinates(new Coordinates(
                    parseFieldFromConsole("Введите x", "x", Double.class),
                    parseFieldFromConsole("Введите y", "y", Integer.class)
            ));
            group.setCreationDate(ZonedDateTime.now());
            group.setStudentsCount(parseFieldFromConsole("Введите количество студентов", "studentsCount", Integer.class));
            group.setTransferredStudents(parseFieldFromConsole("Введите количество переведенных студентов", "transferredStudents", Integer.class));
            group.setFormOfEducation(formOfEducationCreator.createFormOfEducationFromConsole());
            group.setSemesterEnum(semesterCreator.createSemesterFromConsole());
            group.setGroupAdmin(personCreator.createPersonFromConsole());

        } catch (InvalidValueException e) {
            System.out.println(e.getMessage() + ", продукт не будет собран");
        }
    }




}
