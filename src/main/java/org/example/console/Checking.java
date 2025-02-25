package org.example.console;

import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NotUniqueValueException;

import static org.example.console.Console.idStoragee;

public interface Checking {
    default boolean  checkingUniqueness(int value) throws NotUniqueValueException {
        if(idStoragee.contains(value)) throw new NotUniqueValueException(String.format("Элемент с id со значением %s уже существует ", value));

        else return true;
    }
    default boolean checkingExistence(Integer value) throws NotExistingValueException {
        if (!idStoragee.contains(value))
            throw new NotExistingValueException(String.format("Элемент с ключом со значением %s не содержится в коллекции ", value));
        else {
            return true;
        }
    }

    default boolean  addNewId(int id) {
        try {
            if (checkingUniqueness(id)) {
                idStoragee.add(id);
                return true;
            }
        } catch (NotUniqueValueException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    default boolean deleteId(int id){
        try {
            if(checkingExistence(id)){
            idStoragee.remove(Integer.valueOf(id));
            return true;
        }
        }
        catch (NotExistingValueException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}

