package org.example.subjects;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.validators.ParserService;

import java.util.Scanner;

public class NameCreator {
    public static String returnName(String name) throws InvalidValueException{
        return ParserService.parseAndValidate(name, "name", String.class);
    }
    public static String createName(String message) {
        while (true) {
            try {
                Scanner scanner =  new Scanner(System.in);
                System.out.println(message);
                return returnName(scanner.nextLine().trim());
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage());

            }
        }
    }
}
