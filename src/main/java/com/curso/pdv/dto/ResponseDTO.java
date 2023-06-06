package com.curso.pdv.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ResponseDTO<T> {
    
    @Getter
    private List<String> messages;
    @Getter
    private T data;

    public ResponseDTO(List<String> messages, T data) {
        this.messages = messages;
        this.data = data;
    } 
    
    public ResponseDTO(String messages, T data) {
        this.messages = Arrays.asList(messages);
        this.data = data;
    }
    
}
