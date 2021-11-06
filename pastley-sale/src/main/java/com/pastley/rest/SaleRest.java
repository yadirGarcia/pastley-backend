package com.pastley.rest;

import java.io.Serializable;

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

import com.pastley.entity.Sale;
import com.pastley.service.SaleService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@RestController
@RequestMapping("/sale")
public class SaleRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SaleService saleService;

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to consult a s  ale by its id.
	 * 
	 * @param id, Represents the identifier of the sale.
	 * @return The generated response.
	 */
	@GetMapping(value = { "/find/id/{id}", "{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(saleService.findById(id));
	}
	
	/**
	 * Method that allows all sales to be consulted.
	 * @return The generated response.
	 */
	@GetMapping(value = {"", "/all"})
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(saleService.findAll());
	}
	
	/**
	 * Method that allows you to check sales by their status.
	 * @param statu, Represents the state.
	 * @return The generated response.
	 */
	@GetMapping(value = "/all/find/statu/{statu}")
	public ResponseEntity<?> findByStatuAll(@PathVariable("statu") Boolean statu) {
		return ResponseEntity.status(HttpStatus.OK).body(saleService.findByStatuAll(statu));
	}
	
	/**
	 * Method that allows consulting the sales that are in a date range.
	 * @param start, Represents the start date.
	 * @param end,   Represents the end date.
	 * @return The generated response.
	 */
	@GetMapping(value = "/range/all/find/date/register/{start}/{end}")
	public ResponseEntity<?> findByRangeDateRegister(@PathVariable("start") String start, @PathVariable("end") String end) {
		return ResponseEntity.status(HttpStatus.OK).body(saleService.findByRangeDateRegister(start, end));
	}
	
	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to register a sale.
	 * @param sale, Represents the sale.
	 * @return The generated response.
	 */
	@CircuitBreaker(name = "userCB", fallbackMethod = "fallBackCreate")
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody Sale sale) {
		return ResponseEntity.status(HttpStatus.OK).body(saleService.save(sale, (byte) 1));
	}
	
	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating the information of a sale.
	 * 
	 * @param sale, Represents the sale.
	 * @return The generated response.
	 */
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Sale sale) {
		return ResponseEntity.status(HttpStatus.OK).body(saleService.save(sale, (byte) 2));
	}
	
	/**
	 * Method that allows updating the status of a sale.
	 * 
	 * @param id, Represents the identifier of the sale.
	 * @return The generated response.
	 */
	@PutMapping(value = "/update/{id}/statu")
	public ResponseEntity<?> updateStatu(@PathVariable("id") Long id) {
		Sale sale= saleService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(saleService.save(sale, (byte) 3));
	}
	
	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to delete a sale.
	 * @param id, Represents the identifier of the sale.
	 * @return The generated response.
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(saleService.delete(id));
	}
	
}
