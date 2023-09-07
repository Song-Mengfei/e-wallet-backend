package com.ewallet.springbootewallet.Exceptions;

public class InsufficientAuthenticationException extends RuntimeException{
    public InsufficientAuthenticationException() {
        super("Incorrect password");
    }
}
