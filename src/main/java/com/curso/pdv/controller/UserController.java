package com.curso.pdv.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.pdv.entity.User;
import com.curso.pdv.repository.UserRepositry;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserRepositry userRepositry;

    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity<>(userRepositry.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody User user){
        try{
            user.setEnable(true);
            return new ResponseEntity<>(userRepositry.save(user),HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity put(@RequestBody User user){
        Optional<User> userToEdit = userRepositry.findById(user.getId());
        if(userToEdit.isPresent()){
            userRepositry.save(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        
        return ResponseEntity.notFound().build();
    }
}
