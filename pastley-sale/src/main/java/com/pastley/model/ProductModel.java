package com.pastley.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class ProductModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private BigInteger price;
	private String discount;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public ProductModel() {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getPrice() {
		return price;
	}
	public void setPrice(BigInteger price) {
		this.price = price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
