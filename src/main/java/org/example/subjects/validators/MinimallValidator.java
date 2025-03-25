package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;

/**
 * Класс реализует интерфейс {@link Validator} и предназначен
 * для проверки, не меньше ли введенное значение заданного минимального предела.
 */
public class MinimallValidator implements Validator {
    private final int minValue;

    public MinimallValidator(int minValue) {
        this.minValue = minValue;
    }

    /**
     * Проверяет, является ли переданная строка числом и не меньше ли она минимального предела.
     *
     * @param value Строковое представление числа для проверки.
     * @return {@code true}, если значение корректно и не меньше допустимого минимума.
     * @throws InvalidValueException Если значение меньше {@code maxValue}.
     * @throws NumberFormatException Если строка не может быть преобразована в число.
     */
    public boolean validate(String value) throws InvalidValueException, NumberFormatException {
        double number;
        try {
            number = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidValueException(getErrorMessage2(), e);
        }
        if (number < minValue) {
            throw new InvalidValueException(getErrorMessage(), null);
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Значение поля должно быть больше %s", minValue);
    }

    public String getErrorMessage2() {
        return "Значение не является числом";
    }

}

