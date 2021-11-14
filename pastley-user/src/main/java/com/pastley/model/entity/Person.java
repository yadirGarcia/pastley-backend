package com.pastley.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pastley.util.PastleyValidate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
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
		if (!PastleyValidate.isChain(subname)) {
			chain = "El apellido de la persona no es valido.";
		}
		if (!PastleyValidate.isChain(phone)) {
			chain = "El telefono de la persona no es valido.";
		}
		if (!PastleyValidate.isChain(email)) {
			chain = "El email de la persona no es valido.";
		}
		if (!PastleyValidate.isEmail(email)) {
			chain = "El email de la persona no cumple el formato de un email.";
		}
		if (document <= 0) {
			chain = "El documento de la persona no es valido.";
		}
		if (idTypeDocument <= 0) {
			chain = "El tipo de documento de la persona no es valido.";
		}
		return chain;
	}
	

	/**
	 * Convert variables to uppercase.
	 */
	public void uppercase() {
		this.name = PastleyValidate.uppercase(this.name);
		this.subname = PastleyValidate.uppercase(this.subname);
	}
}
