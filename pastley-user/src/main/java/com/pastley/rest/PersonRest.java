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

import com.pastley.entity.Person;
import com.pastley.entity.TypeDocument;
import com.pastley.entity.User;
import com.pastley.service.PersonService;
import com.pastley.service.TypeDocumentService;
import com.pastley.service.UserService;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyResponse;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@RestController
@RequestMapping("person")
public class PersonRest {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TypeDocumentService typeDocumentService;

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	public PastleyResponse createPerson(Person person) {
		PastleyResponse response = new PastleyResponse();
		if (person != null) {
			if (person.getId() <= 0) {
				String message = person.validate(false);
				if (message == null) {
					TypeDocument type = typeDocumentService.findById(person.getIdTypeDocument());
					if(type != null) {
						Person aux = personService.findByDocument(person.getDocument());
						if (aux == null) {
							aux = personService.findByEmail(person.getEmail());
							if (aux == null) {
								aux = personService.findByPhone(person.getPhone());
								if (aux == null) {
									PastleyDate date = new PastleyDate();
									person.setDateRegister(date.currentToDateTime(null));
									person.setDateUpdate(null);
									person.setName(person.getName().toUpperCase());
									person.setSubname(person.getSubname().toUpperCase());
									aux = personService.save(person);
									if (aux != null) {
										response.add("person", aux, HttpStatus.OK);
										response.add("message",
												"Se ha registrado la persona con el id " + aux.getId() + ".");
									} else {
										response.add("message", "No se ha registrado la persona.", HttpStatus.NO_CONTENT);
									}
								} else {
									response.add("message",
											"Ya existe una persona con ese telefono '" + person.getPhone() + "'.",
											HttpStatus.NO_CONTENT);
								}
							} else {
								response.add("message", "Ya existe una persona con ese email '" + person.getEmail() + "'.",
										HttpStatus.NO_CONTENT);
							}
						} else {
							response.add("message",
									"Ya existe una persona con ese documento '" + person.getDocument() + "'.",
									HttpStatus.NO_CONTENT);
						}
					}else {
						response.add("message", "No se ha registrado la persona, no existe ningun tipo de documento registrado con el id "+person.getIdTypeDocument()+".", HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No se ha registrado la persona, " + message + ".", HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No se ha registrado la persona, el ID debe ser menor o igual a 0.",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido la información de persona a registrar.", HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	public PastleyResponse updatePerson(Person person) {
		PastleyResponse response = new PastleyResponse();
		if (person != null) {
			String message = person.validate(true);
			if (message == null) {
				person.uppercase();
				Person aux = personService.findById(person.getId());
				if (aux != null) {
					PastleyDate date = new PastleyDate();
					person.setDateRegister(aux.getDateRegister());
					person.setDateUpdate(date.currentToDateTime(null));
					person.setName(person.getName().toUpperCase());
					aux = personService.save(person);
					if (aux != null) {
						response.add("person", aux, HttpStatus.OK);
						response.add("message", "Se ha actualizado la persona con ID " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha actualizado la persona.", HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No existe ninguna persona con el id " + person.getId() + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", message, HttpStatus.NO_CONTENT);
			}

		} else {
			response.add("message", "No se ha recibido el rol.", HttpStatus.NOT_FOUND);
		}
		return response;
	}

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to search for people by ID
	 */
	@GetMapping(value = { "/findById/{id}", "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();

		Person person = personService.findById(id);
		if (person != null) {
			response.add("person", person, HttpStatus.OK);
		} else {
			response.add("message", "No hay ninguna persona registrada con ese id " + id + ".", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows you to list all people
	 */
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		PastleyResponse response = new PastleyResponse();
		List<Person> list = personService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ninguna persona registrada.", HttpStatus.NOT_FOUND);
		} else {
			response.add("persons", list, HttpStatus.OK);
		}

		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody Person person) {
		return ResponseEntity.ok(createPerson(person));
	}

	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a person.
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody Person person) {
		return ResponseEntity.ok(updatePerson(person));
	}

	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	/**
	 * Method to delete a person.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		PastleyResponse response = new PastleyResponse();
		if (id > 0) {
			Person person = personService.findById(id);
			if (person != null) {
				List<User> list = userService.findByIdPerson(id);
				if (list.isEmpty()) {
					if (personService.delete(id)) {
						response.add("person", person);
						response.add("message", "Se ha eliminado la persona con id " + id + ".", HttpStatus.OK);
					} else {
						response.add("message", "No se ha eliminado la persona con id " + id + ".",
								HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No se ha eliminado la persona con id " + id + ",porque existen "
							+ list.size() + " usuarios asociados a esta persona.", HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No existe ninguna persona con el id " + id + ".", HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "El id de la perosna no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

}
