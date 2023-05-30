package com.curso.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.pdv.entity.Product;

public interface ProductRepositry extends JpaRepository<Product, Long>{
}