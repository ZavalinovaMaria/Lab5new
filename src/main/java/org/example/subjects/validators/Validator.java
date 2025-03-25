package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;

/**
 * Интерфейс предназначен для валидации значений.
 * Классы, реализующие этот интерфейс, должны предоставлять методы для проверки
 * корректности переданных строковых значений и получения сообщений об ошибках.
 */
public interface Validator {
    /**
     * Проверяет корректность переданного значения.
     *
     * @param value строка, которую нужно проверить
     * @return {@code true}, если значение прошло проверку
     * @throws InvalidValueException если значение не соответствует критериям
     */
    boolean validate(String value) throws InvalidValueException;

    /**
     * Возвращает сообщение об ошибке, связанное с валидацией.
     *
     * @return строка с описанием ошибки
     */
    String getErrorMessage();
}
