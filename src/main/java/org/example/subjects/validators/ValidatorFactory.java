package org.example.subjects.validators;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidatorFactory {
    public static List<Validator> getValidators(String parameter) {
        List<Validator> validators = new ArrayList<>();
        switch (parameter) {
            case "name", "creationDate", "birthday", "coordinatesY", "location" ->
                    validators.add(new NotNullValidator());
            case "id", "studentsCount" -> {
                validators.add(new NotNullValidator());
                validators.add(new MinimallValidator(0));
            }
            case "coordinatesX" -> {
                validators.add(new NotNullValidator());
                validators.add(new MinimallValidator(-951));
            }
            case "transferredStudents", "height" -> validators.add(new MinimallValidator(0));
            case "year" -> {
                validators.add(new MinimallValidator(0));
                validators.add(new MaximumValidator(LocalDateTime.now().getYear()));
            }
            case "month" -> {
                validators.add(new MinimallValidator(1));
                validators.add(new MaximumValidator(12));
            }
            case "day" -> {
                validators.add(new MinimallValidator(1));
                validators.add(new MaximumValidator(31));
            }
            case "hour" -> {
                validators.add(new MinimallValidator(0));
                validators.add(new MaximumValidator(23));
            }
            case "minute" -> {
                validators.add(new MinimallValidator(0));
                validators.add(new MaximumValidator(59));
            }
            default -> throw new IllegalArgumentException(String.format("Неизвестный параметр: %s ", parameter));
        }
        return validators;
    }
}












