package org.example.exceptions;

public class NullValueException extends Exception {
    /**
     * Исключение, которое выбрасывается при обнаружении значения null.
     *
     * @param message Сообщение об ошибке, объясняющее причину исключения.
     * @param cause   Причина возникновения исключения.
     */
    public NullValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
