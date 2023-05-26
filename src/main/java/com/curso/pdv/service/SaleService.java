package com.curso.pdv.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.pdv.entity.Sale;
import com.curso.pdv.entity.User;
import com.curso.pdv.repository.UserRepositry;

@Service
public class SaleService {
    
    @Autowired
    private UserRepositry userRepositry;
    /* 
    public Long save(){

        User user = new User();

        Sale newSale = new Sale();
        newSale.setDate(LocalDate.now());
    }*/
    
}
