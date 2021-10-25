package com.pastley.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.entity.Cart;
import com.pastley.model.ProductModel;
import com.pastley.service.CartService;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyResponse;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@RestController
@RequestMapping("/cart")
public class CartController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CartService cartService;

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to check the cart for its id.
	 * 
	 * @param id, Represents the identifier of the cart.
	 * @return The generated response.
	 */
	@GetMapping(value = { "/findById/{id}", "{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		Cart cart = cartService.findById(id);
		if (cart != null) {
			cart.calculate();
			response.add("cart", cart, HttpStatus.OK);
			response.add("message", "Se ha encontrado el carrito con el id " + id + ".");
		} else {
			response.add("message", "No existe ningun carrito con el id " + id + ".", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows you to get all the carts
	 * 
	 * @return The generated response.
	 */
	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		PastleyResponse response = new PastleyResponse();
		List<Cart> list = cartService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun carrito resgitrado.", HttpStatus.NO_CONTENT);
		} else {
			for (Cart c : list)
				c.calculate();
			response.add("carts", list, HttpStatus.OK);
			response.add("message", "Se han encontrado " + list.size() + " resultados.");
		}
		return ResponseEntity.ok(response.getMap());
	}

	@GetMapping(value = "/findByCustomer/{customer}")
	public ResponseEntity<?> findByCustomer(@PathVariable("customer") Long customer) {
		PastleyResponse response = new PastleyResponse();
		if (customer > 0) {
			List<Cart> list = cartService.findByCustomer(customer);
			if (list.isEmpty()) {
				response.add("message", "No hay ningun carrito resgitrado con el cliente id "+customer+".", HttpStatus.NO_CONTENT);
			} else {
				for (Cart c : list)
					c.calculate();
				response.add("carts", list, HttpStatus.OK);
				response.add("message", "Se han encontrado " + list.size() + " resultados.");
			}
		}else {
			response.add("message", "El id " + customer + " del cliente no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	@GetMapping(value = "/findByCustomer/{customer}/statu/{statu}")
	public ResponseEntity<?> findByCustomerAndStatus(@PathVariable("customer") Long customer, @PathVariable("statu") boolean statu){
		PastleyResponse response = new PastleyResponse();
		if (customer > 0) {
			List<Cart> list = cartService.findByCustomerAndStatus(customer, statu);
			if (list.isEmpty()) {
				response.add("message",  "No hay ningun carrito resgitrado con el cliente id "+customer+" y el estado "+statu+".", HttpStatus.NO_CONTENT);
			} else {
				for (Cart c : list)
					c.calculate();
				response.add("carts", list, HttpStatus.OK);
				response.add("message", "Se han encontrado " + list.size() + " resultados con el estado "+statu+".");
			}
		}else {
			response.add("message", "El id " + customer + " del cliente no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to register a cart.
	 * 
	 * @param product, Represents the product register.
	 * @return The generated response.
	 */
	@PostMapping(value = "/create/{id}")
	public ResponseEntity<?> create(@RequestBody ProductModel product, @PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		if (product != null) {
			if (id > 0) {
				String message = product.validate(true);
				if (message == null) {
					Cart cart = cartService.findByProductAndStatus(product.getId(), true);
					PastleyDate date = new PastleyDate();
					if (cart == null) {
						cart = new Cart(product.getId(), product.getDiscount(), product.getVat(), product.getPrice());
						cart.setIdCustomer(id);
						cart.setIdProduct(product.getId());
						cart.setStatu(true);
						cart.setCount(1);
						cart.setDateRegister(date.currentToDateTime(null));
						cart.setDateUpdate(null);
						cart.calculate();
						cart = cartService.save(cart);
						if (cart != null) {
							cart.calculate();
							response.add("cart", cart, HttpStatus.OK);
							response.add("message",
									"Se ha agregado el producto al carrito con el id " + cart.getId() + ".");
						} else {
							response.add("message", "No se ha agregado el producto al carrito.", HttpStatus.NO_CONTENT);
						}
					} else {
						cart.setDateUpdate(date.currentToDateTime(null));
						cart.setCount(cart.getCount() + 1);
						cart.setStatu(true);
						cart.calculate();
						cart = cartService.save(cart);
						if (cart != null) {
							cart.calculate();
							response.add("cart", cart, HttpStatus.OK);
							response.add("message",
									"Ya se encuentra agregado el producto en el carrito, se le aumento la cantidad a "
											+ cart.getCount() + ".");
						} else {
							response.add("message",
									"Ya se encuentra agregado el producto en el carrito, no se le aumento la cantidad al producto.",
									HttpStatus.NO_CONTENT);
						}
					}
				} else {
					response.add("message", "No se ha agregado el producto al carrito, " + message,
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No se ha recibido el cliente.", HttpStatus.NOT_FOUND);
			}
		} else {
			response.add("message", "No se ha recibido el producto.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a cart.
	 * 
	 * @param method, Represents the product to update.
	 * @return The generated response.
	 */
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody ProductModel product, @PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to delete a cart by means of its id.
	 * 
	 * @param id, Represents the identifier of the cart to be deleted.
	 * @return The generated response.
	 */
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		return ResponseEntity.ok(response.getMap());
	}
}
