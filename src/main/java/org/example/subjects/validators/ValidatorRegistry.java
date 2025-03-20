package org.example.subjects.validators;

import java.time.LocalDateTime;
import java.util.*;
public class ValidatorRegistry {
    private static final Map<String, List<Validator>> validatorsMap = new HashMap<>();

    static {
        validatorsMap.put("name", List.of(new NotNullValidator()));
        validatorsMap.put("creationDate", List.of(new NotNullValidator()));
        validatorsMap.put("x", List.of(new NotNullValidator(), new MinimallValidator(-951)));
        validatorsMap.put("y", List.of(new NotNullValidator()));
        validatorsMap.put("id", List.of(new NotNullValidator(), new MinimallValidator(0)));
        validatorsMap.put("studentsCount", List.of(new NotNullValidator(), new MinimallValidator(0)));
        validatorsMap.put("transferredStudents", List.of(new MinimallValidator(0)));
        validatorsMap.put("height", List.of(new NotNullValidator(),new MinimallValidator(0)));
        validatorsMap.put("birthday", List.of(new NotNullValidator()));
        validatorsMap.put("location", List.of(new NotNullValidator()));
        validatorsMap.put("year", List.of(new NotNullValidator(),new MinimallValidator(0), new MaximumValidator(LocalDateTime.now().getYear())));
        validatorsMap.put("month", List.of(new NotNullValidator(),new MinimallValidator(1), new MaximumValidator(12)));
        validatorsMap.put("day", List.of(new NotNullValidator(),new MinimallValidator(1), new MaximumValidator(31)));
        validatorsMap.put("hour", List.of(new NotNullValidator(),new MinimallValidator(0), new MaximumValidator(23)));
        validatorsMap.put("minute", List.of(new NotNullValidator(),new MinimallValidator(0), new MaximumValidator(59)));
    }

    public static List<Validator> getValidators(String parameter) {
        return validatorsMap.getOrDefault(parameter, Collections.emptyList());
    }
}

