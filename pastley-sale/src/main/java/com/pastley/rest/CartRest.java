package com.pastley.rest;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.service.CartService;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@RestController
@RequestMapping("/cart")
public class CartRest implements Serializable {

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
	@GetMapping(value = { "/find/id/{id}", "{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.findById(id));
	}	
}
