package org.example.console;

import org.example.subjects.*;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Creator {
    Scanner scanner = new Scanner(System.in);
    String[] output = new String[17];

    public Creator() {

    }


    /**
     * Creates a Ticket class object based on an array of commands.
     *
     * @param commands An array of commands containing the data to create the Ticket object.
     * @return Returns the created Ticket object.
     */
   public StudyGroup toBuildGroup(String[] commands) {
        //Integer id = provider.generateNextId();
        //Integer venueId = provider.generateNextVenueId();
        //if (checkingUniqueness(id) && checkingIdUniqueness(venueId)) { мб в итоге может быть плохо на всякий заначка
        StudyGroup group = new StudyGroup();
        group.setId(group.generateNextId());
        // ticket.setUser(user.getUserName());
        group.setName(commands[0]);
        group.setCoordinates(new Coordinates(Double.parseDouble(commands[1]), Integer.parseInt(commands[2])));
        group.setCreationDate(ZonedDateTime.now());
        group.setStudentsCount(Integer.parseInt(commands[3]));
        group.setTransferredStudents(Integer.parseInt(commands[4]));
        group.setFormOfEducation(FormOfEducation.valueOf(commands[5].toUpperCase()));
        group.setSemesterEnum(Semester.valueOf(commands[6].toUpperCase()));
        group.setGroupAdmin(new Person( commands[7],
                LocalDateTime.of(Integer.parseInt(commands[8]),Integer.parseInt(commands[9]),Integer.parseInt(commands[10]),Integer.parseInt(commands[11]),Integer.parseInt(commands[12])),
                Long.parseLong(commands[13]),new Location(Double.parseDouble(commands[14]),Long.parseLong(commands[15]),Float.parseFloat(commands[16]))));
        return group;
        // }
    }

/*
    public StudyGroup toBuildUpdationTicket(Ticket ticket,String[] commands) {
        // Integer id = provider.generateNextVenueId();
        //try{
        //if(checkingIdUniqueness(id)){
        ticket.setName(commands[0]);
        ticket.setCoordinates(new Coordinates(Float.parseFloat(commands[1]), Float.parseFloat(commands[2])));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(Float.parseFloat(commands[3]));
        ticket.setDiscount(Double.parseDouble(commands[4]));
        ticket.setRefundable(Boolean.parseBoolean(commands[5]));
        ticket.setType(TicketType.valueOf(commands[6].toUpperCase()));
        ticket.setVenue(new Venue(null, commands[7], Long.parseLong(commands[8]), VenueType.valueOf(commands[9].toUpperCase())));

        //}
        // }catch (NotUniqueValueException e ){
        // System.out.println(e.getMessage());}
        return ticket;
    }
    /**
     * Updates the fields of the Ticket object.
     * The method requests data from the user to update the fields of the Ticket object and
     * uses the received data to update the corresponding fields of the object.
     *
     * @param ticket The Ticket object to update.
     */
   /* public void toUpdateTicket(Ticket ticket){
        ticket.setName(createName());
        ticket.setCoordinates(new Coordinates(Float.parseFloat(createX()), Float.parseFloat(createY())));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(Float.parseFloat(createPrice()));
        ticket.setDiscount(Double.parseDouble(createDiscount()));
        ticket.setRefundable(Boolean.parseBoolean(createRefundable()));
        ticket.setType(TicketType.valueOf(createType().toUpperCase()));
        ticket.setVenue(new Venue(null,  createVenueName(), Long.parseLong(createVenueCapacity()), VenueType.valueOf(createVenueType().toUpperCase())));
    }

    /**
     * Creates a new Ticket object based on user input.
     * <p>
     * The method requests data from the user to create a new Ticket object,
     * uses the entered data to initialize the fields of the Ticket object and returns
     * created Ticket object.
     *
     * @return A new Ticket object created from the input.
     */
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
    /**
     * Creates a unique key for the Ticket object based on user input.
     * @return The unique key for the Ticket object
     */
    /*public String createKey() {
        DatabaseProvider provider = new DatabaseProvider(new OnlineUser());
        provider.generateNextId();
        Integer keyValue = null;
        while (true) {
            try {
                System.out.println("Введите key");
                String value = scanner.nextLine();
                keyValue = Integer.parseInt(value);
                if (keyValue < 0) {
                    System.out.println("Key не может быть отрицательным или null ");
                } else {
                    if (checkingUniqueness(keyValue)) {
                        keyStoragee.add(keyValue);
                        output[0] = value;
                        break;
                    }
                }

            } catch (NotUniqueValueException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Кey должен быть номером");
                continue;
            }
        }
        return output[0];
    }

    */


    /**
     * Creates name for the Ticket object based on user input.
     *
     * @return The name for the Ticket object
     */
    public String createName() {
        while (true) {
            System.out.println("Введите name");
            String name = scanner.nextLine().trim();
            if (name == null | name.isEmpty()) {
                System.out.println("Name не может быть null");
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

    /**
     * Creates y coordinate for the Ticket object based on user input.
     *
     * @return The y coordinate for the Ticket object
     */
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

    /**
     * Creates price for the Ticket object based on user input.
     *
     * @return The price for the Ticket object
     */
    public String createStudentsCount() {
        while (true) {
            try {
                System.out.println("Введите StudentsCount");
                String valueCount = scanner.nextLine();
                Integer count = Integer.parseInt(valueCount);
                if (count < 0 ||valueCount.isEmpty()) {
                    System.out.println("StudentsCount не может быть отрицательным или null ");
                } else {
                    output[3] = valueCount;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("StudentsCount должно быть числом");
            }
        }
        return output[3];
    }

    /**
     * Creates discount for the Ticket object based on user input.
     *
     * @return The discount for the Ticket object
     */
    public String createTransferredStudents() {
        while (true) {
            try {
                System.out.println("Введите TransferredStudent");
                String valueTransferredStudents = scanner.nextLine();
                int transferredStudents = Integer.parseInt(valueTransferredStudents);
                if (transferredStudents < 0 ) {
                    System.out.println("TransferredStudents не может быть меньше 0 ");
                } else {
                    output[4] = valueTransferredStudents;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("TransferredStudents должно быть числом");
            }
        }
        return output[4];
    }




    public String createFormOfEducation() {
        String out = null;
        System.out.println("Выберите номер формы обучения: ");
        System.out.println("1.DISTANCE_EDUCATION");
        System.out.println("2.FULL_TIME_EDUCATION");
        System.out.println("3.EVENING_CLASSES");
        boolean check = true;
        boolean firstInput = true;
        while (check) {
            if (!firstInput) {
                System.out.println("Выберите номер формы обучения: ");
            }
            String input = scanner.nextLine();
            int number = Integer.parseInt(input);
            String typeForm=null;
            switch (number){
                case 1->typeForm="DISTANCE_EDUCATION";
                case 2->typeForm="FULL_TIME_EDUCATION";
                case 3->typeForm="EVENING_CLASSES";
            }
            firstInput = false;
            for (FormOfEducation type : FormOfEducation.values()) {
                if (typeForm.equals(type.form)) {
                    out = typeForm;
                    check = false;
                    break;
                }
            }
            if (check) {
                System.out.println("Вы ввели цифру, которой нет в списке  ");
            }
        }
        output[5] = out;
        return  output[5];
    }
    public String createSemester() {
        String out = null;
        System.out.println("Выберите номер семестра обучения: ");
        System.out.println("1.FIRST");
        System.out.println("2.FIFTH");
        System.out.println("3.SIXTH");
        System.out.println("4.SEVENTH");
        boolean check = true;
        boolean firstInput = true;
        while (check) {
            if (!firstInput) {
                System.out.println("Выберите номер формы обучения: ");
            }
            String input = scanner.nextLine();
            int number = Integer.parseInt(input);
            String typeForm=null;
            switch (number){
                case 1->typeForm = "FIRST";
                case 2->typeForm = "FIFTH";
                case 3->typeForm = "SIXTH";
                case 4->typeForm = "SEVENTH";
            }
            firstInput = false;
            for (Semester semester : Semester.values()) {
                if (typeForm.equals(semester.semester)) {
                    out = typeForm;
                    check = false;
                    break;
                }
            }
            if (check) {
                System.out.println("Вы ввели цифру, которой нет в списке  ");
            }
        }
        output[6] = out;
        return  output[6];
    }

    /**
     * Creates venue`s id for the Ticket object based on user input.
     * @return The venue`s id for the Ticket object
     */
    /*
    public String createVenueId() {

        Random random = new Random();
        Integer valueId = random.nextInt(100000) + 1;
        try {
            if (checkingIdUniqueness(valueId)) {
                idVenueStorage.add(valueId);
                output[8] = String.valueOf(valueId);
            }
        } catch (NotUniqueValueException e) {
            System.out.println(e.getMessage());
        }
        return  output[8];
    }

     */

    /**
     * Creates venue`s name for the Ticket object based on user input.
     *
     * @return The venue`s name for the Ticket object
     */
    public String createPersonName() {

        while (true) {
            System.out.println("Введите Person Name");
            String nameVenue = scanner.nextLine();
            if (nameVenue == null) {
                System.out.println("Name не может быть null");
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
                if (year > LocalDateTime.now().getYear()-15 ) {
                    System.out.println("Год рождения не может быть больше   ");
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
                System.out.println("Дата рождения должна быть числом");
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
                    System.out.println("TransferredStudents не может быть меньше 0 ");
                } else {
                    output[13] = valueHeight;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Рост должно быть числом");
            }
        }
        return output[13];
    }
    public String createLocationX() {
        while (true) {
            try {
                System.out.println("Введите x");
                String valueX = scanner.nextLine();
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
