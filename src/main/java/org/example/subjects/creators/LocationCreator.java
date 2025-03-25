package org.example.subjects.creators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.Location;

import static org.example.subjects.parsers.FieldParserUtil.parseField;
import static org.example.subjects.parsers.FieldParserUtil.parseFieldFromConsole;

/**
 * Класс предназначен для создания объектов {@link Location}.
 * Поддерживает создание на основе переданных строковых значений и ввода с консоли.
 */
public class LocationCreator implements HelperCreator {
    public LocationCreator() {
    }

    /**
     * Создаёт объект {@link Location} на основе переданных строковых значений координат.
     * Если все переданные строки равны {@code null} или пусты, возвращает {@code null}.
     *
     * @param xArg строковое представление координаты X
     * @param yArg строковое представление координаты Y
     * @param zArg строковое представление координаты Z
     * @return объект {@code Location} или {@code null}, если аргументы отсутствуют
     * @throws InvalidValueException если значения не могут быть корректно преобразованы
     */
    public Location createLocation(String xArg, String yArg, String zArg) throws InvalidValueException {
        if (areStringsNull(xArg, yArg, zArg)) {
            return null;
        } else {
            double x = parseField(xArg, "location", Double.class);
            long y = parseField(yArg, "location", Long.class);
            float z = parseField(zArg, "location", Float.class);
            return new Location(x, y, z);
        }
    }

    /**
     * Создаёт объект {@link Location}, запрашивая координаты у пользователя через консоль.
     * Предоставляет пользователю возможность отказаться от ввода координат.
     *
     * @return объект {@code Location} или {@code null}, если пользователь отказался от ввода
     * @throws InvalidValueException если введённые значения некорректны
     */
    public Location createLocationFromConsole() throws InvalidValueException {
        if (chooseOption("Хотите задать координаты старосты?") == 2) {
            return null;
        } else {
            double x = parseFieldFromConsole("Введите x", "location", Double.class);
            long y = parseFieldFromConsole("Введите y", "location", Long.class);
            float z = parseFieldFromConsole("Введите z", "location", Float.class);
            return new Location(x, y, z);
        }
    }

}

