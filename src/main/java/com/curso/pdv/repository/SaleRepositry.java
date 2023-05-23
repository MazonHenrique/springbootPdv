package com.curso.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.pdv.entity.Sale;

@Repository
public interface SaleRepositry extends JpaRepository<Sale, Long>{
}