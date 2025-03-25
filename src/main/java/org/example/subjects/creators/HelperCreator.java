package org.example.subjects.creators;

import java.util.Arrays;
import java.util.Scanner;

public interface HelperCreator {
    /**
     * Проверяет, являются ли все переданные строки {@code null} или пустыми.
     *
     * @param string массив строк для проверки
     * @return {@code true}, если все строки пустые или равны {@code null}, иначе {@code false}
     */
    default boolean areStringsNull(String... string) {
        return Arrays.stream(string)
                .allMatch(str -> str == null || str.isEmpty());
    }

    /**
     * Запрашивает у пользователя, хочет ли он задать данные для объекта.
     * Пользователь должен ввести "1" (Да) или "2" (Нет).
     *
     * @return {@code 1}, если пользователь хочет создать объект, {@code 2}, если нет
     */
    default int chooseOption(String condition) {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            try {
                System.out.println(condition);
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
