package com.curso.pdv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.pdv.dto.ResponseDTO;
import com.curso.pdv.dto.SaleDTO;
import com.curso.pdv.exceptions.InvalidOperationException;
import com.curso.pdv.exceptions.NoItemException;
import com.curso.pdv.service.SaleService;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(saleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        try{
            return new ResponseEntity<>(saleService.getById(id), HttpStatus.OK);
        }catch(NoItemException | InvalidOperationException error){
            return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()), HttpStatus.BAD_REQUEST);    
        }
    }
   
    @PostMapping
    public ResponseEntity<?> post(@RequestBody SaleDTO saleDTO){
        try{
            long id = saleService.save(saleDTO);
            return new ResponseEntity<>(new ResponseDTO<>("Venda realizada com sucesso: " + id), HttpStatus.CREATED);
        }catch(NoItemException | InvalidOperationException error){
            return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()), HttpStatus.BAD_REQUEST);
        }catch(Exception error){
            return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }    
    }
}
