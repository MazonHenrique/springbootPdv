package com.curso.pdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.pdv.entity.User;

@SpringBootApplication
public class PdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdvApplication.class, args);
		
		User user = new User();	
	}

}
