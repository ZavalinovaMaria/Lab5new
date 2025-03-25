package org.example.subjects.parsers;

import org.example.exceptions.InvalidValueException;

/**
 * Вспомогательный класс предназначен для парсинга полей
 * с использованием {@link FieldParser}. Позволяет выполнять парсинг значений как из строки,
 * так и из консоли, с учетом валидации.
 */
public class FieldParserUtil {
    /**
     * Выполняет парсинг строки в указанный тип с проверкой валидации.
     *
     * @param value     строковое значение, подлежащее парсингу
     * @param fieldName название поля, для которого выполняется парсинг
     * @param type      класс типа, в который требуется преобразовать строку
     * @param <T>       тип возвращаемого значения
     * @return объект типа {@code T}, полученный в результате парсинга
     * @throws InvalidValueException если значение не проходит валидацию или не может быть преобразовано
     */
    public static <T> T parseField(String value, String fieldName, Class<T> type) throws InvalidValueException {
        return new FieldParser<>(fieldName, type).parseFromString(value);
    }

    /**
     * Выполняет парсинг значения из консоли в указанный тип с проверкой валидации.
     * Метод запрашивает ввод у пользователя и ожидает корректное значение.
     *
     * @param message   сообщение, выводимое пользователю перед вводом
     * @param fieldName название поля, для которого выполняется парсинг
     * @param type      класс типа, в который требуется преобразовать введенное значение
     * @param <T>       тип возвращаемого значения
     * @return объект типа {@code T}, полученный в результате парсинга
     * @throws InvalidValueException если значение не проходит валидацию
     */
    public static <T> T parseFieldFromConsole(String message, String fieldName, Class<T> type) throws InvalidValueException {
        return new FieldParser<>(fieldName, type).parseFromConsole(message);
    }
}

