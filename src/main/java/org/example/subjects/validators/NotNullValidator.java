package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;

/**
 * Класс реализует интерфейс {@link Validator} и предназначен
 * для проверки, не является ли введенное значение пустым или равным null.
 */

public class NotNullValidator implements Validator {
    /**
     * Проверяет, является ли переданное значение непустой строкой.
     *
     * @param value строка, которую нужно проверить
     * @return {@code true}, если строка не пуста и не состоит только из пробелов
     * @throws InvalidValueException если строка {@code null} или пуста
     */
    @Override
    public boolean validate(String value) throws InvalidValueException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidValueException(getErrorMessage(), null);
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return "Значение поля не может быть null";
    }
}
