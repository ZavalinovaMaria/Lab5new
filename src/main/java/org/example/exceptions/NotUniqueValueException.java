package org.example.exceptions;

public class NotUniqueValueException extends Exception {
    /**
     * Класс-исключение при не уникальном значении id
     *
     * @param message Сообщение об ошибке, объясняющее причину исключения.
     */
    public NotUniqueValueException(String message) {
        super(message);
    }
}
