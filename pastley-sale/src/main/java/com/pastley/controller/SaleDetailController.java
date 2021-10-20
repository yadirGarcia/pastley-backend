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

import com.pastley.entity.Sale;
import com.pastley.entity.SaleDetail;
import com.pastley.service.SaleDetailService;
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
@RequestMapping("/saleDetail")
public class SaleDetailController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SaleDetailService saleDetailService;
	@Autowired
	private SaleService saleService;

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////
	/**
	 * Method that allows consulting a sale detail by its id.
	 * 
	 * @param id, Represents the identifier of the sale detail.
	 * @return The generated response.
	 */
	@GetMapping(value = { "/findById/{id}", "{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		SaleDetail saleDetail = saleDetailService.findById(id);
		if (saleDetail != null) {
			response.add("saleDetail", saleDetail, HttpStatus.OK);
		} else {
			response.add("message", "No existe ningun detalle de venta con el id " + id + ".", HttpStatus.NO_CONTENT);
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
		List<SaleDetail> list = saleDetailService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun detalle de venta resgitrado.", HttpStatus.NO_CONTENT);
		} else {
			response.add("saleDetails", list, HttpStatus.OK);
			response.add("message", "Se han encontrado " + list.size() + " detalles de ventas.");
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows knowing the sale details of a sale made.
	 * 
	 * @return The generated response.
	 */
	@GetMapping(value = "/findBySale/{sale}")
	public ResponseEntity<?> findBySale(@PathVariable("sale") Long sale){
		PastleyResponse response = new PastleyResponse();
		Sale saleEntity = saleService.findById(sale);
		if(saleEntity != null) {
			List<SaleDetail> list = saleDetailService.findBySale(sale);
			if(list.isEmpty()) {
				response.add("message", "No hay ningun detalle de venta asociado a la venta con el id "+sale+".", HttpStatus.NO_CONTENT);
			}else {
				response.add("sale", saleEntity);
				response.add("saleDetails", list, HttpStatus.OK);
				response.add("message", "Se han encontrado " + list.size() + " detalles de ventas asociados a la venta con el id "+sale+".");
			}
		}else {
			response.add("message", "No existe ninguna venta con el id " + sale + ".", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to register a sale detail.
	 * 
	 * @param saleDetail, Represents the sale detail to register.
	 * @return The generated response.
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<?> create(@RequestBody SaleDetail saleDetail) {
		PastleyResponse response = new PastleyResponse();
		return ResponseEntity.ok(response.getMap());
	}
	
	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a sale detail.
	 * 
	 * @param saleDetail, Represents the sale detail to update.
	 * @return The generated response.
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody SaleDetail saleDetail) {
		PastleyResponse response = new PastleyResponse();
		return ResponseEntity.ok(response.getMap());
	}
	
	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to delete a sale detail by means of its id.
	 * @param id, Represents the identifier of the sale detail to be deleted.
	 * @return The generated response.
	 */
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		SaleDetail aux = saleDetailService.findById(id);
		if (aux != null) {
			if (saleDetailService.delete(id)) {
				response.add("message", "Se ha eliminado el detalle de venta con id " + id + ".", HttpStatus.OK);
			}else {
				response.add("message", "No se ha eliminado el detalle de venta con id " + id + ".", HttpStatus.NOT_FOUND);
			}
		} else {
			response.add("message", "No existe ningun detalle de venta con el id " + id + ".", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}
}
