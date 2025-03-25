package org.example.subjects.parsers;

import org.example.exceptions.InvalidValueException;

import java.util.Scanner;

/**
 * Класс предназначен для обработки и валидации ввода данных определенного типа.
 * Используется для преобразования строковых значений в объекты указанного типа {@code T}.
 * Валидация осуществляется с помощью {@link ParserService}.
 *
 * @param <T> тип данных, в который должен быть преобразован ввод
 */
public class FieldParser<T> {
    private final String fieldName;
    /**
     * Класс типа данных, в который требуется преобразовать строковый ввод.
     */
    private final Class<T> type;

    public FieldParser(String fieldName, Class<T> type) {
        this.fieldName = fieldName;
        this.type = type;
    }

    /**
     * Выполняет парсинг строки в объект указанного типа с предварительной валидацией.
     *
     * @param value строковое представление значения
     * @return объект типа {@code T}, полученный в результате парсинга
     * @throws InvalidValueException если значение не проходит валидацию
     */
    public T parseFromString(String value) throws InvalidValueException {
        return ParserService.parseAndValidate(value, fieldName, type);
    }

    /**
     * Выполняет парсинг сообщения из консоли в объект указанного типа с предварительной валидацией
     *
     * @param message сообщение, отображаемое пользователю перед вводом
     * @return объект типа {@code T}, полученный в результате успешного парсинга
     */
    public T parseFromConsole(String message) {
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

