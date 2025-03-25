package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;

/**
 * Класс реализует интерфейс {@link Validator} и предназначен
 * для проверки, не превышает ли введенное значение заданный максимальный предел.
 */
public class MaximumValidator implements Validator {
    private final int maxValue;

    public MaximumValidator(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Проверяет, является ли переданная строка числом и не превышает ли она максимальный предел.
     *
     * @param value Строковое представление числа для проверки.
     * @return {@code true}, если значение корректно и не превышает допустимый максимум.
     * @throws InvalidValueException Если значение превышает {@code maxValue}.
     * @throws NumberFormatException Если строка не может быть преобразована в число.
     */
    public boolean validate(String value) throws InvalidValueException, NumberFormatException {
        double number;
        try {
            number = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidValueException(getErrorMessage2(), e);
        }
        if (number > maxValue) {
            throw new InvalidValueException(getErrorMessage(), null);
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Значение поля должно быть меньше %s", maxValue);
    }

    public String getErrorMessage2() {
        return "Значение не является числом";
    }
}
