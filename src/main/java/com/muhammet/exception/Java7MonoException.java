package com.muhammet.exception;

import lombok.Getter;

@Getter
public class Java7MonoException extends RuntimeException{
    private final ErrorType errorType;
    public Java7MonoException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }

    public Java7MonoException(ErrorType errorType,String message){
        super(message);
        this.errorType=errorType;
    }
}
