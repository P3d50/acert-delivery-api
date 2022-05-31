package com.delivery.api.exceptions;

public class InvalidPassWordException extends RuntimeException {
    public InvalidPassWordException(){
        super("Invalid password");
    }
}
