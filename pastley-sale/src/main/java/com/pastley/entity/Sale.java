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
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Entity
@Table(name="sale")
public class Sale implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_coustomer", nullable = false)
	private Long idCoustomer;
	
	@Column(name = "id_method_pay", nullable = false)
	private Long idMethodPay;
	
	@Column(name="iva", nullable = false, length = 3)
	private String iva;
	
	@Column(name="total_net", nullable = false)
	private BigInteger totalNet;
	
	@Column(name="total_gross", nullable = false)
	private BigInteger totalGross;
	
	@Column(name="statu", nullable = false, columnDefinition="tinyint(1) default 1")
	private boolean statu;
	
	@Column(name="date_register", nullable = false)
	private String dateRegister;
	
	@Column(name="date_update", nullable = true)
	private String dateUpdate;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Sale() {
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

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public BigInteger getTotalNet() {
		return totalNet;
	}

	public void setTotalNet(BigInteger totalNet) {
		this.totalNet = totalNet;
	}

	public BigInteger getTotalGross() {
		return totalGross;
	}

	public void setTotalGross(BigInteger totalGross) {
		this.totalGross = totalGross;
	}

	public Long getIdCoustomer() {
		return idCoustomer;
	}

	public void setIdCoustomer(Long idCoustomer) {
		this.idCoustomer = idCoustomer;
	}

	public boolean isStatu() {
		return statu;
	}

	public void setStatu(boolean statu) {
		this.statu = statu;
	}

	public Long getIdMethodPay() {
		return idMethodPay;
	}

	public void setIdMethodPay(Long idMethodPay) {
		this.idMethodPay = idMethodPay;
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
}
