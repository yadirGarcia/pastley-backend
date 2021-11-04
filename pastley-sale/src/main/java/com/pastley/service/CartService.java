package com.pastley.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyInterface;
import com.pastley.util.PastleyValidate;
import com.pastley.util.exception.PastleyException;
import com.pastley.entity.Cart;
import com.pastley.repository.CartRepository;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class CartService implements PastleyInterface<Long, Cart> {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private SaleService saService;

	///////////////////////////////////////////////////////
	// Method - Find
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to check the cart for its id.
	 * 
	 * @param id, Represents the identifier of the cart.
	 * @return Cart.
	 */
	@Override
	public Cart findById(Long id) {
		if (id > 0) {
			Optional<Cart> cart = cartRepository.findById(id);
			if (cart.isPresent()) {
				return cart.orElse(null);
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"No se ha encontrado ningun producto en el carrito con el id " + id + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del producto del carrito no es valido.");
		}
	}

	/**
	 * Method that allows consulting a product in the cart by the customer id and
	 * the product id.
	 * 
	 * @param idCustomer, Represents the customer id.
	 * @param statu,      Represents the status of the product.
	 * @param idProduct,  Represents the product id.
	 * @return Cart.
	 */
	public Cart findByCustomerAndProductAndStatu(boolean statu, Long idCustomer, Long idProduct) {
		if (idCustomer > 0) {
			if (idProduct > 0) {
				Cart cart = cartRepository.findByCustomerAndProductAndStatu(statu, idCustomer, idProduct);
				if (cart != null) {
					return cart;
				}
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"No se ha encontrado ningun producto de carrito con el id del cliente " + idCustomer
								+ ", id producto " + idProduct + " y estado " + statu + ".");
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND, "El id del producto no es valido.");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del cliente no es valido.");
		}
	}

	///////////////////////////////////////////////////////
	// Method - Find - List
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to consult all the products in the cart.
	 * 
	 * @return List of carts.
	 */
	@Override
	public List<Cart> findAll() {
		return cartRepository.findAll();
	}

	/**
	 * Method that allows you to consult all the carts by their status.
	 * 
	 * @param statu, Represents the status of the product in cart.
	 * @return List of carts.
	 */
	@Override
	public List<Cart> findByStatuAll(boolean statu) {
		return cartRepository.findByStatu(statu);
	}

	/**
	 * Method that allows consulting all the products of a client.
	 * 
	 * @param idCustomer, Represents the customer id.
	 * @return List of carts.
	 */
	public List<Cart> findByCustomer(Long idCustomer) {
		if (idCustomer > 0) {
			return cartRepository.findByIdCustomer(idCustomer);
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del cliente no es valido.");
		}
	}

	/**
	 * Method that allows to consult all the products of a client and by their
	 * status.
	 * 
	 * @param id,    Represents the customer id.
	 * @param statu, Represents the status of the product
	 * @return List of carts.
	 */
	public List<Cart> findByCustomerAndStatus(Long idCustomer, boolean statu) {
		if (idCustomer > 0) {
			return cartRepository.findByCustomerAndStatus(idCustomer, statu);
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del cliente no es valido.");
		}
	}

	/**
	 * Method that allows consulting the different carts with the same product.
	 * 
	 * @param idCustomer, Represents the customer id.
	 * @param idProduct,  Represents the product id.
	 * @return List Cart.
	 */
	public List<Cart> findByCustomerAndProduct(Long idCustomer, Long idProduct) {
		if (idCustomer > 0) {
			if (idProduct > 0) {
				return cartRepository.findByCustomerAndProduct(idCustomer, idProduct);
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND, "El id del producto no es valido.");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del cliente no es valido.");
		}
	}

	/**
	 * Method that allows you to check the products in the cart by product and
	 * status.
	 * 
	 * @param idProduct, Represents the product id.
	 * @param statu,     Represents the status of the product.
	 * @return List of carts.
	 */
	public List<Cart> findByProductAndStatus(Long idProduct, boolean statu) {
		saService.findProductById(idProduct);
		return cartRepository.findByProductAndStatus(idProduct, statu);
	}

	///////////////////////////////////////////////////////
	// Method - Find - List - Range
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to filter the products in the cart that are registered
	 * between a range of dates.
	 * 
	 * @param start, Represents the start date.
	 * @param end,   Represents the end date.
	 * @return List of carts.
	 */
	public List<Cart> findByRangeDateRegister(String start, String end) {
		String array_date[] = findByRangeDateRegisterValidateDate(start, end);
		return cartRepository.findByRangeDateRegister(array_date[0], array_date[1]);
	}

	/**
	 * Method that allows you to filter the products in the cart that are registered
	 * between a range of dates and the customer's id.
	 * 
	 * @param idCustomer, Represents the customer id.
	 * @param start,      Represents the start date.
	 * @param end,        Represents the end date.
	 * @return List of carts.
	 */
	public List<Cart> findByRangeDateRegisterAndCustomer(Long idCustomer, String start, String end) {
		String array_date[] = findByRangeDateRegisterValidateDate(start, end);
		return cartRepository.findByRangeDateRegisterAndCustomer(idCustomer, array_date[0], array_date[1]);
	}

	/**
	 * Method that allows to validate the two dates.
	 * 
	 * @param start, Represents the start date.
	 * @param end,   Represents the end date.
	 * @return Array.
	 */
	private String[] findByRangeDateRegisterValidateDate(String start, String end) {
		if (PastleyValidate.isChain(start) && PastleyValidate.isChain(end)) {
			PastleyDate date = new PastleyDate();
			try {
				String array_date[] = { date.formatToDateTime(date.convertToDate(start.replaceAll("-", "/")), null),
						date.formatToDateTime(date.convertToDate(end.replaceAll("-", "/")), null) };
				return array_date;
			} catch (ParseException e) {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"El formato permitido para las fechas es: 'Año-Mes-Dia'.");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido la fecha inicio o la fecha fin.");
		}
	}

	///////////////////////////////////////////////////////
	// Method - Save and Update
	///////////////////////////////////////////////////////
	@Override
	public Cart save(Cart entity) {
		return null;
	}

	/**
	 * Method that allows you to register or update a cart product.
	 * 
	 * @param entity, Represents the cart product.
	 * @param type,   Represents the type of operation
	 * @return Cart.
	 */
	public Cart save(Cart entity, byte type) {
		if (entity != null) {
			String message = entity.validate(false);
			String messageType = (type == 1) ? "registrar"
					: ((type == 2) ? "actualizar" : ((type == 3) ? "actualizar estado" : "n/a"));
			if (message == null) {
				Cart cart = null;
				if (entity.getId() != null && entity.getId() > 0) {
					cart = saveToUpdate(entity, type);
				} else {
					cart = saveToSave(entity, type);
				}
				cart.setDiscount(PastleyValidate.isChain(cart.getDiscount()) ? cart.getDiscount() : "0");
				cart.setVat(PastleyValidate.isChain(cart.getVat()) ? cart.getVat() : "0");
				cart.calculate();
				cart = cartRepository.save(cart);
				if (cart != null) {
					return cart;
				} else {
					throw new PastleyException(HttpStatus.NOT_FOUND,
							"No se ha " + messageType + " el producto carrito.");
				}
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"No se ha " + type + " el producto carrito, " + message + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido el cart.");
		}
	}

	/**
	 * Method that allows you to register a cart product.
	 * 
	 * @param entity, Represents the cart product.
	 * @param type,   Represents the type of operation
	 * @return Cart.
	 */
	private Cart saveToSave(Cart entity, byte type) {
		try {
			findByCustomerAndProductAndStatu(true, entity.getIdCustomer(), entity.getIdProduct());
		} catch (Exception e) {
			PastleyDate date = new PastleyDate();
			entity.setId(0L);
			entity.setDateRegister(date.currentToDateTime(null));
			entity.setDateUpdate(null);
			entity.setCount(entity.getCount() <= 0 ? 1 : entity.getCount());
			return entity;
		}
		throw new PastleyException(HttpStatus.NOT_FOUND, "El cliente id " + entity.getIdCustomer()
				+ " ya tiene agregado en el carrito el producto con el id " + entity.getIdProduct() + ".");
	}

	/**
	 * Method that allows you to update a cart product.
	 * 
	 * @param entity, Represents the cart product.
	 * @param type,   Represents the type of operation
	 * @return Cart.
	 */
	private Cart saveToUpdate(Cart entity, byte type) {
		Cart cart = findById(entity.getId());
		if (cart != null) {
			PastleyDate date = new PastleyDate();
			entity.setDateRegister(cart.getDateRegister());
			entity.setCount(entity.getCount() + cart.getCount());
			entity.setDateUpdate(date.currentToDateTime(null));
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha encontrado ningun producto carrito con el id " + entity.getId() + ".");
		}
		return entity;

	}

	///////////////////////////////////////////////////////
	// Method - Save and Update
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to delete a product with the cart.
	 */
	@Override
	public boolean delete(Long id) {
		Cart cart = findById(id);
		if (cart.isStatu()) {
			cartRepository.deleteById(id);
			try {
				if (findById(id) == null) {
					return true;
				}
			} catch (PastleyException e) {
				return true;
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha eliminado el producto del carito con el id " + id + ", ya se realizo la venta.");
		}
		throw new PastleyException(HttpStatus.NOT_FOUND,
				"No se ha eliminado el producto del carito con el id " + id + ".");
	}
}
