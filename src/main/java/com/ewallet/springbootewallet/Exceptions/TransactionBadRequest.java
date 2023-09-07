package com.ewallet.springbootewallet.Exceptions;

public class TransactionBadRequest extends RuntimeException{
    public TransactionBadRequest() {
        super("Transaction bad request");
    }
}
