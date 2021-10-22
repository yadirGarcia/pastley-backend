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
	
	@Column(name="subname", nullable = false, length = 50)
	private String subname;
	
	@Column(name="phone", nullable = false)
	private String phone;
	
	@Column(name="email", nullable = false, length = 50)
	private String email;
	
	@Column(name="address", length = 50)
	private String address;
	
	@Column(name="date_birthday",nullable = true)
	private Date dateBirthday;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_register",nullable = false)
	private Date dateRegister;
	
	@Column(name = "date_update", nullable = true, columnDefinition = "datetime default null")
	private Date dateUpdate;
	
	
	
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

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
