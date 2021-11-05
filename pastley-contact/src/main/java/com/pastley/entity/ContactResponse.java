package com.pastley.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @project Pastley-Contact.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */
@Entity
@Table(name = "contactResponse")
public class ContactResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "responser", nullable = false, length = 250)
	private String response;
	
	@Column(name = "date_register", nullable = false)
	private String dateRegister;
	
	@Column(name = "date_update", nullable = true)
	private String dateUpdate;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	ContactResponse(){
	}
	
	///////////////////////////////////////////////////////
	// Getter and Setter
	///////////////////////////////////////////////////////
	
	public Long getId() {
		return id;
	}

	public String getResponse() {
		return response;
	}

	public String getDateRegister() {
		return dateRegister;
	}

	public String getDateUpdate() {
		return dateUpdate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setDateRegister(String dateRegister) {
		this.dateRegister = dateRegister;
	}

	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
}
