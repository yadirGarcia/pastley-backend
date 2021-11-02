package com.pastley.model;

import java.io.Serializable;


/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long points;
	private String ip;
	private boolean statu;
	private boolean session;
	private PersonModel person;
	private RoleModel role;
	
	public UserModel() {
		this(0L, 0L, null, false, false, new PersonModel(), new RoleModel());
	}
	
	public UserModel(String ip, boolean statu, boolean session, PersonModel person,
			RoleModel role) {
		this(0L, 0L, ip, statu, session, person, role);
	}
	
	public UserModel(Long id, Long points, String ip, boolean statu, boolean session, PersonModel person,
			RoleModel role) {
		this.id = id;
		this.points = points;
		this.ip = ip;
		this.statu = statu;
		this.session = session;
		this.person = person;
		this.role = role;
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
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public PersonModel getPerson() {
		return person;
	}
	public void setPerson(PersonModel person) {
		this.person = person;
	}
	public RoleModel getRole() {
		return role;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
