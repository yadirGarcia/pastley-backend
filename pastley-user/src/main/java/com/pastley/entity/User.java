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
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */

@Entity
@Table(name="user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="mail", nullable = false, length = 50)
	private String mail;
	
	@Column(name="points")
	private Long points;
	
	@Column(name="password", nullable = false,length = 50)
	private String password;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="last_password", length = 50)
	private String lastPassword;
	
	@Column(name = "statu", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean statu;
	
	@Column(name = "session", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean session;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_register",nullable = false)
	private Date dateRegister;
	
	@Column(name = "date_update", nullable = true, columnDefinition = "datetime default null")
	private Date dateUpdate;
	
	@Column(name="date_last_date",nullable = false)
	private Date dateLastDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_session",nullable = false)
	private Date dateSession;
	
	@Column(name="id_role", nullable = false)
	private Long idRole;
	
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	
	User(){
			
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

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Date getDateLastDate() {
		return dateLastDate;
	}

	public void setDateLastDate(Date dateLastDate) {
		this.dateLastDate = dateLastDate;
	}

	public Date getDateSession() {
		return dateSession;
	}

	public void setDateSession(Date dateSession) {
		this.dateSession = dateSession;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
