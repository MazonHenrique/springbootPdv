package com.curso.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.pdv.entity.Sale;

public interface SaleRepositry extends JpaRepository<Sale, Long>{
}