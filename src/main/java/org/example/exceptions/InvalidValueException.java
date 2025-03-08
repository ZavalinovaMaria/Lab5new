package org.example.exceptions;

public class InvalidValueException extends Exception {
    /**
     * The class of exception that is thrown when a null value is encountered.
     * @param message An error message explaining the reason for the exception.
     * @param cause The reason for the exception.
     */
    public InvalidValueException(String message,Throwable cause)  {
        super(message,cause);
    }
}

