package com.curso.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.pdv.entity.User;

public interface UserRepositry extends JpaRepository<User, Long>{
}