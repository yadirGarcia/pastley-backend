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
 * @project Pastley-Sale.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "statu", nullable = false)
	private boolean statu;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register", nullable = false)
	private Date dateRegister;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update", nullable = false)
	private Date dateUpdate;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	
	Contact() {

	}
	
	///////////////////////////////////////////////////////
	// Getter and Setter
	///////////////////////////////////////////////////////
		
	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public boolean isStatu() {
		return statu;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatu(boolean statu) {
		this.statu = statu;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

}
