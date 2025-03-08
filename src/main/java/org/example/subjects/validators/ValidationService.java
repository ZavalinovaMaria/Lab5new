package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;

import java.util.List;
public class ValidationService {

    public static boolean useValidator(String value, String parameter) throws InvalidValueException, IllegalArgumentException{
        List<Validator> validators = ValidatorFactory.getValidators(parameter);
        for (Validator validator: validators) {
            if (!validator.validate(value)) {
                return false;
            }
        }
        return true;
    }
}


