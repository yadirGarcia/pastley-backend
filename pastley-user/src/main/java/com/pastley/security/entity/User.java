package com.pastley.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pastley.entity.Person;
import com.pastley.util.PastleyValidate;
import com.sun.istack.NotNull;

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
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nickname", nullable = false, unique = true, length = 100)
	private String nickname;

	@Column(name = "points")
	private Long points;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "ip", nullable = true)
	private String ip;

	@Column(name = "last_password", nullable = true, length = 50)
	private String lastPassword;

	@Column(name = "statu", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean statu;

	@Column(name = "session", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean session;

	@Column(name = "date_register", nullable = false)
	private String dateRegister;

	@Column(name = "date_update", nullable = true)
	private String dateUpdate;

	@Column(name = "date_last_date", nullable = true)
	private String dateLastDate;

	@Column(name = "date_session", nullable = true)
	private String dateSession;
	
	///////////////////////////////////////////////////////
	// Relation
	///////////////////////////////////////////////////////

	@ManyToOne()
	@JoinColumn(name = "id_person", nullable = false)
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "id_role", nullable = false)
	private Role role;
	
	@NotNull
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public User() {
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
		if (!PastleyValidate.isChain(password)) {
			chain = "La clave no es valida.";
		}
		if(person == null) {
			chain = "No se ha recibido la persona.";
		}
		return chain;
	}
}
