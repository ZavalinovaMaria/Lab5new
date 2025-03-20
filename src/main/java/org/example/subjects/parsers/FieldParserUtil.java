package org.example.subjects.parsers;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.parsers.FieldParser;

public class FieldParserUtil {
    public static <T> T parseField(String value, String fieldName, Class<T> type) throws InvalidValueException {
        return new FieldParser<>(fieldName, type).parseFromString(value);
    }
    public static <T> T parseFieldFromConsole(String message, String fieldName, Class<T> type) throws InvalidValueException {
        return new FieldParser<>(fieldName, type).parseFromConsole(message);
    }
}

