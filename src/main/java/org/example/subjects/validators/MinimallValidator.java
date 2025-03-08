package org.example.subjects.validators;


import org.example.exceptions.InvalidValueException;

public class MinimallValidator implements Validator {
    private final int minValue;

    public MinimallValidator(int minValue) {
        this.minValue = minValue;
    }
    public boolean validate(String value) throws InvalidValueException, NumberFormatException{
        double number;
        try {
            number = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidValueException(getErrorMessage2(), null);
        }
        if (number < minValue){
            throw new InvalidValueException(getErrorMessage(), null);
        }
        return true;
    }

   @Override
    public String getErrorMessage() {
        return String.format("Значение поля должно быть больше %s",minValue);
    }
    public String getErrorMessage2() {
        return "Значение не является числом";
    }

}

