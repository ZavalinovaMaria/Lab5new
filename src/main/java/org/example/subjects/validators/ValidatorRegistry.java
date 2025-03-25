package org.example.subjects.validators;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс представляет собой хранилище валидаторов,
 * которые используются для проверки различных параметров.
 * Валидаторы хранятся в виде карты, где ключ — это
 * название параметра, а значение — список {@link Validator}, которые должны
 * быть применены к этому параметру.
 */
public class ValidatorRegistry {
    /**
     * Карта, содержащая соответствие параметров и их списка валидаторов.
     * Инициализируется статическим блоком, который задает правила валидации для различных параметров.
     */
    private static final Map<String, List<Validator>> validatorsMap = new HashMap<>();

    static {
        validatorsMap.put("name", List.of(new NotNullValidator()));
        validatorsMap.put("creationDate", List.of(new NotNullValidator()));
        validatorsMap.put("x", List.of(new NotNullValidator(), new MinimallValidator(-951)));
        validatorsMap.put("y", List.of(new NotNullValidator()));
        validatorsMap.put("id", List.of(new NotNullValidator(), new MinimallValidator(0)));
        validatorsMap.put("studentsCount", List.of(new NotNullValidator(), new MinimallValidator(0)));
        validatorsMap.put("transferredStudents", List.of(new MinimallValidator(0)));
        validatorsMap.put("height", List.of(new NotNullValidator(), new MinimallValidator(0)));
        validatorsMap.put("birthday", List.of(new NotNullValidator()));
        validatorsMap.put("location", List.of(new NotNullValidator()));
        validatorsMap.put("year", List.of(new NotNullValidator(), new MinimallValidator(0), new MaximumValidator(LocalDateTime.now().getYear())));
        validatorsMap.put("month", List.of(new NotNullValidator(), new MinimallValidator(1), new MaximumValidator(12)));
        validatorsMap.put("day", List.of(new NotNullValidator(), new MinimallValidator(1), new MaximumValidator(31)));
        validatorsMap.put("hour", List.of(new NotNullValidator(), new MinimallValidator(0), new MaximumValidator(23)));
        validatorsMap.put("minute", List.of(new NotNullValidator(), new MinimallValidator(0), new MaximumValidator(59)));
    }

    /**
     * Возвращает список валидаторов, соответствующих переданному параметру.
     *
     * @param parameter название параметра, для которого требуется получить валидаторы
     * @return список валидаторов для указанного параметра; если параметр отсутствует в карте, возвращается пустой список
     */
    public static List<Validator> getValidators(String parameter) {
        return validatorsMap.getOrDefault(parameter, Collections.emptyList());
    }
}

