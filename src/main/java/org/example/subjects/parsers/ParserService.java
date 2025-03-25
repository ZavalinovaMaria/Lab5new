package org.example.subjects.parsers;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.validators.Validator;
import org.example.subjects.validators.ValidatorRegistry;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Класс предназначен для парсинга и валидации входных данных.
 * Используется для преобразования строковых значений в объекты указанного типа {@code T}
 * с предварительной проверкой на соответствие заданным требованиям.
 */
public class ParserService {
    /**
     * Выполняет парсинг и валидацию входного значения.
     *
     * @param value     строковое значение, подлежащее парсингу
     * @param parameter название параметра, для которого выполняется валидация
     * @param type      класс типа, в который требуется преобразовать строку
     * @param <T>       тип возвращаемого значения
     * @return объект типа {@code T}, полученный в результате парсинга
     * @throws InvalidValueException если значение не проходит валидацию или не может быть преобразовано
     */
    public static <T> T parseAndValidate(String value, String parameter, Class<T> type) throws InvalidValueException {
        List<Validator> validators = ValidatorRegistry.getValidators(parameter);
        for (Validator validator : validators) {
            if (!validator.validate(value)) {
                throw new InvalidValueException("Ошибка валидации для " + parameter, null);
            }
        }
        return convertType(value, type);
    }

    /**
     * Преобразует строковое значение в заданный тип.
     *
     * @param value1 строковое представление значения
     * @param type   класс типа, в который выполняется преобразование
     * @param <T>    тип возвращаемого значения
     * @return объект указанного типа {@code T}
     * @throws InvalidValueException если преобразование невозможно или передано некорректное значение
     */
    private static <T> T convertType(String value1, Class<T> type) throws InvalidValueException {
        String value = value1.trim();
        try {
            if (type == Integer.class) {
                return type.cast(Integer.parseInt(value));
            } else if (type == Double.class) {
                return type.cast(Double.parseDouble(value));
            } else if (type == Long.class) {
                return type.cast(Long.parseLong(value));
            } else if (type == Float.class) {
                return type.cast(Float.parseFloat(value));
            } else if (type == String.class) {
                return type.cast(value);
            } else if (type == ZonedDateTime.class) {
                return type.cast(ZonedDateTime.parse(value));
            } else if (type == LocalDateTime.class) {
                return type.cast(LocalDateTime.parse(value));
            } else {
                throw new InvalidValueException("Не поддерживаемый тип: " + type.getSimpleName(), null);
            }
        } catch (NumberFormatException e) {
            throw new InvalidValueException("Ошибка преобразования в " + type.getSimpleName() + ": " + e.getMessage(), null);
        } catch (DateTimeParseException e) {
            throw new InvalidValueException("Некорректный формат даты", e);
        } catch (NullPointerException e) {
            throw new InvalidValueException("Пустое поле", e);
        }
    }
}

