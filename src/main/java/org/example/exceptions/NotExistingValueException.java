package org.example.exceptions;

public class NotExistingValueException extends Exception{
    /**
     * Класс-исключение если такое значение id не существует в коллекции
     * @param message Сообщение об ошибке, объясняющее причину исключения.
     */
    public  NotExistingValueException(String message){
        super(message);
    }
}
