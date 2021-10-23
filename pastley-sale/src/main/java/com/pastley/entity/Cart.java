package com.pastley.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pastley.util.PastleyValidate;

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
	// Other
	///////////////////////////////////////////////////////
	@Transient
	private BigInteger priceDiscount;

	@Transient
	private BigInteger priceIva;

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
	// Method
	///////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void calculate() {
		this.calculatePriceIva();
		this.calculateSubTotalNeto();
		this.calculateSubtotalGross();
		this.calculateDiscount();
	}

	/**
	 * 
	 */
	public void calculatePriceIva() {
		this.priceIva = calculateToString(this.iva);
	}

	public void calculateSubTotalNeto() {
		this.subtotalNet = BigInteger.ZERO;
	}

	public void calculateSubtotalGross() {
		this.subtotalGross = BigInteger.ZERO;
	}

	/**
	 * 
	 */
	public void calculateDiscount() {
		this.priceDiscount = calculateToString(this.discount);
	}
	
	/**
	 * 
	 * @return
	 */
	public BigInteger calculatePriceAddPriceIva() {
		if (!PastleyValidate.bigIntegerHigherZero(this.priceIva)) {
			calculatePriceIva();
		}
		return (PastleyValidate.bigIntegerHigherZero(this.price)) ? this.price.add(this.priceIva) : BigInteger.ZERO;
	}
	
	/**
	 * 
	 * @return
	 */
	public BigInteger calculatePriceSubDiscount() {
		if(!PastleyValidate.bigIntegerHigherZero(this.priceDiscount)) {
			calculateDiscount();
		}
		return (PastleyValidate.bigIntegerHigherZero(this.price)) ? this.price.subtract(this.priceDiscount) : BigInteger.ZERO;
	}

	public static void main(String... args) {
		Cart cart = new Cart(1L, "2", "02", new BigInteger("2000"));
		cart.calculate();
		System.out.println("Price: " + cart.getPrice());
		System.out.println("Price IVA: " + cart.getPriceIva());
		System.out.println("Price + Price IVA: " + cart.calculatePriceAddPriceIva());
		System.out.println("Discount: " + cart.getPriceDiscount());
		System.out.println("Price - Discount: " + cart.calculatePriceSubDiscount());
		System.out.println("Subtotal Bruto: " + cart.getSubtotalGross());
		System.out.println("Subtotal Neto: " + cart.getSubtotalNet());
	}

	///////////////////////////////////////////////////////
	// Method - Private
	///////////////////////////////////////////////////////
	private BigInteger calculateToString(String chain) {
		if (PastleyValidate.isChain(chain)) {
			BigDecimal price = new BigDecimal(this.price);
			int number = (PastleyValidate.isNumber(chain)) ? Integer.parseInt(chain) : 0;
			price = price.multiply(new BigDecimal((double) (number / 100d)));
			return price.toBigInteger();
		}
		return BigInteger.ZERO;
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

	public BigInteger getPriceIva() {
		return priceIva;
	}

	public void setPriceIva(BigInteger priceIva) {
		this.priceIva = priceIva;
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

	public BigInteger getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(BigInteger priceDiscount) {
		this.priceDiscount = priceDiscount;
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
