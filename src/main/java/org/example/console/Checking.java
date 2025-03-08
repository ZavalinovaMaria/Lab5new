package org.example.console;

import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NotUniqueValueException;

import java.util.ArrayList;


/**
 * Interface for checking the uniqueness of id in a collection.
 */
public interface Checking {


    /**
     * Checks the uniqueness of the id value.
     *
     * @param value The value of the id to check.
     * @return true if the id value is unique, false otherwise.
     * @throws NotUniqueValueException If the id value is not unique.
     */

    default boolean checkingUniqueness(int value, ArrayList<Integer> storage) throws NotUniqueValueException {
        if(storage.contains(value)) throw new NotUniqueValueException(String.format("Элемент с id со значением %s уже существует ", value));

         return true;
    }

    /**
     * Checks the existence of an element with the given id in the collection and removes it.
     *
     * @param value id to check for existence.
     * @throws NotExistingValueException If the element with the given id does not exist.
     */
    default boolean checkingExistence(Integer value,ArrayList<Integer> storage) throws NotExistingValueException {
        if (!storage.contains(value))
            throw new NotExistingValueException(String.format("Элемент с ключом со значением %s не содержится в коллекции ", value));
        return true;
    }

    /**
     * Adds a new id to the collection.
     *
     * @param id New id to add.
     */

    default boolean addNewId(int id,ArrayList<Integer> storage) {
        try {
            if (checkingUniqueness(id,storage)) {
                storage.add(id);
                return true;
            }
        } catch (NotUniqueValueException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    default boolean deleteId(int id,ArrayList<Integer> storage){
        try {
            if(checkingExistence(id,storage)){
                storage.remove(Integer.valueOf(id));
            return true;
        }
        }
        catch (NotExistingValueException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}

