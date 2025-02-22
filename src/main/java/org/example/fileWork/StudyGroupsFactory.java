package org.example.fileWork;

import org.example.exceptions.NullValueException;
import org.example.subjects.*;

import java.time.*;

public class StudyGroupsFactory {
    public static StudyGroup createGroup(String[] builder) {
        int countOfNullFields = 0;
        StudyGroup group = new StudyGroup();
        try {
            int id = builder[0] != null && Integer.parseInt(builder[0]) > 0 ? Integer.parseInt(builder[0]) : 0;
            System.out.println("id"+countOfNullFields+""+id );

            String name = builder[1] != null ?  builder[1] : null;
            if (name == null) countOfNullFields++;
            System.out.println("name"+name);
            Double x = builder[2] != null ?
                    (Double.parseDouble(builder[2]) > -951 ?
                            Double.parseDouble(builder[2]) : null) :
                    null;
            if (x == null) countOfNullFields++;
            System.out.println("coordX"+countOfNullFields);
            int y = builder[3] != null  ? Integer.parseInt(builder[3]) : 0;
            if (y == 0) countOfNullFields++;
            System.out.println("coorY"+countOfNullFields);
            System.out.println("coord"+countOfNullFields);

            String creationDateString = builder[4];
            ZonedDateTime creationDate = creationDateString != null ? ZonedDateTime.parse(creationDateString) : null;
            if (creationDateString == null) countOfNullFields++;

            Integer studentsCount = builder[5] != null && Integer.parseInt(builder[5]) > 0 ? Integer.parseInt(builder[5]) : null;
            if (studentsCount == null) countOfNullFields++;

            int transferredStudents = (builder[6] != null) ? Integer.parseInt(builder[6]) : 0;
            if (transferredStudents == 0) countOfNullFields++;
            System.out.println("transfer"+countOfNullFields);
            String typeString = builder[7] != null ? (String) builder[7] : null;
            FormOfEducation formOfEducation = typeString != null ? FormOfEducation.valueOf(typeString.toUpperCase()) : null;
            if (typeString == null) countOfNullFields++;


            String typeSemestrString = builder[8] != null ? (String) builder[8] : null;
            Semester semestrEnum = typeSemestrString != null ? Semester.valueOf(typeSemestrString.toUpperCase()) : null;
            if (typeSemestrString == null) countOfNullFields++;


            String namePerson = builder[9] != null ? builder[9] : null;
            if (namePerson == null) countOfNullFields++;
            System.out.println("personr"+countOfNullFields);
            String birthdayString = builder[10];
            LocalDateTime birthday = birthdayString != null ? LocalDateTime.parse(birthdayString) : null;
            if (birthdayString == null) countOfNullFields++;

            long height = builder[11] != null && Long.parseLong(builder[11]) > 0 ? Long.parseLong(builder[11]) : 0L;
            if (height == 0L) countOfNullFields++;


            double xl = builder[12] != null ? Double.parseDouble(builder[12]): 0;
            long yl = builder[13] != null ? Integer.parseInt(builder[13]):0 ;
            float zl = builder[14] != null ? Float.parseFloat(builder[14]):0;





/*
            Integer id = builder[8] != null && Integer.parseInt(builder[8]) > 0 ? Integer.parseInt(builder[8]) : null;
            if (id == null) countOfNullFields++;
            String name = builder[9] != null ? builder[9] : null;
            if (name == null) countOfNullFields++;



            Double discount = builder[12] != null ?
                    (Double.parseDouble(builder[12]) > 0 && Double.parseDouble(builder[12]) <= 100 ?
                            Double.parseDouble(builder[12]) : null) :
                    null;
            if (discount == null) countOfNullFields++;


            String refundableObj = builder[13].trim().toLowerCase();
            Boolean refundable = null;
            if (refundableObj.equals("true")) {
                refundable = true;
            } else if (refundableObj.equals("false")) {
                refundable = false;
            } else {
                refundable = null;
                countOfNullFields++;
            }
            String typeString = builder[14] != null ? (String) builder[14] : null;
            TicketType type = typeString != null ? TicketType.valueOf(typeString.toUpperCase()) : null;
            if (typeString == null) countOfNullFields++;

*/
            if (countOfNullFields != 0)
                throw new NullValueException(String.format("Есть пустое поле - продукт не будет собран  "), null);
            else {
                group.setCharacteristic(id, name, new Coordinates(x, y), creationDate,
                        studentsCount, transferredStudents, formOfEducation, semestrEnum, new Person(namePerson, birthday, height, new Location(xl,yl,zl)));
                System.out.println("dывод айди после создания :"+group.getId());}


        } catch (NumberFormatException e1) {
            System.out.println("Есть поле с некорректным форматом числа - продукт не будет собран ");

        } catch (NullValueException e) {
            System.out.println(e.getMessage());
            // continue; тк до этого шли по массиву
        }
        return group;

    }

}
