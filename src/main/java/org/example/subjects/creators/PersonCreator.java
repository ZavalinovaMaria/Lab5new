package org.example.subjects.creators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.Location;
import org.example.subjects.Person;

import java.time.LocalDateTime;

import static org.example.subjects.parsers.FieldParserUtil.parseField;
import static org.example.subjects.parsers.FieldParserUtil.parseFieldFromConsole;

/**
 * Класс предназначен для создания объектов {@link Person}.
 * Поддерживает создание как на основе переданных строковых значений, так и через ввод с консоли.
 */
public class PersonCreator implements HelperCreator {
    private final LocationCreator creator = new LocationCreator();

    public PersonCreator() {
    }

    /**
     * Создаёт объект {@link Person} на основе переданных строковых значений.
     *
     * @param name    строковое представление имени
     * @param birth   строковое представление даты рождения
     * @param heightS строковое представление роста
     * @param xArg    строковое представление координаты X
     * @param yArg    строковое представление координаты Y
     * @param zArg    строковое представление координаты Z
     * @return объект {@code Person} или {@code null}, если аргументы отсутствуют
     * @throws InvalidValueException если значения не могут быть корректно преобразованы
     */
    public Person createPerson(String name, String birth, String heightS, String xArg, String yArg, String zArg) throws InvalidValueException {
        if (areStringsNull(name, birth, heightS, xArg, yArg, zArg)) {
            return null;
        } else {
            String personName = parseField(name, "name", String.class);
            LocalDateTime birthday = parseField(birth, "birthday", LocalDateTime.class);
            long height = parseField(heightS, "height", Long.class);
            Location location = creator.createLocation(xArg, yArg, zArg);
            return new Person(personName, birthday, height, location);
        }
    }

    /**
     * Создаёт объект {@link Person}, запрашивая необходимые данные у пользователя через консоль.
     * Предоставляет пользователю возможность отказаться от создания объекта.
     *
     * @return объект {@code Person} или {@code null}, если пользователь отказался от ввода
     * @throws InvalidValueException если введённые значения некорректны
     */
    public Person createPersonFromConsole() throws InvalidValueException {
        if (chooseOption("Хотите задать старосту?") == 2) {
            return null;
        } else {
            String personName = parseFieldFromConsole("Введите имя старосты", "name", String.class);
            int year = parseFieldFromConsole("Введите год рождения ", "year", Integer.class);
            int month = parseFieldFromConsole("Введите месяц  рождения ", "month", Integer.class);
            int day = parseFieldFromConsole("Введите день рождения ", "day", Integer.class);
            int hour = parseFieldFromConsole("Введите час рождения ", "hour", Integer.class);
            int minute = parseFieldFromConsole("Введите минуту рождения ", "minute", Integer.class);
            LocalDateTime birthday = LocalDateTime.of(year, month, day, hour, minute);
            long height = parseFieldFromConsole("Введите рост человека", "height", Long.class);
            Location location = creator.createLocationFromConsole();
            return new Person(personName, birthday, height, location);
        }
    }

}
