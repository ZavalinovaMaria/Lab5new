package org.example.subjects.creators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.FormOfEducation;

import java.util.Scanner;

import static org.example.subjects.parsers.FieldParserUtil.parseField;

/**
 * Класс предназначен для создания объектов {@link FormOfEducation}
 * на основе пользовательского ввода из консоли или переданной строки.
 */
public class FormOfEducationCreator {
    /**
     * Запрашивает у пользователя форму обучения через консоль и возвращает соответствующее значение {@link FormOfEducation}.
     * Ожидает ввод числа от 1 до 3, соответствующего одному из вариантов.
     * В случае некорректного ввода предлагает повторить попытку.
     *
     * @return выбранная форма обучения в виде {@code FormOfEducation}
     */
    public FormOfEducation createFormOfEducationFromConsole() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Выберите номер формы обучения: ");
                System.out.println("1. DISTANCE_EDUCATION");
                System.out.println("2. FULL_TIME_EDUCATION");
                System.out.println("3. EVENING_CLASSES");

                String input = scanner.nextLine();
                int number = Integer.parseInt(input);

                if (number < 1 || number > 3) {
                    System.out.println("Число должно быть от 1 до 3. Попробуйте снова.");
                    continue;
                }

                FormOfEducation typeForm;
                typeForm = switch (number) {
                    case 1 -> FormOfEducation.DISTANCE_EDUCATION;
                    case 2 -> FormOfEducation.FULL_TIME_EDUCATION;
                    case 3 -> FormOfEducation.EVENING_CLASSES;
                    default -> null;
                };
                return typeForm;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 3.");
            }
        }
    }

    /**
     * Преобразует строковое значение в соответствующий элемент перечисления {@link FormOfEducation}.
     * Применяет парсинг строки с валидацией. В случае некорректного значения выбрасывает исключение.
     *
     * @param name строковое представление формы обучения
     * @return объект {@code FormOfEducation}, соответствующий переданному названию
     * @throws InvalidValueException если передано некорректное значение
     */
    public FormOfEducation createForm(String name) throws InvalidValueException {
        try {
            String typeString = parseField(name, "name", String.class);
            return typeString != null ? FormOfEducation.valueOf(typeString.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            throw new InvalidValueException(String.format("Некорректное значение для enum поля:%s", name), null);
        }
    }


}
