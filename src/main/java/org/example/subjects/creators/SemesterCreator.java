package org.example.subjects.creators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.Semester;

import java.util.Scanner;

public class SemesterCreator {
    public  Semester createSemester(String name) throws InvalidValueException {
        try {
            return name != null && !name.trim().isEmpty() ? Semester.valueOf(name.trim().toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            throw new InvalidValueException(String.format("Некорректное значение для enum поля:%s", name), null);
        }
    }
    public  Semester createSemesterFromConsole() throws InvalidValueException {
        Scanner scanner = new Scanner(System.in);
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
                return switch (number) {
                    case 1 -> Semester.FIRST;
                    case 2 -> Semester.FIFTH;
                    case 3 -> Semester.SIXTH;
                    case 4 -> Semester.SEVENTH;
                    case 5 -> null;
                    default -> null;
                };
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 5.");
            }
        }
    }
}
