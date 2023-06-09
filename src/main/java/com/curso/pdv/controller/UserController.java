package com.curso.pdv.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.curso.pdv.entity.User;
import com.curso.pdv.repository.UserRepositry;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserRepositry userRepositry;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(userRepositry.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody User user){
        try{
            user.setEnable(true);
            return new ResponseEntity<>(userRepositry.save(user),HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> put(@Valid @RequestBody User user){
        Optional<User> userToEdit = userRepositry.findById(user.getId());
        if(userToEdit.isPresent()){
            try{
                userRepositry.save(user);
                return new ResponseEntity<>(user,HttpStatus.OK);
            }catch(Exception error){
                return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new ResponseDTO<>("Usuário nao encontrado!"),HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        Optional<User> userToEdit = userRepositry.findById(id);
        if(userToEdit.isPresent()){
            try{
                userRepositry.deleteById(id);
                return new ResponseEntity<>(new ResponseDTO<>("Usuário removido com sucesso"), HttpStatus.OK);
            }catch(EmptyResultDataAccessException error){
                return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }catch(Exception error){
                return new ResponseEntity<>(new ResponseDTO<>(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new ResponseDTO<>("Usuário nao encontrado!"),HttpStatus.NOT_FOUND);    
    }
}
