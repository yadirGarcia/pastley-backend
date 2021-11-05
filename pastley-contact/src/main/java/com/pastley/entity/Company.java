package com.pastley.entity;

import java.io.Serializable;
import java.math.BigInteger;

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

	@Column(name = "name", unique = true, nullable = false, length = 100)
	private String name;

	@Column(name = "email", unique = true, nullable = false, length = 300)
	private String email;

	@Column(name = "password", nullable = false, length = 500)
	private String password;

	@Column(name = "address", nullable = false, length = 200)
	private String address;
	
	@Column(name = "description", nullable = true, length = 500)
	private String desciption;

	@Column(name = "mission", nullable = true, length = 500)
	private String mission;

	@Column(name = "vision", nullable = true, length = 500)
	private String vision;

	@Column(name = "about_us", nullable = true, length = 500)
	private String aboutUs;

	@Column(name = "size", nullable = false, length = 10)
	private Integer size;

	@Column(name = "butdget", nullable = false)
	private BigInteger butdget;

	@Column(name = "logo", nullable = true, length = 500)
	private String logo;
	
	@Column(name = "statu", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean statu;

	@Column(name = "send_sales_mail", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean sendSalesMail;

	@Column(name = "alert_stock", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean alertStock;

	@Column(name = "alert_min_stock", nullable = false, columnDefinition = "tinyint(1) default 1")
	private Integer alertMinStock;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Company() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public BigInteger getButdget() {
		return butdget;
	}

	public void setButdget(BigInteger butdget) {
		this.butdget = butdget;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isStatu() {
		return statu;
	}

	public void setStatu(boolean statu) {
		this.statu = statu;
	}

	public boolean isSendSalesMail() {
		return sendSalesMail;
	}

	public void setSendSalesMail(boolean sendSalesMail) {
		this.sendSalesMail = sendSalesMail;
	}

	public boolean isAlertStock() {
		return alertStock;
	}

	public void setAlertStock(boolean alertStock) {
		this.alertStock = alertStock;
	}

	public Integer getAlertMinStock() {
		return alertMinStock;
	}

	public void setAlertMinStock(Integer alertMinStock) {
		this.alertMinStock = alertMinStock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
