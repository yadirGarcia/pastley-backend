package com.pastley.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @project Pastley-Sale.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {
	 
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name",nullable = false)
	private String name;

	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "description",nullable = false)
	private String desciption;
	
	@Column(name = "address",nullable = false)
	private String address;
	
	@Column(name = "mision",nullable = false)
	private String mision;
	
	@Column(name = "vision",nullable = false)
	private String vision;
	
	@Column(name = "who",nullable = false)
	private String who;
	
	@Column(name = "size",nullable = false, length = 10)
	private Integer size;
	
	@Column(name = "statu", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean statu;
	
	@Column(name = "butdget",nullable = false)
	private Float butdget;
	
	@Column(name = "logo",nullable = false)
	private Long logo;
	
	@Column(name = "send_sale",nullable = false)
	private String sendSale;
	
	@Column(name = "alert_stock",nullable = false, length = 10)
	private Integer alertStock;
	
	@Column(name = "alert_min_stock",nullable = false, length = 10)
	private Integer alertMinStock;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	
	Company(){
		
	}
	///////////////////////////////////////////////////////
	// Getter and Setter
	///////////////////////////////////////////////////////

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getDesciption() {
		return desciption;
	}

	public String getAddress() {
		return address;
	}

	public String getMision() {
		return mision;
	}

	public String getVision() {
		return vision;
	}

	public String getWho() {
		return who;
	}

	public Integer getSize() {
		return size;
	}

	public boolean isStatu() {
		return statu;
	}

	public Float getButdget() {
		return butdget;
	}

	public Long getLogo() {
		return logo;
	}

	public String getSendSale() {
		return sendSale;
	}

	public Integer getAlertStock() {
		return alertStock;
	}

	public Integer getAlertMinStock() {
		return alertMinStock;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMision(String mision) {
		this.mision = mision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setStatu(boolean statu) {
		this.statu = statu;
	}

	public void setButdget(Float butdget) {
		this.butdget = butdget;
	}

	public void setLogo(Long logo) {
		this.logo = logo;
	}

	public void setSendSale(String sendSale) {
		this.sendSale = sendSale;
	}

	public void setAlertStock(Integer alertStock) {
		this.alertStock = alertStock;
	}

	public void setAlertMinStock(Integer alertMinStock) {
		this.alertMinStock = alertMinStock;
	}
	
	 
	
	
}
