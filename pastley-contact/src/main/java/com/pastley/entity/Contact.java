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

	@Column(name = "statu",  nullable = false)
	private boolean statu;

	
	@Column(name = "date_register", nullable = false)
	private String dateRegister;

	
	@Column(name = "date_update",nullable = true)
	private String dateUpdate;
	
	@Column(name = "id_usuario",nullable = false)
	private Long idUsuario;
	
	@Column(name = "id_pqr",nullable = false)
	private Long idTypePQR;

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

	public String getDateRegister() {
		return dateRegister;
	}

	public String getDateUpdate() {
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

	public void setDateRegister(String dateRegister) {
		this.dateRegister = dateRegister;
	}

	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdTypePQR() {
		return idTypePQR;
	}

	public void setIdTypePQR(Long idTypePQR) {
		this.idTypePQR = idTypePQR;
	}
	
	

}
