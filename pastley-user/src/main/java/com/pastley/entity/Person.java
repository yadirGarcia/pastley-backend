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
@Table(name = "person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "document", nullable = false)
	private Long document;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "subname", nullable = false, length = 50)
	private String subname;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "address", length = 50, nullable = true)
	private String address;

	@Column(name = "date_birthday", nullable = true)
	private String dateBirthday;

	@Column(name = "date_register", nullable = false)
	private String dateRegister;

	@Column(name = "date_update", nullable = true)
	private String dateUpdate;

	@Column(name = "id_type_document", nullable = false)
	private Long idTypeDocument;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	Person() {

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
				chain = "El id de la persona debe ser mayor a cero.";
			}
		}
		if (!PastleyValidate.isChain(name)) {
			chain = "El nombre de la persona no es valido.";
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

	public String getDateBirthday() {
		return dateBirthday;
	}

	public void setDateBirthday(String dateBirthday) {
		this.dateBirthday = dateBirthday;
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

	public Long getIdTypeDocument() {
		return idTypeDocument;
	}

	public void setIdTypeDocument(Long idTypeDocument) {
		this.idTypeDocument = idTypeDocument;
	}

}
