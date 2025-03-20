package org.example.subjects.creators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.Location;
import org.example.subjects.Person;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

import static org.example.subjects.parsers.FieldParserUtil.parseField;
import static org.example.subjects.parsers.FieldParserUtil.parseFieldFromConsole;

public class PersonCreator {
    private final LocationCreator creator = new LocationCreator();
    public PersonCreator(){
    }

    public  boolean areStringsNull(String... strs) {
        return Arrays.stream(strs)
                .allMatch(str -> str == null || str.isEmpty());
    }
    public Person createPerson(String name, String birth, String heightS, String xArg, String yArg, String zArg) throws InvalidValueException {
        if(areStringsNull(name,birth,heightS,xArg,yArg,zArg)){
            return null;
        }
        else{
            String personName = parseField(name, "name", String.class);
            LocalDateTime birthday = parseField(birth, "birthday", LocalDateTime.class);
            long height = parseField(heightS, "height", Long.class);
            Location location = creator.createLocation(xArg,yArg,zArg);
            return new Person(personName,birthday,height,location);
        }
    }
    public Person createPersonFromConsole() throws InvalidValueException {
        if(chooseOption()==2){
            return null;
        }
        else{
            String personName = parseFieldFromConsole("Введите имя старосты", "name", String.class);
            int year = parseFieldFromConsole("Введите год рождения ","year", Integer.class);
            int month=parseFieldFromConsole("Введите месяц  рождения ","month", Integer.class);
            int day=parseFieldFromConsole("Введите день рождения ","day", Integer.class);
            int hour=parseFieldFromConsole("Введите час рождения ","hour", Integer.class);
            int minute=parseFieldFromConsole("Введите минуту рождения ","minute", Integer.class);
            LocalDateTime birthday = LocalDateTime.of(year,month,day,hour,minute);
            long height = parseFieldFromConsole("Введите рост человека", "height", Long.class);
            Location location = creator.createLocationFromConsole();
            return new Person(personName,birthday,height,location);
        }
    }
    private int chooseOption() {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            try {
                System.out.println("Хотите задать старосту?");
                System.out.println("1.Да");
                System.out.println("2.Нет ");
                String input = scanner.nextLine();
                number = Integer.parseInt(input);
                if (number != 1 && number != 2) {
                    System.out.println("Нужно ввести 1 или 2. Попробуйте снова.");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 или 2.");
            }
        }
    }
}
