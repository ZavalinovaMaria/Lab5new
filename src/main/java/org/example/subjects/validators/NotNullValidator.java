package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;

public class NotNullValidator implements Validator {
    @Override
    public  boolean validate(String value) throws InvalidValueException {
        if (value==null || value.trim().isEmpty()) {
            throw new InvalidValueException(getErrorMessage(),null);
        }
        return true;
    }
    @Override
    public String getErrorMessage() {
        return "Значение поля не может быть null";
    }
}
