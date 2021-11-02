package com.pastley.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", nullable = false, unique = true, length = 100)
	private String name;
	
	@Column(name = "flavor", nullable = true, length = 500)
	private String flavor;
	
	@Column(name = "vat", nullable = false, length = 3)
	private String vat;
	
	@Column(name = "stock", nullable = false)
	private int stock;

	@Column(name = "stock_min", nullable = false)
	private int stockMin;
	
	@Column(name = "dimension", nullable = true)
	private String dimension;
	
	@Column(name = "image", nullable = true, length = 500)
	private byte image;
	
	@Column(name = "statu", nullable = true, columnDefinition = "tinyint(1) default 1")
	private String statu;
	
	@Column(name = "description", nullable = true, length = 500)
	private String description;
	
	@Column(name = "description", nullable = true, length = 500)
	private String ingredients;
	
	@Column(name = "discount", nullable = false)
	private String discount;
	
	@Column(name = "price", nullable = false)
	private BigInteger price;

	public BigInteger getPrice() {
		return price;
	}

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@ManyToOne
	@JoinColumn(name = "id_category")
	@JsonBackReference
	private Category category;

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Product() {
	}

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

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMin() {
		return stockMin;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public byte getImage() {
		return image;
	}

	public void setImage(byte image) {
		this.image = image;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
