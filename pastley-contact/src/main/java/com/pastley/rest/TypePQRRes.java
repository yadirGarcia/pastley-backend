package com.pastley.rest;

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

import com.pastley.service.TypePQRService;
import com.pastley.util.PastleyResponse;
import com.pastley.util.PastleyValidate;
import com.pastley.entity.*;

@RestController
@RequestMapping("/method")
public class TypePQRRes {
	@Autowired
	private TypePQRService typePQRService;

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
		TypePQR methodTypePQR = typePQRService.findById(id);
		if (methodTypePQR != null) {
			response.add("method", methodTypePQR, HttpStatus.OK);
		} else {
			response.add("message", "No existe ningun Tipo PQR con el id " + id + ".", HttpStatus.NO_CONTENT);
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
			TypePQR methodTypePQR = typePQRService.findByName(name);
			if (methodTypePQR != null) {
				response.add("method", methodTypePQR, HttpStatus.OK);
			} else {
				response.add("message", "No existe ningun Tipo PQR con el nombre " + name + ".", HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "El nombre del PQR '" + name + "' no es valido.", HttpStatus.NO_CONTENT);
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
		List<TypePQR> list = typePQRService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun PQR registrado.", HttpStatus.NO_CONTENT);
		} else {
			response.add("methods", list, HttpStatus.OK);
			response.add("message", "Se han encontrado " + list.size() + " PQR.");
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
		List<TypePQR> list = typePQRService.findByStatuAll(statu);
		if (list.isEmpty()) {
			response.add("message", "No hay ningun PQR resgitrado con el estado " + statu + ".", HttpStatus.NO_CONTENT);
		} else {
			response.add("methods", list, HttpStatus.OK);
			response.add("message", "Se han encontrado " + list.size() + " PQR con el estado " + statu + ".");
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
	public ResponseEntity<?> create(@RequestBody TypePQR method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {
			String message = method.validate(false);
			if (message == null) {
				method.uppercase();
				TypePQR aux = typePQRService.findByName(method.getName());
				if (aux == null) {
					aux = typePQRService.save(method);
					if (aux != null) {
						response.add("method", aux, HttpStatus.OK);
						response.add("message", "Se ha registrado el PQR con id " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha registrado el PQR.", HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "Ya existe un PQR con ese nombre '" + method.getName() + "'.",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", message, HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido el PQR.", HttpStatus.NOT_FOUND);
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
	public ResponseEntity<?> update(@RequestBody TypePQR method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {
			String message = method.validate(true);
			if (message == null) {
				method.uppercase();
				TypePQR aux = typePQRService.findById(method.getId());
				if (aux != null) {
					aux = typePQRService.save(method);
					if (aux != null) {
						response.add("method", aux, HttpStatus.OK);
						response.add("message", "Se ha actualizado el PQR con id " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha actualizado el PQR con id " + method.getId() + ".",
								HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No existe ningun PQR con el id " + method.getId() + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", message, HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido el PQR.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows changing the status of a payment method.
	 * @param id, Represents the identifier of the payment method.
	 * @return The generated response.
	 */
	@PutMapping(value = "/update/statu/{id}")
	public ResponseEntity<?> updateStatu(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		if(id > 0) {
			TypePQR method = typePQRService.findById(id);
			if(method != null) {
				method.setStatu(!method.isStatu());
				method = typePQRService.save(method);
				if(method != null) {
					response.add("method", method, HttpStatus.OK);
					response.add("message", "Se ha actualizado el estado del PQR con id " + id + ".");
				}else {
					response.add("message", "No se ha actualizado el estado del PQR con id " + id + ".",
							HttpStatus.NO_CONTENT);
				}
			}else {
				response.add("message", "No existe ningun PQR con el id " + id + ".", HttpStatus.NOT_FOUND);
			}
		}else {
			response.add("message", "El id del PQR no es valido.", HttpStatus.NOT_FOUND);
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
		TypePQR aux = typePQRService.findById(id);
		if (aux != null) {
			if (typePQRService.delete(id)) {
				response.add("message", "Se ha eliminado el PQR con id " + id + ".", HttpStatus.OK);
			} else {
				response.add("message", "No se ha eliminado el PQR con id " + id + ".",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No existe ningun PQR con el id " + id + ".", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
}
