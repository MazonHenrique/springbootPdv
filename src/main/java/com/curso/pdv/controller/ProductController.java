package com.curso.pdv.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.pdv.dto.ResponseDTO;
import com.curso.pdv.entity.Product;
import com.curso.pdv.repository.ProductRepositry;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductRepositry productRepositry;

    @GetMapping
    public ResponseEntity<?>getAll(){
        return new ResponseEntity<>(productRepositry.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Product product){
        try{
            return new ResponseEntity<>(productRepositry.save(product),HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> put(@Valid @RequestBody Product product){
        Optional<Product> productToEdit = productRepositry.findById(product.getId());
        if(productToEdit.isPresent()){
            productRepositry.save(product);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            productRepositry.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO<>("Produto removido com sucesso"), HttpStatus.OK);
        }catch(Exception error){
            return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
