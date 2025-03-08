package org.example.subjects.validators;

import org.example.exceptions.InvalidValueException;

import java.time.*;


public class ParserService {
    public static <T> T parseAndValidate(String value, String parameter, Class<T> type) throws InvalidValueException, IllegalArgumentException, NullPointerException,NumberFormatException{
        if (ValidationService.useValidator(value,parameter)) {
            if (type == Integer.class) {
                return type.cast(Integer.parseInt(value)); // Обработка Integer
            } else if (type == Double.class) {
                return type.cast(Double.parseDouble(value)); // Обработка Double
            } else if (type == Long.class) {
                return type.cast(Long.parseLong(value)); // Обработка Long
            } else if (type == Float.class) {
                return type.cast(Float.parseFloat(value)); // Обработка Float
            } else if (type == String.class) {
                return type.cast(value); // Возвращаем строку как есть // Возвращаем строку как есть
            }else if(type == ZonedDateTime.class){
                return type.cast(ZonedDateTime.parse(value));
            }else if(type == LocalDateTime.class){
                return type.cast(LocalDateTime.parse(value));
            }else {
                // Другие типы, которые могут быть
                throw new InvalidValueException("Не поддерживаемый тип", null);
            }
            }
        return null;
    }
}
