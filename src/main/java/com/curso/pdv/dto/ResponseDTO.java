package com.curso.pdv.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ResponseDTO<T> {
    
    @Getter
    private List<String> messages;

    public ResponseDTO(List<String> messages) {
        this.messages = messages;
    } 
    
    public ResponseDTO(String messages) {
        this.messages = Arrays.asList(messages);
    }
    
}
