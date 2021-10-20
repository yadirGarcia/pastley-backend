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
@RequestMapping("/sale")
public class SaleController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SaleDetailService saleDetailService;
	
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
	
	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to register a sale.
	 * 
	 * @param sale, Represents the sale to register.
	 * @return The generated response.
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<?> create(@RequestBody Sale sale) {
		PastleyResponse response = new PastleyResponse();
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
	
	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a sale.
	 * 
	 * @param sale, Represents the sale to update.
	 * @return The generated response.
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody Sale sale) {
		PastleyResponse response = new PastleyResponse();
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows changing the status of a sale.
	 * @param id, Represents the identifier of the sale.
	 * @return The generated response.
	 */
	@PutMapping(value = "/update/statu/{id}")
	public ResponseEntity<?> updateStatu(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		if(id > 0) {
			Sale sale = saleService.findById(id);
			if(sale != null) {
				sale.setStatu(!sale.isStatu());
				sale = saleService.save(sale);
				if(sale != null) {
					response.add("sale", sale, HttpStatus.OK);
					response.add("message", "Se ha actualizado el estado de la venta con id " + id + ".");
				}else {
					response.add("message", "No se ha actualizado el estado de la venta con id " + id + ".",
							HttpStatus.NO_CONTENT);
				}
			}else {
				response.add("message", "No existe ninguna venta con el id " + id + ".", HttpStatus.NO_CONTENT);
			}
		}else {
			response.add("message", "El id de la venta no es valido.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to delete a sale by means of its id.
	 * 
	 * @param id, Represents the identifier of the sale to be deleted.
	 * @return The generated response.
	 */
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		if(id > 0) {
			Sale sale = saleService.findById(id);
			if(sale != null) {
				List<SaleDetail> list = saleDetailService.findBySale(id);
				if(list.isEmpty()) {
					if(saleService.delete(id)) {
						response.add("sale", sale, HttpStatus.OK);
						response.add("message", "Se ha eliminado la venta con el id " + id + ".");
					}else {
						response.add("message", "No se ha eliminado la venta con el id  "+ id +".", HttpStatus.NO_CONTENT);
					}
				}else {
					response.add("saleDetails", list, HttpStatus.NO_CONTENT);
					response.add("message", "No se ha eliminado la venta con el id  "+ id +", tiene asociado a "+ list.size() + " detalles de ventas.");
				}
			}else {
				response.add("message", "No existe ninguna venta con el id " + id + ".", HttpStatus.NO_CONTENT);
			}
		}else {
			response.add("message", "El id de la venta no es valido.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}
}
