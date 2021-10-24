package com.pastley.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.pastley.util.PastleyValidate;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class ProductModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private BigInteger price;
	private String discount;

	///////////////////////////////////////////////////////
	// Other
	///////////////////////////////////////////////////////
	private String vat;

	private BigInteger priceVat;
	private BigInteger priceDiscount;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public ProductModel() {
	}

	public ProductModel(Long id, String name, BigInteger price, String discount) {
		this(id, name, price, discount, null);
	}

	public ProductModel(BigInteger price, String discount, String vat) {
		this(0L, null, price, discount, vat);
	}

	public ProductModel(Long id, BigInteger price, String discount, String vat) {
		this(id, null, price, discount, vat);
	}

	public ProductModel(Long id, String name, BigInteger price, String discount, String vat) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.vat = vat;
	}

	///////////////////////////////////////////////////////
	// Method - Validate
	///////////////////////////////////////////////////////
	/**
	 * Method that validates the attributes of the class.
	 * 
	 * @param isId, Represents if you want to validate the id.
	 * @return The error occurred.
	 */
	public String validate(boolean isId) {
		String chain = null;
		if (isId) {
			if (id <= 0) {
				chain = "El id del producto debe ser mayor a cero.";
			}
		}
		if (!PastleyValidate.isChain(name)) {
			chain = "El nombre del producto no es valido.";
		}
		if (!PastleyValidate.isChain(discount)) {
			chain = "El descuento del producto no es valido.";
		}
		if (!PastleyValidate.bigIntegerHigherZero(price)) {
			chain = "El precio del producto no es valido.";
		}
		return chain;
	}

	///////////////////////////////////////////////////////
	// Method - Calculate
	///////////////////////////////////////////////////////
	/**
	 * Method that allows all prices to be calculated.
	 */
	public void calculate() {
		this.calculatePriceIva();
		this.calculateDiscount();

	}

	/**
	 * Method that allows calculating the price of vat.
	 */
	public void calculatePriceIva() {
		this.priceVat = calculate(this.vat);
	}

	/**
	 * Method that allows the discount price to be calculated.
	 */
	public void calculateDiscount() {
		this.priceDiscount = calculate(this.discount);
	}

	/**
	 * Method for calculating the net subtotal.
	 * 
	 * @return The value obtained.
	 */
	public BigInteger calculateSubTotalNet() {
		return calculatePriceSubDiscount();
	}

	/**
	 * Method for calculating the gross subtotal.
	 * 
	 * @return The value obtained.
	 */
	public BigInteger calculateSubtotalGross() {
		if (!PastleyValidate.bigIntegerHigherZero(this.priceVat)) {
			calculatePriceIva();
		}
		return calculateSubTotalNet().add(this.priceVat);
	}

	/**
	 * Method that allows adding the value of the VAT to the price of the product.
	 * 
	 * @return The value obtained.
	 */
	public BigInteger calculatePriceAddPriceIva() {
		if (!PastleyValidate.bigIntegerHigherZero(this.priceVat)) {
			calculatePriceIva();
		}
		BigInteger price = (PastleyValidate.bigIntegerHigherZero(this.price)) ? this.price.add(this.priceVat)
				: BigInteger.ZERO;
		return (PastleyValidate.bigIntegerLessZero(price)) ? BigInteger.ZERO : price;
	}

	/**
	 * Method that allows subtracting the value of the discount from the price of
	 * the product.
	 * 
	 * @return The value obtained.
	 */
	public BigInteger calculatePriceSubDiscount() {
		if (!PastleyValidate.bigIntegerHigherZero(this.priceDiscount)) {
			calculateDiscount();
		}
		BigInteger price = (PastleyValidate.bigIntegerHigherZero(this.price)) ? this.price.subtract(this.priceDiscount)
				: BigInteger.ZERO;
		return (PastleyValidate.bigIntegerLessZero(price)) ? BigInteger.ZERO : price;
	}

	///////////////////////////////////////////////////////
	// Method - Private
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to convert a percentage into a price.
	 * 
	 * @param chain, Represents the percentage.
	 * @return The value obtained.
	 */
	private BigInteger calculate(String chain) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public BigInteger getPriceVat() {
		return priceVat;
	}

	public void setPriceIva(BigInteger priceVat) {
		this.priceVat = priceVat;
	}

	public BigInteger getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(BigInteger priceDiscount) {
		this.priceDiscount = priceDiscount;
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
