package org.example.subjects.parsers;

import org.example.exceptions.InvalidValueException;

import java.util.Scanner;
public class FieldParser<T> {
    private final String fieldName;
    private final Class<T> type;

    public FieldParser(String fieldName, Class<T> type) {
        this.fieldName = fieldName;
        this.type = type;
    }
    public T parseFromString(String value) throws InvalidValueException {
        return ParserService.parseAndValidate(value, fieldName, type);
    }

    public T parseFromConsole(String message ) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().trim();
                return parseFromString(input);
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

