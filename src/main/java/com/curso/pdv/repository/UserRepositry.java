package com.curso.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.pdv.entity.User;

@Repository
public interface UserRepositry extends JpaRepository<User, Long>{
}