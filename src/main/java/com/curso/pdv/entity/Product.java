package com.curso.pdv.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "O campo descrição é obrigarório")
	private String name;
	
	@Column(length = 20, precision = 20, scale = 2, nullable = false)
	@NotNull(message = "O campo preço é obrigarório")
	@Min(1)
	private BigDecimal price;
	
	@Column(nullable = false)
	@NotNull(message = "O campo quantidade é obrigarório")
	@Min(1)
	private int quantity;
}
