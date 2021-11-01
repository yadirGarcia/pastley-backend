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

import com.pastley.util.PastleyValidate;

/**
 * @project Pastley-Sale.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */
@Entity
@Table(name = "typePQR")
public class TypePQR implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "statu", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean statu;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register", nullable = false)
	private Date dateRegister;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update", nullable = true, columnDefinition = "datetime default null")
	private Date dateUpdate;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////

	TypePQR() {

	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Metodo que valida los atributos de la clase.
	 * 
	 * @param isId, Representa si se desea validar el id.
	 * @return el error ocurrido.
	 */
	public String validate(boolean isId) {
		String chain = null;
		if (isId) {
			if (id <= 0) {
				chain = "El id del PQR debe ser mayor a cero.";
			}
		}
		if (chain == null && !PastleyValidate.isChain(name)) {
			chain = "El nombre del PQR no es valido.";
		}
		return chain;
	}

	public void uppercase() {
		this.name = PastleyValidate.uppercase(this.name);
	}
	///////////////////////////////////////////////////////
	// Getter and Setter
	/////////////////////////////////////////////////////

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescripcion() {
		return descripcion;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
