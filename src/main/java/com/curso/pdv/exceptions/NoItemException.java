package com.curso.pdv.exceptions;

public class NoItemException extends RuntimeException{
    public NoItemException(String message){
        super(message);
    }
}