package org.example.exceptions;

public class InvalidValueException extends Exception {
    /**
     * Исключение, которое выбрасывается при парсинге поля если обнаружено недопустимое значение.
     *
     * @param message Сообщение об ошибке, объясняющее причину исключения.
     * @param cause   Причина возникновения исключения.
     */
    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }
}

