package com.curso.pdv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.curso.pdv.dto.ResponseDTO;
import com.curso.pdv.exceptions.InvalidOperationException;
import com.curso.pdv.exceptions.NoItemException;

@RestControllerAdvice
public class ApplicationAdviceController {
    
    @ExceptionHandler(NoItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handleNoItemException(NoItemException ex){
        String messageError = ex.getMessage();
        return new ResponseDTO<>(messageError);
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handleInvalidOperationException(InvalidOperationException ex){
        String messageError = ex.getMessage();
        return new ResponseDTO<>(messageError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> erros = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String erroMessage = error.getDefaultMessage();
            erros.add(erroMessage);
        });

        return new ResponseDTO<>(erros);
        
    }
}
