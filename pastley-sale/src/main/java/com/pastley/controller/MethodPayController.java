package com.pastley.controller;

import java.io.Serializable;
import java.text.ParseException;
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

import com.pastley.entity.MethodPay;
import com.pastley.model.StatisticModel;
import com.pastley.service.MethodPayService;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyResponse;
import com.pastley.util.PastleyValidate;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@RestController
@RequestMapping("/method")
public class MethodPayController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MethodPayService methodPayService;

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////
	/**
	 * Method that allows consulting a payment method by its id.
	 * 
	 * @param id, Represents the identifier of the payment method.
	 * @return The generated response.
	 */
	@GetMapping(value = { "/findById/{id}", "{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		MethodPay methodPay = methodPayService.findById(id);
		if (methodPay != null) {
			response.add("method", methodPay, HttpStatus.OK);
		} else {
			response.add("message", "No existe ningun metodo de pago con el id " + id + ".", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows consulting a payment method by its name.
	 * 
	 * @param name, Represents the name of the payment method.
	 * @return The generated response.
	 */
	@GetMapping(value = { "/findByName/{name}" })
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		PastleyResponse response = new PastleyResponse();
		if (PastleyValidate.isChain(name)) {
			MethodPay methodPay = methodPayService.findByName(name);
			if (methodPay != null) {
				response.add("method", methodPay, HttpStatus.OK);
			} else {
				response.add("message", "No existe ningun metodo de pago con el nombre " + name + ".",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "El nombre del metodo de pago '" + name + "' no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows you to obtain all payment methods.
	 * 
	 * @return The generated response.
	 */
	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		PastleyResponse response = new PastleyResponse();
		List<MethodPay> list = methodPayService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun metodo de pago resgitrado.", HttpStatus.NO_CONTENT);
		} else {
			response.add("methods", list, HttpStatus.OK);
			response.add("message", "Se han encontrado " + list.size() + " metodos de pago.");
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows you to obtain all payment methods through your state.
	 * 
	 * @return The generated response.
	 */
	@GetMapping(value = "/findByStatuAll/{statu}")
	public ResponseEntity<?> findByStatuAll(@PathVariable("statu") Boolean statu) {
		PastleyResponse response = new PastleyResponse();
		List<MethodPay> list = methodPayService.findByStatuAll(statu);
		if (list.isEmpty()) {
			response.add("message", "No hay ningun metodo de pago resgitrado con el estado " + statu + ".",
					HttpStatus.NO_CONTENT);
		} else {
			response.add("methods", list, HttpStatus.OK);
			response.add("message",
					"Se han encontrado " + list.size() + " metodos de pago con el estado " + statu + ".");
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows filtering the payment methods that are registered between a date range.
	 * @param start, Represents the start date.
	 * @param end, Represents the end date.
	 * @return The generated response.
	 */
	@GetMapping(value = "/findByRangeDateRegisterAll/{start}/{end}")
	public ResponseEntity<?> findByStatuAll(@PathVariable("start") String start, @PathVariable("end") String end) {
		PastleyResponse response = new PastleyResponse();
		PastleyDate date = new PastleyDate();
		try {
			String array_date[] = { 
				date.formatToDateTime(date.convertToDate(start.replaceAll("-", "/")), null),
				date.formatToDateTime(date.convertToDate(end.replaceAll("-", "/")), null) 
			};
			List<MethodPay> list = methodPayService.findByRangeDateRegister(array_date[0], array_date[1]);
			if (list.isEmpty()) {
				response.add("message", "No hay ningun metodo de pago resgitrado en ese rango de fecha " + array_date[0]
						+ " a " + array_date[1] + ".", HttpStatus.NO_CONTENT);
			} else {
				response.add("methods", list, HttpStatus.OK);
				response.add("message", "Se han encontrado " + list.size() + " metodos de pago en ese rango de fecha "
						+ array_date[0] + " a " + array_date[1] + ".");
			}
			response.add("dates", array_date);
		} catch (ParseException e) {
			response.add("message",
					"No se ha recibido la fecha inicio o la fecha fin, el formato permitido es: 'Año-Mes-Dia'.",
					HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows to know the amount of sale made by a payment method.
	 * @param id, Represents the identifier of the payment method.
	 * @return The generated response.
	 */
	@GetMapping(value = "/findByStatisticSale/{id}")
	public ResponseEntity<?> findByStatisticSale(@PathVariable Long id){
		PastleyResponse response = new PastleyResponse();
		if(id > 0) {
			MethodPay method = methodPayService.findById(id);
			if(method != null) {
				StatisticModel<MethodPay> sm = new StatisticModel<>(method, methodPayService.findByStatisticSale(id));
				response.add("statistic", sm, HttpStatus.OK);
				response.add("message",
						"Se generado la estadistica del metodo de pago con id "+id+".");
			}else {
				response.add("message", "No existe ningun metodo de pago con el id " + id + ".",
						HttpStatus.NO_CONTENT);
			}
		}else {
			response.add("message", "El id del metodo de pago no es valido.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows to know the amount of sales made by a payment method.
	 * @return The generated response.
	 */
	@GetMapping(value = "/findByStatisticSaleAll")
	public ResponseEntity<?> findByStatisticSaleAll(){
		PastleyResponse response = new PastleyResponse();
		List<StatisticModel<MethodPay>> list = methodPayService.findByStatisticSaleAll();
		if(list.isEmpty()) {
			response.add("message", "No se pudo generar la estadistica, no hay valores para mostrar.",
					HttpStatus.NO_CONTENT);
		}else {
			response.add("statistics", list, HttpStatus.OK);
			response.add("message",
					"Se generado la estadistica con " + list.size() + " metodos de pago.");
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to register a payment method.
	 * 
	 * @param method, Represents the payment method to register.
	 * @return The generated response.
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<?> create(@RequestBody MethodPay method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {
			if (method.getId() <= 0) {
				String message = method.validate(false);
				if (message == null) {
					method.uppercase();
					MethodPay aux = methodPayService.findByName(method.getName());
					if (aux == null) {
						PastleyDate date = new PastleyDate();
						method.setDateRegister(date.currentToDateTime(null));
						method.setDateUpdate(null);
						aux = methodPayService.save(method);
						if (aux != null) {
							response.add("method", aux, HttpStatus.OK);
							response.add("message", "Se ha registrado el metodo de pago con id " + aux.getId() + ".");
						} else {
							response.add("message", "No se ha registrado el metodo de pago.", HttpStatus.NO_CONTENT);
						}
					} else {
						response.add("message",
								"Ya existe un metodo de pago con ese nombre '" + method.getName() + "'.",
								HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", message, HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No se ha registrado el metodo de pago, el ID debe ser menor o igual a 0.",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido el metodo de pago.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a payment method.
	 * 
	 * @param method, Represents the payment method to update.
	 * @return The generated response.
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody MethodPay method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {
			String message = method.validate(true);
			if (message == null) {
				method.uppercase();
				MethodPay aux = methodPayService.findById(method.getId());
				if (aux != null) {
					PastleyDate date = new PastleyDate();
					method.setDateRegister(aux.getDateRegister());
					method.setDateUpdate(date.currentToDateTime(null));
					aux = methodPayService.save(method);
					if (aux != null) {
						response.add("method", aux, HttpStatus.OK);
						response.add("message", "Se ha actualizado el metodo de pago con id " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha actualizado el metodo de pago con id " + method.getId() + ".",
								HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No existe ningun metodo de pago con el id " + method.getId() + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", message, HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido el metodo de pago.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows changing the status of a payment method.
	 * 
	 * @param id, Represents the identifier of the payment method.
	 * @return The generated response.
	 */
	@PutMapping(value = "/update/statu/{id}")
	public ResponseEntity<?> updateStatu(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		if (id > 0) {
			MethodPay method = methodPayService.findById(id);
			if (method != null) {
				method.setStatu(!method.isStatu());
				method = methodPayService.save(method);
				if (method != null) {
					response.add("method", method, HttpStatus.OK);
					response.add("message", "Se ha actualizado el estado del metodo de pago con id " + id + ".");
				} else {
					response.add("message", "No se ha actualizado el estado del metodo de pago con id " + id + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No existe ningun metodo de pago con el id " + id + ".", HttpStatus.NOT_FOUND);
			}
		} else {
			response.add("message", "El id del metodo de pago no es valido.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to delete a payment method by means of its id.
	 * 
	 * @param id, Represents the identifier of the payment method to be deleted.
	 * @return The generated response.
	 */
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		MethodPay aux = methodPayService.findById(id);
		if (aux != null) {
			if (methodPayService.delete(id)) {
				response.add("method", aux);
				response.add("message", "Se ha eliminado el metodo de pago con id " + id + ".", HttpStatus.OK);
			} else {
				response.add("message", "No se ha eliminado el metodo de pago con id " + id + ".",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No existe ningun metodo de pago con el id " + id + ".", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
}
