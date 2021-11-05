package com.pastley.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pastley.security.enums.RoleEnum;

import lombok.Data;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Enumerated(EnumType.STRING)
	@Column(name = "name", nullable = false, length = 50)
	private RoleEnum name;

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
	public Role(Long id){
		this.id = id;
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
		if (name != null) {
			chain = "El nombre del rol no es valido.";
		}
		return chain;
	}
}
