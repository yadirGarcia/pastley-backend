package com.pastley.model;

import java.io.Serializable;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class RoleModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public RoleModel() {
		this(0L, null);
	}
	
	public RoleModel(String name) {
		this(0L, name);
	}
	
	public RoleModel(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	///////////////////////////////////////////////////////
	// Getter and Setters
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
