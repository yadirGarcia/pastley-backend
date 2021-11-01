package com.pastley.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pastley.util.PastleyValidate;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */

@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "description", nullable = true, length = 140)
	private String description;

	@Column(name = "statu", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean statu;

	@Column(name = "session", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean session;
	
	@Column(name="date_register", nullable = false)
	private String dateRegister;
	
	@Column(name="date_update", nullable = true)
	private String dateUpdate;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////

	public Role() {
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	
	/**
	 * Method that validates the attributes of the class.
	 * 
	 * @param isId, Represents if you want to validate the id.
	 * @return The error occurred.
	 */
	public String validate(boolean isId) {
		String chain = null;
		if (isId) {
			if (id <= 0) {
				chain = "El id del rol debe ser mayor a cero.";
			}
		}
		if (!PastleyValidate.isChain(name)) {
			chain = "El nombre del rol no es valido.";
		}
		return chain;
	}

	/**
	 * Convert variables to uppercase.
	 */
	public void uppercase() {
		this.name = PastleyValidate.uppercase(this.name);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public String getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(String dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatu() {
		return statu;
	}

	public void setStatu(boolean statu) {
		this.statu = statu;
	}

	public boolean isSession() {
		return session;
	}

	public void setSession(boolean session) {
		this.session = session;
	}
}
