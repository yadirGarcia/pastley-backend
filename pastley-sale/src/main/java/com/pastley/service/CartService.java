package com.pastley.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pastley.util.PastleyInterface;
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
public class CartService implements PastleyInterface<Long, Cart>{
	
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
		if(id > 0 ) {
			Optional<Cart> cart = cartRepository.findById(id);
			if(cart.isPresent()) {
				return cart.orElse(null);
			}else {
				throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha encontrado ningun producto en el carrito con el id "+id+".");
			}
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del producto del carrito no es valido.");
		}
	}

	///////////////////////////////////////////////////////
	// Method - Find - List
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to consult all the products in the cart.
	 * @return List of carts.
	 */
	@Override
	public List<Cart> findAll() {
		return cartRepository.findAll();
	}
	
	/**
	 * Method that allows you to consult all the carts by their status.
	 * @param statu, Represents the status of the product in cart.
	 * @return List of carts.
	 */
	@Override
	public List<Cart> findByStatuAll(boolean statu) {
		return cartRepository.findByStatu(statu);
	}
	
	/**
	 * Method that allows consulting all the products of a client.
	 * @param idCustomer, Represents the customer id.
	 * @return List of carts.
	 */
	public List<Cart> findByCustomer(Long idCustomer){
		if(idCustomer > 0) {
			return cartRepository.findByIdCustomer(idCustomer);
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del cliente no es valido.");
		}
	}
	
	/**
	 * Method that allows to consult all the products of a client and by their status.
	 * @param id, Represents the customer id.
	 * @param statu, Represents the status of the product
	 * @return List of carts.
	 */
	public List<Cart> findByCustomerAndStatus(Long idCustomer, boolean statu) {
		if(idCustomer > 0) {
			return cartRepository.findByCustomerAndStatus(idCustomer, statu);
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del cliente no es valido.");
		}
	}
	
	/**
	 * Method that allows you to check the products in the cart by product and status.
	 * @param idProduct, Represents the product id.
	 * @param statu, Represents the status of the product.
	 * @return List of carts.
	 */
	public List<Cart> findByProductAndStatus(Long idProduct, boolean statu) {
		saService.findProductById(idProduct);
		return cartRepository.findByProductAndStatus(idProduct, statu);
	}
	
	///////////////////////////////////////////////////////
	// Method - Save and Update
	///////////////////////////////////////////////////////
	@Override
	public Cart save(Cart entity) {
		try {
			return cartRepository.save(entity);
		} catch (Exception e) {
			return null;
		}
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
		if(cart.isStatu()) {
			cartRepository.deleteById(id);
			try {
				if(findById(id) == null) {
					return true;
				}
			} catch (PastleyException e) {
				return true;
			}
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha eliminado el producto del carito con el id " + id + ", ya se realizo la venta.");
		}
		throw new PastleyException(HttpStatus.NOT_FOUND,
				"No se ha eliminado el producto del carito con el id " + id + ".");
	}
}
