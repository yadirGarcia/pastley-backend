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
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mail", nullable = false, length = 50)
	private String mail;

	@Column(name = "points")
	private Long points;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "ip")
	private String ip;

	@Column(name = "last_password", length = 50)
	private String lastPassword;

	@Column(name = "statu", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean statu;

	@Column(name = "session", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean session;

	@Column(name = "date_register", nullable = false)
	private String dateRegister;

	@Column(name = "date_update", nullable = true)
	private String dateUpdate;

	@Column(name = "date_last_date", nullable = false)
	private String dateLastDate;

	@Column(name = "date_session", nullable = false)
	private String dateSession;

	@Column(name = "id_role", nullable = false)
	private Long idRole;

	@Column(name = "id_person", nullable = false)
	private Long idPerson;

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
		if (!PastleyValidate.isChain(mail)) {
			chain = "El nombre del rol no es valido.";
		}
		return chain;
	}
	/**
	 * Convert variables to uppercase.
	 */
	public void uppercase() {
		this.mail = PastleyValidate.uppercase(this.mail);
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLastPassword() {
		return lastPassword;
	}

	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
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

	public String getDateLastDate() {
		return dateLastDate;
	}

	public void setDateLastDate(String dateLastDate) {
		this.dateLastDate = dateLastDate;
	}

	public String getDateSession() {
		return dateSession;
	}

	public void setDateSession(String dateSession) {
		this.dateSession = dateSession;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}
}
