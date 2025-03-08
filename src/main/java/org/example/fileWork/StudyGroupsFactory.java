package org.example.fileWork;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.*;
import org.example.subjects.validators.*;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class StudyGroupsFactory {
    public static StudyGroup createGroup(String[] builder) {
        StudyGroup group = new StudyGroup();
        try {
            int id = ParserService.parseAndValidate(builder[0], "id", Integer.class).intValue();
            String name = ParserService.parseAndValidate(builder[1], "name", String.class);
            Double x = ParserService.parseAndValidate(builder[2], "coordinatesX", Double.class);
            int y = ParserService.parseAndValidate(builder[3], "coordinatesY", Integer.class).intValue();
            ZonedDateTime creationDate = ParserService.parseAndValidate(builder[4], "creationDate", ZonedDateTime.class);
            Integer studentsCount = ParserService.parseAndValidate(builder[5], "studentsCount", Integer.class);
            int transferredStudents = ParserService.parseAndValidate(builder[6], "transferredStudents", Integer.class).intValue();
            FormOfEducation formOfEducation = EnumBuilder.buildForm(builder[7]);
            Semester semester = EnumBuilder.buildSemestr(builder[8]);
            Person groupAdmin = createPerson(builder[9],builder[10],builder[11],builder[12],builder[13],builder[14]);

            group.setCharacteristic(id, name, new Coordinates(x, y), creationDate,
                        studentsCount, transferredStudents, formOfEducation, semester , groupAdmin);

        }catch(InvalidValueException e) {
            System.out.println(e.getMessage()+", продукт не будет собран");
        }catch (NullPointerException e1) {
            System.out.println("Есть поле с некорректным форматом числа - продукт не будет собран ");
        }catch(DateTimeParseException e){
            System.out.println("Некорректный формат даты - продукт не будет собран");
        } catch(IllegalArgumentException e) {
            System.out.println(" Некорректное значение для enum поля - продукт не будет собран" );
        }

        return group;

    }
    public static Location createLocation(String xArg, String yArg, String zArg) throws InvalidValueException {
        if(areStringsNull(xArg,yArg,zArg)){
            return null;
        }
        else{
            double x = ParserService.parseAndValidate(xArg, "location", Double.class).doubleValue();
            long y = ParserService.parseAndValidate(yArg, "location", Long.class).longValue();
            float z = ParserService.parseAndValidate(zArg, "location", Float.class).floatValue();
            return new Location(x,y,z);
        }
    }
    public static Person createPerson(String name, String birth, String heigh, String xArg, String yArg, String zArg) throws InvalidValueException {
        if(areStringsNull(name,birth,heigh,xArg,yArg,zArg)){
            return null;
        }
        else{
            String personName = ParserService.parseAndValidate(name, "name", String.class);
            LocalDateTime birthday = ParserService.parseAndValidate(birth, "birthday", LocalDateTime.class);
            long height = ParserService.parseAndValidate(heigh, "height", Long.class).longValue();
            Location location = createLocation(xArg,yArg,zArg);
            return new Person(personName,birthday,height,location);
        }
    }


    public static boolean areStringsNull(String... strs) {
        return Arrays.stream(strs)
                .allMatch(str -> str == null || str.isEmpty());
    }


}
