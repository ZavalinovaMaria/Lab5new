package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;
public class MaximumValidator implements Validator {
    private final int maxValue;

    public MaximumValidator(int maxValue) {
        this.maxValue = maxValue;
    }
    public boolean validate(String value) throws InvalidValueException, NumberFormatException {
        double number;
        try {
            number = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidValueException(getErrorMessage2(), e);
        }
        if (number > maxValue){
            throw new InvalidValueException(getErrorMessage(), null);
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Значение поля должно быть меньше %s",maxValue);
    }
    public String getErrorMessage2() {
        return "Значение не является числом";
    }
}
