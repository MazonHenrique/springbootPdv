package com.curso.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.pdv.entity.Product;

@Repository
public interface ProductRepositry extends JpaRepository<Product, Long>{
}