package com.pastley.model;

import java.io.Serializable;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class PersonModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long document;
	private String name;
	private String subname;
	private String phone;
	private String email;
	private String address;
	private String dateBirthday;
	private Long idTypeDocument;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public PersonModel() {
		this(0L, 0L, null, null, null, null, null, null, 0L);
	}

	public PersonModel(Long document, String name, String subname, String phone, String email, String address,
			String dateBirthday, Long idTypeDocument) {
		this(0L, document, name, subname, phone, email, address, dateBirthday, idTypeDocument);
	}

	public PersonModel(Long id, Long document, String name, String subname, String phone, String email, String address,
			String dateBirthday, Long idTypeDocument) {
		this.id = id;
		this.document = document;
		this.name = name;
		this.subname = subname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.dateBirthday = dateBirthday;
		this.idTypeDocument = idTypeDocument;
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

	public Long getIdTypeDocument() {
		return idTypeDocument;
	}

	public void setIdTypeDocument(Long idTypeDocument) {
		this.idTypeDocument = idTypeDocument;
	}
}
