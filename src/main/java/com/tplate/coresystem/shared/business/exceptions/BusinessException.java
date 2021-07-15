package com.tplate.coresystem.shared.business.exceptions;


// Goal: represents an exception that it is not handled by the API.
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }

}
