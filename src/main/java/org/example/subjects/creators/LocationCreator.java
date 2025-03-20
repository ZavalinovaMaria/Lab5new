package org.example.subjects.creators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.Location;

import java.util.Arrays;
import java.util.Scanner;

import static org.example.subjects.parsers.FieldParserUtil.parseField;
import static org.example.subjects.parsers.FieldParserUtil.parseFieldFromConsole;

public class LocationCreator {
    public LocationCreator() {
    }

    public Location createLocation(String xArg, String yArg, String zArg) throws InvalidValueException {
        if (areStringsNull(xArg, yArg, zArg)) {
            return null;
        } else {
            double x = parseField(xArg, "location", Double.class);
            long y = parseField(yArg, "location", Long.class);
            float z = parseField(zArg, "location", Float.class);
            return new Location(x, y, z);
        }
    }

    public Location createLocationFromConsole() throws InvalidValueException {
        if (chooseOption()==2) {
            return null;
        } else {
            double x = parseFieldFromConsole("Введите x", "location", Double.class);
            long y = parseFieldFromConsole("Введите y", "location", Long.class);
            float z = parseFieldFromConsole("Введите z", "location", Float.class);
            return new Location(x, y, z);
        }
    }

    public boolean areStringsNull(String... string) {
        return Arrays.stream(string)
                .allMatch(str -> str == null || str.isEmpty());
    }

    private int chooseOption() {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            try {
                System.out.println("Хотите задать координаты старосты?");
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

