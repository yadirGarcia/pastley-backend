package com.pastley.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.entity.User;
import com.pastley.service.UserService;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyResponse;
import com.pastley.util.PastleyValidate;


@RestController
@RequestMapping("user")
public class UserRest {
	@Autowired
	UserService userService;

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to search for "CASHIER" by ID
	 */
	@GetMapping(value = { "/findById/{id}", "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();

		// if(roleService.findById(user.getIdRole()) != null) {
		// if( == "Cajero") {
		User user = userService.findById(id);
		if (user != null) {
			response.add("user", user, HttpStatus.OK);
		} else {
			response.add("message", "No hay ningun cajero registratdo con ese ID " + id + ".", HttpStatus.NO_CONTENT);
		}
		// }
		// }
		
		return ResponseEntity.ok(response.getMap());
	}
	/**
	 * Method that allows you to search for "CUSTOMER" by ID
	 */
	
	/**
	 * Method that allows you to search for "CUSTOMER LOGIN" by ID
	 */
	
	
	
	/**
	 * Method that allows you to list all "CASHIER"
	 */
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		PastleyResponse response = new PastleyResponse();
		
		// if(roleService.findById(user.getIdRole()) != null) {
		// if( == "Cajero") {
		//Hacer listas diferentes para los diferentes roles
		List<User> list = userService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun cajero registrado.", HttpStatus.NO_CONTENT);
		} else {
			response.add("user", list, HttpStatus.OK);
		}
		return ResponseEntity.ok(response.getMap());
		// }
		// }
	}
	/**
	 * Method that allows you to search for "CUSTOMER" by ID
	 */
	
	/**
	 * Method that allows you to search for "CUSTOMER LOGIN" by ID
	 */
	
	

	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to register a "CASHIER".
	 */
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody User user) {
		PastleyResponse response = new PastleyResponse();

		if (user != null) {
			if (user.getId() <= 0) {
				// if(roleService.findById(user.getIdRole()) != null) {
				// if( == "Cajero") {

				if (PastleyValidate.isChain(user.getMail())) {
					User aux = userService.findByMail(user.getMail());
					if (aux == null) {
						if (user.getPassword() != null) {
							PastleyDate date = new PastleyDate();
							user.setDateUpdate(null);
							user.setDateSession(null);
							user.setDateRegister(date.currentToDateTime(null));
							user.setStatu(true);
							user.setMail(user.getMail().toUpperCase());
							user.setLastPassword(user.getPassword());
							// falta id cajero y perosona
							aux = userService.save(user);
							if (aux != null) {
								response.add("user", aux, HttpStatus.OK);
								response.add("message", "Se ha registrado el usuario con el id " + aux.getId() + ".");
							} else {
								response.add("message", "No se ha registrado el usuario.", HttpStatus.NO_CONTENT);
							}

						} else {
							response.add("message",
									"Para realizar el registro del cajero debe asignarle una contraseña.",
									HttpStatus.NO_CONTENT);
						}
					} else {
						response.add("message",
								"Ya existe una un usuario registrado con ese email '" + user.getMail() + "'.",
								HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No se ha registrado el cajero, debe asignarle un email al cajero.",
							HttpStatus.NO_CONTENT);
				}

				// }
				// }
			} else {
				response.add("message", "No se ha registrado el cajero, el ID debe ser menor o igual a 0.",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido los datos del cajero a registrar.", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(response.getMap());
	}
	/**
	 * Method that allows you to register a "ADMINISTRATOR".
	 */

	/**
	 * Method that allows you to register a "CUSTOMER".
	 */

	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a user "CASHIER".
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody User user) {
		PastleyResponse response = new PastleyResponse();

		if (user != null) {
			// if(roleService.findById(user.getIdRole()) != null) {
			// if( == "Cajero") {
			String message = user.validate(true);
			if (message == null) {
				user.uppercase();
				User aux = userService.findById(user.getId());
				if (aux != null) {
					PastleyDate date = new PastleyDate();
					user.setDateUpdate(date.currentToDateTime(null));
					user.setDateRegister(aux.getDateRegister());
					user.setMail(user.getMail().toUpperCase());
					aux = userService.save(user);
					if (aux != null) {
						response.add("user", aux, HttpStatus.OK);
						response.add("message", "Se ha actualizado el cajero con ID " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha actualizado el cajero.", HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No existe ningun cajero con el id " + user.getId() + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", message, HttpStatus.NO_CONTENT);
			}
			// }
			// }
		} else {
			response.add("message", "No se ha recibido el cajero.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows updating a user "ADMINISTRATOR".
	 */

	/**
	 * Method that allows updating a user "CUSTOMER".
	 */

	/**
	 * Method that allows updating a role statu.
	 */
	@PutMapping(value = "/update/{id}/statu")
	public ResponseEntity<?> update(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		if (id > 0) {
			// if(roleService.findById(user.getIdRole()) != null) {
			// if( == "Cajero") {
			User user = userService.findById(id);
			if (user != null) {
				PastleyDate date = new PastleyDate();
				user.setDateUpdate(date.currentToDateTime(null));
				user.setStatu(!user.isStatu());
				user = userService.save(user);
				if (user != null) {
					response.add("role", user, HttpStatus.OK);
					response.add("message",
							"Se ha actualizado el estado a " + user.isStatu() + " del cajero con id " + id + ".");
				} else {
					response.add("message", "No se ha actualizado el estado del cajero con id " + id + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No existe ningun cajero con el id " + id + ".", HttpStatus.NO_CONTENT);
			}
			// }
			// }

		} else {
			response.add("message", "El cajero rol no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

}
