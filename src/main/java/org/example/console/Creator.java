package org.example.console;

import org.example.subjects.*;
import org.example.subjects.builders.*;


import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Creator {
    Scanner scanner = new Scanner(System.in);
    String[] output = new String[17];

    public Creator() {}

   public StudyGroup toBuildGroup(String[] commands) {
        StudyGroup group = new StudyGroup();
        group.setId(group.generateNextId());
        group.setName(NameBuilder.build(commands[0]));
        group.setCoordinates(new Coordinates(NumberBuilder.buildCoordX(commands[1]), NumberBuilder.buildCoordY(commands[2])));
        group.setCreationDate(ZonedDateTime.now());
        group.setStudentsCount(NumberBuilder.buildstudentsCount(commands[3]));
        group.setTransferredStudents(NumberBuilder.buildTransferredStudents(commands[4]));
        group.setFormOfEducation(EnumBuilder.buildForm(commands[5]));
        group.setSemesterEnum(EnumBuilder.buildSemestr(commands[6]));
       if(commands.length==13){
           group.setGroupAdmin(new Person( NameBuilder.build(commands[7]),LocalDateTime.parse(commands[8]),
                   NumberBuilder.buildHeight(commands[9]),new Location(NumberBuilder.buildLocationX(commands[10]),
                   NumberBuilder.buildLocationY(commands[11]),NumberBuilder.buildLocationZ(commands[12]))));
       } else{
           group.setGroupAdmin(new Person( NameBuilder.build(commands[7]),
                   LocalDateTime.of(NumberBuilder.buildPersonBirthdayYear(commands[8]),NumberBuilder.buildPersonBirthdayMonth(commands[9]),
                           NumberBuilder.buildPersonBirthdayDay(commands[10]),NumberBuilder.buildPersonBirthdayHour(commands[11]),
                           NumberBuilder.buildPersonBirthdayMinute(commands[12])),
                   NumberBuilder.buildHeight(commands[13]),new Location(NumberBuilder.buildLocationX(commands[14]),
                   NumberBuilder.buildLocationY(commands[15]),NumberBuilder.buildLocationZ(commands[16]))));}
        return group;

    }


    public void toBuildUpdationGroup(StudyGroup group,String[] commands) {
        group.setName(NameBuilder.build(commands[1]));
        group.setCoordinates(new Coordinates(NumberBuilder.buildCoordX(commands[2]), NumberBuilder.buildCoordY(commands[3])));
        group.setCreationDate(ZonedDateTime.now());
        group.setStudentsCount(NumberBuilder.buildstudentsCount(commands[4]));
        group.setTransferredStudents(NumberBuilder.buildTransferredStudents(commands[5]));
        group.setFormOfEducation(EnumBuilder.buildForm(commands[6]));
        group.setSemesterEnum(EnumBuilder.buildSemestr(commands[7]));
        if(commands.length==14){
            group.setGroupAdmin(new Person( NameBuilder.build(commands[8]),LocalDateTime.parse(commands[9]),
                    NumberBuilder.buildHeight(commands[10]),new Location(NumberBuilder.buildLocationX(commands[11]),
                    NumberBuilder.buildLocationY(commands[12]),NumberBuilder.buildLocationZ(commands[13]))));
        } else{
            group.setGroupAdmin(new Person( NameBuilder.build(commands[8]),
                LocalDateTime.of(NumberBuilder.buildPersonBirthdayYear(commands[9]),NumberBuilder.buildPersonBirthdayMonth(commands[10]),
                        NumberBuilder.buildPersonBirthdayDay(commands[11]),NumberBuilder.buildPersonBirthdayHour(commands[12]),
                        NumberBuilder.buildPersonBirthdayMinute(commands[13])),
                NumberBuilder.buildHeight(commands[14]),new Location(NumberBuilder.buildLocationX(commands[15]),
                NumberBuilder.buildLocationY(commands[16]),NumberBuilder.buildLocationZ(commands[17]))));

    }}

    public void toUpdateGroup(StudyGroup group) {
        group.setName(createName());
        group.setCoordinates(new Coordinates(Double.parseDouble(createX()), Integer.parseInt(createY())));
        group.setCreationDate(ZonedDateTime.now());
        group.setStudentsCount(Integer.parseInt(createStudentsCount()));
        group.setTransferredStudents(Integer.parseInt(createTransferredStudents()));
        group.setFormOfEducation(FormOfEducation.valueOf(createFormOfEducation().toUpperCase()));
        Semester semesterEnum = (createSemester() != null) ? Semester.valueOf(createSemester().toUpperCase()) : null;
        group.setSemesterEnum(semesterEnum);
        group.setGroupAdmin(new Person(createPersonName(),
                LocalDateTime.of(Integer.parseInt(createPersonBirthdayYear()), Integer.parseInt(createPersonBirthdayMounth()), Integer.parseInt(createPersonBirthdayDay()), Integer.parseInt(createPersonBirthdayHour()), Integer.parseInt(createPersonBirthdayMinute())),
                Long.parseLong(createPersonHeight()), new Location(0,0,0)));
        String xS = createLocationX();
        String yS = createLocationY();
        String zS = createLocationZ();
        double x = (xS!=null) ? Double.parseDouble(xS):-1;
        long y = (yS!=null) ? Long.parseLong(yS):-1;
        float z = (zS!=null) ? Float.parseFloat(zS):-1;
        group.getGroupAdmin().setLocation(new Location(x,y,z));
    }
    public StudyGroup createGroup() {
        createName();
        createX();
        createY();
        createStudentsCount();
        createTransferredStudents();
        createFormOfEducation();
        createSemester();
        createPersonName();
        createPersonBirthdayYear();
        createPersonBirthdayMounth();
        createPersonBirthdayDay();
        createPersonBirthdayHour();
        createPersonBirthdayMinute();
        createPersonHeight();
        createLocationX();
        createLocationY();
        createLocationZ();
        return toBuildGroup(output);
    }

    public String createName() {
        while (true) {
            System.out.println("Введите имя");
            String name = scanner.nextLine().trim();
            if (name == null | name.isEmpty()) {
                System.out.println("Имя не может быть null");
            } else {
                output[0] = name;
                break;
            }
        }
        return output[0];
    }


    public String createX() {
        while (true) {
            try {
                System.out.println("Введите x");
                String valueX = scanner.nextLine();
                Double x = Double.parseDouble(valueX);
                if (x < -951.0 || valueX.isEmpty() ) {
                    System.out.println("X не может быть отрицательным или null ");}
                else{
                output[1] = valueX;
                break;

            }} catch (NumberFormatException e) {
                System.out.println("Координаты должны быть числом");
            }
        }
        return output[1];
    }

    public String createY() {
        while (true) {
            try {
                System.out.println("Введите y");
                String valueY = scanner.nextLine();
                int y = Integer.parseInt(valueY);
                if (valueY.isEmpty()) {
                    System.out.println("Y не может быть null");
                } else {
                    output[2] = valueY;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Координаты должны быть числом");
            }
        }
        return output[2];
    }

    public String createStudentsCount() {
        while (true) {
            try {
                System.out.println("Введите количество студентов");
                String valueCount = scanner.nextLine();
                Integer count = Integer.parseInt(valueCount);
                if (count < 0 ||valueCount.isEmpty()) {
                    System.out.println("Количество студентов не может быть отрицательным или null ");
                } else {
                    output[3] = valueCount;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Количество студентов должно быть числом");
            }
        }
        return output[3];
    }

    public String createTransferredStudents() {
        while (true) {
            try {
                System.out.println("Введите количество переведенных студентов");
                String valueTransferredStudents = scanner.nextLine();
                int transferredStudents = Integer.parseInt(valueTransferredStudents);
                if (transferredStudents < 0 ) {
                    System.out.println("Количество переведенных студентов не может быть меньше 0 ");
                } else {
                    output[4] = valueTransferredStudents;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Количество переведенных студентов должно быть числом");
            }
        }
        return output[4];
    }
    public String createFormOfEducation() {
        while (true) {
            try {
                System.out.println("Выберите номер формы обучения: ");
                System.out.println("1. DISTANCE_EDUCATION");
                System.out.println("2. FULL_TIME_EDUCATION");
                System.out.println("3. EVENING_CLASSES");

                String input = scanner.nextLine();
                int number = Integer.parseInt(input);

                if (number < 1 || number > 3) {
                    System.out.println("Число должно быть от 1 до 3. Попробуйте снова.");
                    continue;
                }

                String typeForm = switch (number) {
                    case 1 -> "DISTANCE_EDUCATION";
                    case 2 -> "FULL_TIME_EDUCATION";
                    case 3 -> "EVENING_CLASSES";
                    default -> null;
                };

                output[5] = typeForm;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 3.");
            }
        }
        return output[5];
    }


    public String createSemester() {
        while (true) {
            try {
                System.out.println("Выберите номер семестра обучения: ");
                System.out.println("1.FIRST");
                System.out.println("2.FIFTH");
                System.out.println("3.SIXTH");
                System.out.println("4.SEVENTH");
                System.out.println("5.null");

                String input = scanner.nextLine();
                int number = Integer.parseInt(input);
                if (number < 1 || number > 5) {
                    System.out.println("Число должно быть от 1 до 5. Попробуйте снова.");
                    continue;
                }
                String typeForm = switch (number) {
                    case 1-> "FIRST";
                    case 2-> "FIFTH";
                    case 3->  "SIXTH";
                    case 4-> "SEVENTH";
                    case 5-> null;
                    default -> null;
                };
                output[6] = typeForm;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 5.");
            }
        }
        return output[6];
    }

    public String createPersonName() {
        while (true) {
            System.out.println("Введите имя старосты");
            String nameVenue = scanner.nextLine();
            if (nameVenue == null) {
                System.out.println("Имя не может быть null");
            } else {
                output[7] = nameVenue;
                break;
            }
        }
        return output[7];
    }
    public String createPersonBirthdayYear() {
        while (true) {
            try {
                System.out.println("Введите год рождения ");
                String valueYear = scanner.nextLine();
                int year = Integer.parseInt(valueYear);
                if (year > LocalDateTime.now().getYear() || year<0 ) {
                    System.out.println("Человек такого года рождения еще не родился");
                } else {
                    output[8] = valueYear;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Год рождения должен быть числом");
            }
        }
        return output[8];
    }
    public String createPersonBirthdayMounth() {
        while (true) {
            try {
                System.out.println("Введите месяц рождения ");
                String valueMounth = scanner.nextLine();
                int mounth = Integer.parseInt(valueMounth);
                if (mounth >12 ||mounth<1 ) {
                    System.out.println("В году всего 12 месяцев");
                } else {
                    output[9] = valueMounth;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Месяц рождения должен быть числом");
            }
        }
        return output[9];
    }
    public String createPersonBirthdayDay() {
        while (true) {
            try {
                System.out.println("Введите день  рождения ");
                String valueDay = scanner.nextLine();
                int day = Integer.parseInt(valueDay);
                if (day >31 ||day<1 ) {
                    System.out.println("В месяце всего от 1 до 31 дней.");
                } else {
                    output[10] = valueDay;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Дeнь рождения должен быть числом");
            }
        }
        return output[10];
    }
    public String createPersonBirthdayHour() {
        while (true) {
            try {
                System.out.println("Введите час рождения ");
                String valueHour = scanner.nextLine();
                int hour = Integer.parseInt(valueHour);
                if (hour >23 ||hour<0 ) {
                    System.out.println("В сутках всего от 0 до 23 часов");
                } else {
                    output[11] = valueHour;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Час рождения должен быть числом");
            }
        }
        return output[11];
    }
    public String createPersonBirthdayMinute() {
        while (true) {
            try {
                System.out.println("Введите минуту рождения ");
                String valueMinute = scanner.nextLine();
                int minute = Integer.parseInt(valueMinute);
                if (minute >59 ||minute<0 ) {
                    System.out.println("В часе всего от 0 до 59 минут");
                } else {
                    output[12] = valueMinute;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Минута рождения должна быть числом");
            }
        }
        return output[12];
    }
    public String createPersonHeight() {
        while (true) {
            try {
                System.out.println("Введите рост человека");
                String valueHeight = scanner.nextLine();
                long height = Long.parseLong(valueHeight);
                if (height < 0 ) {
                    System.out.println("Рост не может быть меньше 0 ");
                } else {
                    output[13] = valueHeight;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Рост должен быть числом");
            }
        }
        return output[13];
    }

    public String createLocationX() {
        while (true) {
            try {
                System.out.println("Введите x");
                String valueX = scanner.nextLine();
                if (valueX.isEmpty()) {
                    output[14] = null;
                    break;
                }
                double x = Double.parseDouble(valueX);
                output[14] = valueX;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Координаты должны быть числом");
            }
        }
        return output[14];
    }
    public String createLocationY() {
        while (true) {
            try {
                System.out.println("Введите y");
                String valueY = scanner.nextLine();
                if (valueY.isEmpty()) {
                    output[15] = null;
                    break;
                }
                long y = Long.parseLong(valueY);
                output[15] = valueY;
                break;

            } catch (NumberFormatException e) {
                System.out.println("Координаты должны быть числом");
            }
        }
        return output[15];
    }

    public String createLocationZ() {
        while (true) {
            try {
                System.out.println("Введите z");
                String valueZ = scanner.nextLine();
                if (valueZ.isEmpty()) {
                    output[16] = null;
                    break;
                }
                float z = Float.parseFloat(valueZ);
                output[16] = valueZ;
                break;

            } catch (NumberFormatException e) {
                System.out.println("Координаты должны быть числом");
            }
        }
        return output[16];
    }
}
