package com.pastley.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.entity.Sale;
import com.pastley.service.SaleService;
import com.pastley.util.PastleyResponse;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@RestController
@RequestMapping("/sale")
public class SaleController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SaleService saleService;
	
	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to consult a sale by its id.
	 * 
	 * @param id, Represents the identifier of the sale.
	 * @return The generated response.
	 */
	@GetMapping(value = { "/findById/{id}", "{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		Sale sale = saleService.findById(id);
		if (sale != null) {
			response.add("sale", sale, HttpStatus.OK);
		} else {
			response.add("message", "No existe ninguna venta con el id " + id + ".", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows to obtain all the sales details.
	 * 
	 * @return The generated response.
	 */
	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		PastleyResponse response = new PastleyResponse();
		List<Sale> list = saleService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun venta resgitrada.", HttpStatus.NO_CONTENT);
		} else {
			response.add("sales", list, HttpStatus.OK);
			response.add("message", "Se han encontrado " + list.size() + " ventas.");
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows you to obtain all sales through your state.
	 * 
	 * @return The generated response.
	 */
	@GetMapping(value = "/findByStatuAll/{statu}")
	public ResponseEntity<?> findByStatuAll(@PathVariable("statu") Boolean statu) {
		PastleyResponse response = new PastleyResponse();
		List<Sale> list = saleService.findByStatuAll(statu);
		if (list.isEmpty()) {
			response.add("message", "No hay ninguna venta resgitrada con el estado " + statu + ".",
					HttpStatus.NO_CONTENT);
		} else {
			response.add("sales", list, HttpStatus.OK);
			response.add("message", "Se han encontrado " + list.size() + " ventas con el estado " + statu + ".");
		}
		return ResponseEntity.ok(response.getMap());
	}
}
