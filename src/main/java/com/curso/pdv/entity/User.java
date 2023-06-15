package com.curso.pdv.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "Campo nome é obrigatório")
	private String name;

	@Column(length = 30, nullable = false)
	@NotBlank(message = "O campo Username é obrigatorio")
	private String username;
	
	@Column(length = 30, nullable = false)
	@NotBlank(message = "O campo Senha é obrigatorio")
	private String password;

	private boolean isEnable;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Sale> sales;
	
	
}
