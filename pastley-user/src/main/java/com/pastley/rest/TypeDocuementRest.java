package com.pastley.rest;

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
import com.pastley.service.PersonService;
import com.pastley.service.TypeDocumentService;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyResponse;
import com.pastley.util.PastleyValidate;
import java.util.List;

@RestController
@RequestMapping("typeDocument")
public class TypeDocuementRest {
	@Autowired
	TypeDocumentService typeDocumentService;
	@Autowired
	private PersonService personService;

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////

	/**
	 * Method that allows you to search for type docuemnt by ID
	 */

	@GetMapping(value = { "/findById/{id}", "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();

		TypeDocument typeDocument = typeDocumentService.findById(id);
		if (typeDocument != null) {
			response.add("typeDocument", typeDocument, HttpStatus.OK);
		} else {
			response.add("message", "No hay ningun tipo de dcumento registratdo con ese ID " + id + ".",
					HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows you to list all Type Document
	 */
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		PastleyResponse response = new PastleyResponse();
		List<TypeDocument> list = typeDocumentService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun tipo de docuemnto registrado.", HttpStatus.NO_CONTENT);
		} else {
			response.add("typeDocument", list, HttpStatus.OK);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows you to search for type docuemnt by Name
	 */
	@GetMapping(value = { "/findByName/{name}" })
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		PastleyResponse response = new PastleyResponse();
		TypeDocument typeDocument = typeDocumentService.findByName(name);
		if (typeDocument == null) {
			response.add("message", "No hay ningun tipo de docuemnto registrado con ese nombre " + name + ".",
					HttpStatus.NO_CONTENT);
		} else {
			response.add("typeDocument", typeDocument, HttpStatus.OK);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////

	/**
	 * Method that allows you to register a Type Docuemnts.
	 */
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody TypeDocument typeDocument) {
		PastleyResponse response = new PastleyResponse();

		if (typeDocument != null) {
			if (typeDocument.getId() <= 0) {
				if (PastleyValidate.isChain(typeDocument.getName())) {
					TypeDocument aux = typeDocumentService.findByName(typeDocument.getName());
					if (aux == null) {
						PastleyDate date = new PastleyDate();
						typeDocument.setDateUpdate(null);
						typeDocument.setDateRegister(date.currentToDateTime(null));
						typeDocument.setStatu(true);
						typeDocument.setName(typeDocument.getName().toUpperCase());
						aux = typeDocumentService.save(typeDocument);
						if (aux != null) {
							response.add("typeDocument", aux, HttpStatus.OK);
							response.add("message",
									"Se ha registrado el tipo de docuemnto con el id " + aux.getId() + ".");
						} else {
							response.add("message", "No se ha registrado el tipo de documento.", HttpStatus.NO_CONTENT);
						}
					} else {
						response.add("message", "Ya existe una un tipo de docuemnto registrado con ese nombre '"
								+ typeDocument.getName() + "'.", HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message",
							"No se ha registrado el tipo de documento, debe darle un nombre al tipo de documento.",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No se ha registrado el tipo de docuemnto, el ID debe ser menor o igual a 0.",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido los datos del tipo de docuemnto a registrar.",
					HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a type document.
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody TypeDocument typeDocument) {
		PastleyResponse response = new PastleyResponse();
		if (typeDocument != null) {
			String message = typeDocument.validate(true);
			if (message == null) {
				typeDocument.uppercase();
				TypeDocument aux = typeDocumentService.findById(typeDocument.getId());
				if (aux != null) {
					PastleyDate date = new PastleyDate();
					typeDocument.setDateRegister(aux.getDateRegister());
					typeDocument.setDateUpdate(date.currentToDateTime(null));
					typeDocument.setName(typeDocument.getName().toUpperCase());
					aux = typeDocumentService.save(typeDocument);
					if (aux != null) {
						response.add("typeDocument", aux, HttpStatus.OK);
						response.add("message", "Se ha actualizado el tipo de docuemnto con ID " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha actualizado el tipo de docuemnto.", HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message",
							"No existe ningun tipo de docuemnto con el id " + typeDocument.getId() + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", message, HttpStatus.NO_CONTENT);
			}

		} else {
			response.add("message", "No se ha recibido el tipo de docuemnto.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows updating a type document statu.
	 */
	@PutMapping(value = "/update/{id}/statu")
	public ResponseEntity<?> update(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		if (id > 0) {
			TypeDocument typeDocument = typeDocumentService.findById(id);
			if (typeDocument != null) {
				PastleyDate date = new PastleyDate();
				typeDocument.setDateUpdate(date.currentToDateTime(null));
				typeDocument.setStatu(!typeDocument.isStatu());
				typeDocument = typeDocumentService.save(typeDocument);
				if (typeDocument != null) {
					response.add("typeDocument", typeDocument, HttpStatus.OK);
					response.add("message", "Se ha actualizado el estado a " + typeDocument.isStatu()
							+ " del tipo de documento con id " + id + ".");
				} else {
					response.add("message", "No se ha actualizado el estado del tipo de documento con id " + id + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No existe ningun rol con el tipo de documento " + id + ".",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "El id tipo de documento no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	/**
	 * Method to delete a type Document.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		PastleyResponse response = new PastleyResponse();
		if(id > 0) {
			TypeDocument typeDocument = typeDocumentService.findById(id);
			if(typeDocument != null) {
				List<Person> list= personService.findByIdTypeDocument(id);
				if(list.isEmpty()) {
					if(typeDocumentService.delete(id)) {
						response.add("typeDocument", typeDocument);
						response.add("message", "Se ha eliminado el tipo de documento con id " + id + ".", HttpStatus.OK);
					}else {
						response.add("message", "No se ha eliminado el tipo de documento con id " + id + ".",
								HttpStatus.NO_CONTENT);
					}
				}else {
					response.add("message", "No se ha eliminado el tipo de documento con id " + id + ",porque existen "+list.size()+" personas registradas con ese tipo de documento.",
							HttpStatus.NO_CONTENT);
				}
			}else {
				response.add("message", "No existe ningun tipo de documento con el id " + id + ".", HttpStatus.NO_CONTENT);
			}
		}else {
			response.add("message", "El id del tipo de documento no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

}
