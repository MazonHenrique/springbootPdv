package com.curso.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.pdv.entity.ItemSale;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Long>{
}