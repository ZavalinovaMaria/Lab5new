package org.example.utils;

import org.example.exceptions.*;

import java.util.ArrayList;

/**
 * Интерфейс для проверки уникальности и существования идентификаторов, а также их добавления и удаления из хранилища.
 */
public interface CheckingId {
    /**
     * Проверяет, является ли переданный идентификатор уникальным в указанном хранилище.
     *
     * @param value   идентификатор, который необходимо проверить.
     * @param storage список идентификаторов, среди которых выполняется проверка.
     * @throws NotUniqueValueException если идентификатор уже существует в хранилище.
     */
    default boolean checkingUniquenessOfId(int value, ArrayList<Integer> storage) throws NotUniqueValueException {
        if (storage.contains(value))
            throw new NotUniqueValueException(String.format("Элемент с id со значением %s уже существует ", value));
        return true;
    }

    /**
     * Проверяет, существует ли переданный идентификатор в указанном хранилище.
     *
     * @param value   идентификатор, который необходимо проверить.
     * @param storage список идентификаторов, среди которых выполняется проверка.
     * @throws NotExistingValueException если идентификатор уже существует в хранилище.
     */
    default boolean checkingExistenceOfId(Integer value, ArrayList<Integer> storage) throws NotExistingValueException {
        if (!storage.contains(value))
            throw new NotExistingValueException(String.format("Элемент с id со значением %s не содержится в коллекции ", value));
        return true;
    }

    /**
     * Добавляет новый идентификатор в хранилище после проверки его уникальности.
     *
     * @param id      идентификатор, который необходимо добавить.
     * @param storage список идентификаторов, в который добавляется новый id.
     * @return {@code true}, если идентификатор успешно добавлен, иначе {@code false}.
     */
    default boolean addNewId(int id, ArrayList<Integer> storage) {
        try {
            if (checkingUniquenessOfId(id, storage)) {
                storage.add(id);
                return true;
            }
        } catch (NotUniqueValueException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Удаляет идентификатор из хранилища после проверки его существования.
     *
     * @param id      идентификатор, который необходимо удалить.
     * @param storage список идентификаторов, из которого удаляется id.
     * @return {@code true}, если идентификатор успешно удален, иначе {@code false}.
     */
    default boolean deleteId(int id, ArrayList<Integer> storage) {
        try {
            if (checkingExistenceOfId(id, storage)) {
                storage.remove(Integer.valueOf(id));
                return true;
            }
        } catch (NotExistingValueException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

