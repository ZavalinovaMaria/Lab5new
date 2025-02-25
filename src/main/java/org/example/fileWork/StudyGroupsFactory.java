package org.example.fileWork;

import org.example.exceptions.NullValueException;
import org.example.subjects.*;
import org.example.subjects.builders.EnumBuilder;
import org.example.subjects.builders.NumberBuilder;
import org.example.subjects.builders.NameBuilder;

import java.time.*;
import java.time.format.DateTimeParseException;

public class StudyGroupsFactory {
    public static StudyGroup createGroup(String[] builder) {
        int countOfNullFields = 0;
        StudyGroup group = new StudyGroup();
        try {
            int id = builder[0] != null && Integer.parseInt(builder[0]) > 0 ? Integer.parseInt(builder[0]) : -1;
            String name = NameBuilder.build(builder[1]);
            if (name.equals("Null")) countOfNullFields++;


            Double x = NumberBuilder.buildCoordX(builder[2]);
            if (x == null) countOfNullFields++;

            int y = NumberBuilder.buildCoordY(builder[3]);
            if (y == -1) countOfNullFields++;


            String creationDateString = builder[4];
            ZonedDateTime creationDate = creationDateString != null ? ZonedDateTime.parse(creationDateString) : null;
            if (creationDateString == null) countOfNullFields++;

            Integer studentsCount = NumberBuilder.buildstudentsCount(builder[5]);
            if (studentsCount == null) countOfNullFields++;

            int transferredStudents = NumberBuilder.buildTransferredStudents(builder[6]);
            if (transferredStudents == -1) countOfNullFields++;

            FormOfEducation formOfEducation = EnumBuilder.buildForm(builder[7]);
            if (formOfEducation == null) countOfNullFields++;
            Semester semestrEnum = EnumBuilder.buildSemestr(builder[8]);
            String namePerson = NameBuilder.build(builder[9]);
            if (namePerson.equals("Null")) countOfNullFields++;

            String birthdayString = builder[10];
            LocalDateTime birthday = birthdayString != null ? LocalDateTime.parse(birthdayString) : null;
            if (birthdayString == null) countOfNullFields++;

            long height = NumberBuilder.buildHeight(builder[11]);
            if (height == -1) countOfNullFields++;

            double xl = NumberBuilder.buildLocationX(builder[12]);
            long yl = NumberBuilder.buildLocationY(builder[13]);
            float zl = NumberBuilder.buildLocationZ(builder[14]);

            if (countOfNullFields != 0)
                throw new NullValueException(String.format("Есть пустое поле - продукт не будет собран  "), null);
            else {
                group.setCharacteristic(id, name, new Coordinates(x, y), creationDate,
                        studentsCount, transferredStudents, formOfEducation, semestrEnum, new Person(namePerson, birthday, height, new Location(xl,yl,zl)));
                }
        } catch (NumberFormatException e1) {
            System.out.println("Есть поле с некорректным форматом числа - продукт не будет собран ");
        }catch(DateTimeParseException e){
            System.out.println("Некорректный формат даты- продукт не будет собран");
        } catch(IllegalArgumentException e) {
            System.out.println(" Некорректное значение для enum поля - продукт не будет собран" );
        } catch (NullValueException e) {
            System.out.println(e.getMessage());

        }
        return group;

    }

}
