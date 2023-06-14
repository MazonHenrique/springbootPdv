package com.curso.pdv.exceptions;

public class NoItemException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoItemException(String message){
        super(message);
    }
}
