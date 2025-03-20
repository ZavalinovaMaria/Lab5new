package org.example.subjects.validators;


import org.example.exceptions.InvalidValueException;

public interface Validator {
     boolean validate(String value) throws InvalidValueException;
    String getErrorMessage();
}
