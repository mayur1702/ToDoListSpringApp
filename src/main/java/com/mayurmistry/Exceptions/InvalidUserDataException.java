package com.mayurmistry.Exceptions;

public class InvalidUserDataException extends Exception {
    public InvalidUserDataException(String errorMessage) {
        super(errorMessage);
    }
}
