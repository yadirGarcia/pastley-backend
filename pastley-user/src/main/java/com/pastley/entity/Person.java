package com.pastley.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Entity
@Table(name="person")
public class Person implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="document",nullable = false)
	private Long document;
	
	@Column(name="name",nullable = false,length = 50)
	private String name;
	
	//agregar los que faltan
	
	@Column(name="date_birthday",nullable = true)
	private Date dateBirthday;
	
	
	// mas
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_register",nullable = false)
	private Date dateRegister;

	
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	Person(){
		
	}



	///////////////////////////////////////////////////////
	// Getter and Setter
	///////////////////////////////////////////////////////
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getDocument() {
		return document;
	}

	public void setDocument(Long document) {
		this.document = document;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateBirthday() {
		return dateBirthday;
	}




	public void setDateBirthday(Date dateBirthday) {
		this.dateBirthday = dateBirthday;
	}




	public Date getDateRegister() {
		return dateRegister;
	}




	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}
	
	
}
