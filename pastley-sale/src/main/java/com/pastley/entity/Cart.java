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
@Table(name = "cart")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_product", nullable = false)
	private Long idProduct;

	@Column(name = "id_customer", nullable = false)
	private Long idCustomer;

	@Column(name = "discount", nullable = false, columnDefinition = "varchar(3) default 0")
	private String discount;

	@Column(name = "iva", nullable = false, length = 3)
	private String iva;

	@Column(name = "count", nullable = false)
	private int count;

	@Column(name = "price", nullable = false)
	private BigInteger price;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "subtotal_net", nullable = false)
	private BigInteger subtotalNet;

	@Column(name = "subtotal_gross", nullable = false)
	private BigInteger subtotalGross;

	@Column(name = "statu", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean statu;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Cart() {
	}

	public Cart(Long id, String discount, String iva, BigInteger price) {
		this.id = id;
		this.discount = discount;
		this.iva = iva;
		this.price = price;
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

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigInteger getSubtotalNet() {
		return subtotalNet;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public void setSubtotalNet(BigInteger subtotalNet) {
		this.subtotalNet = subtotalNet;
	}

	public BigInteger getSubtotalGross() {
		return subtotalGross;
	}

	public void setSubtotalGross(BigInteger subtotalGross) {
		this.subtotalGross = subtotalGross;
	}

	public boolean isStatu() {
		return statu;
	}

	public void setStatu(boolean statu) {
		this.statu = statu;
	}
}
