package com.pastley.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.entity.Person;
import com.pastley.security.entity.Role;
import com.pastley.security.entity.User;
import com.pastley.security.service.RoleService;
import com.pastley.security.service.UserService;
import com.pastley.service.PersonService;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyResponse;
import com.pastley.util.PastleyVariable;

@RestController
@RequestMapping("user")
public class UserRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonRest personRest;

	@Autowired
	private RoleService roleService;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	public PastleyResponse findByIdUser(Long id, Long idRole, String name) {
		PastleyResponse response = new PastleyResponse();
		User user = userService.findByIdAndIdRol(id, idRole);
		if (user != null) {
			response.add("user", user, HttpStatus.OK);
			response.add("message", "Se encontro el " + name + " con el ID " + id + ".");
		} else {
			response.add("message", "No hay ningun " + name + " registratdo con ese ID " + id + ".",
					HttpStatus.NO_CONTENT);
		}
		return response;
	}

	public PastleyResponse createUser(User user, Long idRole, String name) {
		PastleyResponse response = new PastleyResponse();
		if (user != null) {
			if (user.getId() <= 0) {
				String message = user.validate(false);
				if (message == null) {
					user.setRole(roleService.findById(idRole));
					if (user.getRole() != null) {
						message = user.getPerson().validate(false);
						if(message == null ) {
							PastleyResponse res = personRest.createPerson(user.getPerson());
							Person personRegister = (Person) res.getMap().get("person");
							message = (String) res.getMap().get("message");
							boolean next = (user.getPerson() != null);
							if (!next) {
								System.out.println("VALIDANDO");
								Person personDocument = personService.findByDocument(user.getPerson().getDocument());
								if(personDocument != null) {
									System.out.println("VALIDANDO DOCUMENT");
									res = personRest.updatePerson(user.getPerson().update(personDocument));
									personDocument = (Person) res.getMap().get("person");
									next = (personDocument != null);
									message = (!next) ? "No se ha actulizado la información de la persona." : "Se ha actualizado la información de la persona.";
								}
								if(!next) {
									System.out.println("VALIDANDO PERSON Y SOL");
									next = (userService.findByIdAndIdRol(user.getId(), idRole) == null);
									message = (!next) ? "Ya existe ese usuario con ese rol" : message;
								}
							}else {
								user.setPerson(personRegister);
							}
							if (next) {
								System.out.println("registrar usuario");
								PastleyDate date = new PastleyDate();
								// user.setPassword(passwordEncoder.encode(user.getPassword()));
								user.setDateRegister(date.currentToDateTime(null));
								user.setIp(null);
								user.setStatu(true);
								user.setSession(false);
								user.setPoints(0L);
								user.setLastPassword(null);
								user.setDateSession(null);
								user.setDateLastDate(null);
								user.setDateUpdate(null);
								user.setRole(new Role(idRole));
								user = userService.save(user);
								if (user != null) {
									response.add("user", user);
									response.add("message",
											"Se ha registrado el " + name + " con el id " + user.getId() + ".",
											HttpStatus.OK);
								} else {
									response.add("message", "No se ha registrado el " + name + ".", HttpStatus.NO_CONTENT);
								}
							} else {
								response.add("message", "No se ha registrado el " + name + ", " + message,
										HttpStatus.NO_CONTENT);
							}
						}else {
							response.add("message", "No se ha registrado el " + name + ", " + message,
									HttpStatus.NO_CONTENT);
						}

					} else {
						response.add("message",
								"No se ha registrado el " + name + ", No existe un rol con el id " + idRole + ".",
								HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No se ha registrado el " + name + ", " + message + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No se ha registrado el " + name + ", el ID debe ser menor o igual a 0.",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido los datos del " + name + " a registrar.", HttpStatus.NOT_FOUND);
		}
		return response;
	}

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to search for "CASHIER" by ID
	 */
	@GetMapping(value = { "/findById/{id}", "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		User user = userService.findById(id);
		if (user != null) {
			response.add("user", user, HttpStatus.OK);
		} else {
			response.add("message", "No hay ningun usuario registratdo con ese ID " + id + ".", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows you to search for "CUSTOMER" by ID
	 */
	@GetMapping(value = { "/findById/customer/{id}" })
	public ResponseEntity<?> findByIdCustomer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(findByIdUser(id, PastleyVariable.PASTLEY_USER_CUSTOMER_ID, "cliente"));
	}

	/**
	 * Method that allows you to search for "CASHIER" by ID
	 */
	@GetMapping(value = { "/findById/cashier/{id}" })
	public ResponseEntity<?> findByIdCashier(@PathVariable("id") Long id) {
		return ResponseEntity.ok(findByIdUser(id, PastleyVariable.PASTLEY_USER_CASHIER_ID, "cajero"));
	}

	/**
	 * Method that allows you to search for "ADMINISTRATOR" by ID
	 */
	@GetMapping(value = { "/findById/administrator/{id}" })
	public ResponseEntity<?> findByIdAdministrator(@PathVariable("id") Long id) {
		return ResponseEntity.ok(findByIdUser(id, PastleyVariable.PASTLEY_USER_ADMINISTRATOR_ID, "administrador"));
	}

	/**
	 * Method that allows you to search for "CUSTOMER LOGIN" by ID
	 */

	/**
	 * Method that allows you to list all "CASHIER"
	 */
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		PastleyResponse response = new PastleyResponse();
		List<User> list = userService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun cajero registrado.", HttpStatus.NO_CONTENT);
		} else {
			response.add("user", list, HttpStatus.OK);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////

	/**
	 * Method that allows you to register a "ADMINISTRATOR".
	 */
	@PostMapping("/create/administrator")
	public ResponseEntity<?> createAdministrator(@RequestBody User user) {
		return ResponseEntity.ok(createUser(user, PastleyVariable.PASTLEY_USER_ADMINISTRATOR_ID, "administrador"));
	}

	/**
	 * Method that allows you to register a "CUSTOMER".
	 */
	@PostMapping("/create/customer")
	public ResponseEntity<?> createCustore(@RequestBody User user) {
		return ResponseEntity.ok(createUser(user, PastleyVariable.PASTLEY_USER_CUSTOMER_ID, "cliente"));
	}

	/**
	 * Method that allows you to register a "CASHIER".
	 */
	@PostMapping("/create/cashier")
	public ResponseEntity<?> createCashier(@RequestBody User user) {
		return ResponseEntity.ok(createUser(user, PastleyVariable.PASTLEY_USER_CASHIER_ID, "cajero"));
	}

	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a user "CASHIER".
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody User user) {
		PastleyResponse response = new PastleyResponse();
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
