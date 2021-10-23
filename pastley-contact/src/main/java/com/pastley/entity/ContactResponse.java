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
@Table(name = "contactResponse")
public class ContactResponse implements Serializable {
	
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "responser", nullable = false)
	private String response;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register", nullable = false)
	private Date dateRegister;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update", nullable = false)
	private Date dateUpdate;
	
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

	public Date getDateRegister() {
		return dateRegister;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	
	

}
