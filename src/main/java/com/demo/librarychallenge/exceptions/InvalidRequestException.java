package com.demo.librarychallenge.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String msg){
        super(msg);
    }
}
