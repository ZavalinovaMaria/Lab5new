package org.example.subjects.creators;

import org.example.exceptions.InvalidValueException;
import org.example.subjects.*;

import java.time.ZonedDateTime;

import static org.example.subjects.parsers.FieldParserUtil.parseField;
import static org.example.subjects.parsers.FieldParserUtil.parseFieldFromConsole;

/**
 * Класс предназначен для создания объектов {@link StudyGroup}.
 * Поддерживает создание групп на основе массива строковых значений или через пользовательский ввод в консоли.
 */
public class StudyGroupsCreator {
    PersonCreator personCreator;
    FormOfEducationCreator formOfEducationCreator;
    SemesterCreator semesterCreator;

    public StudyGroupsCreator() {
        this.personCreator = new PersonCreator();
        this.formOfEducationCreator = new FormOfEducationCreator();
        this.semesterCreator = new SemesterCreator();
    }

    /**
     * Безопасно получает значение из массива по указанному индексу.
     *
     * @param array массив строковых значений
     * @param index индекс элемента
     * @return строковое значение или {@code null}, если индекс выходит за границы массива
     */
    private String getSafeValue(String[] array, int index) {
        return (index >= 0 && index < array.length) ? array[index] : null;
    }

    /**
     * Создаёт объект {@link StudyGroup} на основе переданного массива строковых значений.
     * Если флаг {@code isScriptWorking} установлен в {@code true}, id группы будет установлен в -1.
     *
     * @param builder         массив строковых значений, содержащий данные для создания группы
     * @param isScriptWorking флаг, указывающий, используется ли скриптовый режим
     * @return объект {@code StudyGroup}
     * @throws InvalidValueException если одно из значений не проходит валидацию
     */
    public StudyGroup createGroup(String[] builder, Boolean isScriptWorking) throws InvalidValueException {
        StudyGroup group = new StudyGroup();
        int startIndex = 0;
        if (isScriptWorking) {
            startIndex -= 1;
            group.setId(-1);
        } else {
            group.setId(parseField(builder[startIndex], "id", Integer.class));
        }
        fillGroup(group, builder, startIndex);
        return group;
    }

    /**
     * Заполняет объект {@link StudyGroup} данными из массива.
     *
     * @param group      объект {@code StudyGroup}, который требуется заполнить
     * @param builder    массив строковых значений с данными
     * @param startIndex начальный индекс в массиве {@code builder}
     * @throws InvalidValueException если одно из значений не проходит валидацию
     */
    public void fillGroup(StudyGroup group, String[] builder, int startIndex) throws InvalidValueException {
        group.setName(parseField(builder[startIndex + 1], "name", String.class));
        group.setCoordinates(new Coordinates(parseField(builder[startIndex + 2], "x", Double.class), parseField(builder[startIndex + 3], "y", Integer.class)));
        group.setCreationDate(parseField(builder[startIndex + 4], "creationDate", ZonedDateTime.class));
        group.setStudentsCount(parseField(builder[startIndex + 5], "studentsCount", Integer.class));
        group.setTransferredStudents(parseField(builder[startIndex + 6], "transferredStudents", Integer.class));
        group.setFormOfEducation(formOfEducationCreator.createForm(builder[startIndex + 7]));
        group.setSemesterEnum(semesterCreator.createSemester(getSafeValue(builder, startIndex + 8)));
        group.setGroupAdmin(personCreator.createPerson(getSafeValue(builder, startIndex + 9), getSafeValue(builder, startIndex + 10), getSafeValue(builder, startIndex + 11), getSafeValue(builder, startIndex + 12), getSafeValue(builder, startIndex + 13), getSafeValue(builder, startIndex + 14)));
    }

    /**
     * Создаёт объект {@link StudyGroup} через консольный ввод пользователя.
     *
     * @return объект {@code StudyGroup}
     */
    public StudyGroup createGroupFromConsole() {
        StudyGroup group = new StudyGroup();
        fillGroupFromConsole(group);
        return group;
    }

    /**
     * Заполняет объект {@link StudyGroup} данными, введёнными пользователем в консоли.
     *
     * @param group объект {@code StudyGroup}, который требуется заполнить
     */
    public void fillGroupFromConsole(StudyGroup group) {
        try {
            group.setName(parseFieldFromConsole("Введите имя", "name", String.class));
            group.setCoordinates(new Coordinates(
                    parseFieldFromConsole("Введите x", "x", Double.class),
                    parseFieldFromConsole("Введите y", "y", Integer.class)
            ));
            group.setCreationDate(ZonedDateTime.now());
            group.setStudentsCount(parseFieldFromConsole("Введите количество студентов", "studentsCount", Integer.class));
            group.setTransferredStudents(parseFieldFromConsole("Введите количество переведенных студентов", "transferredStudents", Integer.class));
            group.setFormOfEducation(formOfEducationCreator.createFormOfEducationFromConsole());
            group.setSemesterEnum(semesterCreator.createSemesterFromConsole());
            group.setGroupAdmin(personCreator.createPersonFromConsole());

        } catch (InvalidValueException e) {
            System.out.println(e.getMessage() + ", продукт не будет собран");
        }
    }


}
