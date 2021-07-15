package com.finconsgroup.appostman.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BILLIONAIRES") // only when the table and class haven't the same name
public class Bilionaire { 

	@Id
	@GeneratedValue
	private Long id; 
	
	@Column(name="first_name")
	private String nome;
	
	private String lastName;
	
	private String career;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the career
	 */
	public String getCareer() {
		return career;
	}

	/**
	 * @param career the career to set
	 */
	public void setCareer(String career) {
		this.career = career;
	}
	
	
}
