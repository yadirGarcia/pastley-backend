package com.pastley.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pastley.util.PastleyValidate;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Entity
@Table(name = "sale_detail")
public class SaleDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "id_product", nullable = false)
	private Long idProduct;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "discount", nullable = false, columnDefinition = "varchar(3) default 0")
	private String discount;

	@Column(name = "price", nullable = false)
	private BigInteger price;

	@Column(name = "sub_total_net", nullable = false)
	private BigInteger subTotalNet;

	@Column(name = "sub_total_gross", nullable = false)
	private BigInteger subTotalGross;

	///////////////////////////////////////////////////////
	// Relations
	///////////////////////////////////////////////////////
	@ManyToOne
	@JoinColumn(name = "sale")
	private Sale sale;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public SaleDetail() {
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Method that validates the attributes of the class.
	 * 
	 * @param isId, Represents if you want to validate the id.
	 * @return A string with the error occurred.
	 */
	public String validate(boolean isId) {
		String chain = null;
		if (isId) {
			if (id <= 0) {
				chain = "El id del detalle de venta debe ser mayor a cero.";
			}
		}
		if (chain == null) {
			if (idProduct == null || idProduct <= 0) {
				chain = "No has seleccionado el producto en el detalle de venta.";
			} else if (quantity <= 0) {
				chain = "La cantidad del producto con id " + idProduct + " debe ser mayor a cero.";
			} else if (!PastleyValidate.isChain(discount)) {
				chain = "El descuento del producto con id " + idProduct + " debe estar entre 0 a 100.";
			} else if (!PastleyValidate.bigIntegerHigherZero(price)) {
				chain = "El precio del producto con id " + idProduct + " debe ser mayor a cero.";
			} else if (subTotalNet == null) {
				chain = "El subtotal neto del producto con id " + idProduct + " no es valido.";
			} else if (subTotalGross == null) {
				chain = "El subtotal bruto del producto con id " + idProduct + " no es valido.";
			} else if (sale == null || sale.getId() == null || sale.getId() <= 0) {
				chain = "No has seleccionado la venta donde pertenece este detalle.";
			}
		}
		return chain;
	}

	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
	public Long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public BigInteger getSubTotalNet() {
		return subTotalNet;
	}

	public void setSubTotalNet(BigInteger subTotalNet) {
		this.subTotalNet = subTotalNet;
	}

	public BigInteger getSubTotalGross() {
		return subTotalGross;
	}

	public void setSubTotalGross(BigInteger subTotalGross) {
		this.subTotalGross = subTotalGross;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}