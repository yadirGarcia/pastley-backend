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

import com.pastley.models.entity.Contact;
import com.pastley.models.entity.TypePQR;
import com.pastley.models.service.ContactService;
import com.pastley.models.service.TypePQRService;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyResponse;

@RestController
@RequestMapping("contact")
public class ContactRes {

	@Autowired
	private ContactService contactService;

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

		Contact contact = contactService.findById(id);
		if (contact != null) {
			response.add("contact", contact, HttpStatus.OK);
		} else {
			response.add("message", "No hay ningun Contacto Registrado con ese ID " + id + ".", HttpStatus.NOT_FOUND);
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
		List<Contact> list = contactService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun contacto registrado", HttpStatus.NOT_FOUND);
		} else {
			response.add("contact", list, HttpStatus.OK);
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
		List<Contact> list = contactService.findByStatuAll(statu);
		if (list.isEmpty()) {
			response.add("message", "No hay ningun Contacto resgitrado con el estado " + statu + ".", HttpStatus.NO_CONTENT);
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
	 * Method that allows you to register a sale.
	 * 
	 * @param sale, Represents the sale to register.
	 * @return The generated response.
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<?> create(@RequestBody Contact method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {
			Contact aux = contactService.findById(method.getId());
			TypePQR axu = typePQRService.findById(method.getIdTypePQR());
			
			if (axu != null) {
				if (method.getMessage() != null) {

					// validar si el pqr existe; que los campos no esten vacios , que el usuario
					// exista ----- contactResponse debe tener contacto
					if (aux == null) {
						PastleyDate date = new PastleyDate();
						method.setStatu(true);
						method.setDateUpdate(null);
						method.setDateRegister(date.currentToDateTime(null));
						aux = contactService.save(method);
						//Email email = new Email("",aux.getIdUsuario(),"","","","");//de, usuario,clave, para
						try {
						//	email.sendMail();

							if (aux != null) {
								response.add("method", aux, HttpStatus.OK);
								response.add("message", "Se ha registrado el Contacto con id " + aux.getId() + ".");
							} else {
								response.add("message", "No se ha registrado el Contacto.", HttpStatus.NO_CONTENT);
							}
						}

						catch (Exception e) {
							response.add("menssage", "No se ha podido Enviar el correo", HttpStatus.NO_CONTENT);
						}

					}

					else {
						response.add("message", "Ya existe un Contacto con ese id '" + method.getId() + "'.",
								HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "Debe Existir un mensaje '", HttpStatus.NO_CONTENT);
				}
			}

			else {
				response.add("message", "El tipo de PQR no Existe'", HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido el Contacto.", HttpStatus.NOT_FOUND);
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
	public ResponseEntity<?> update(@RequestBody Contact method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {
			Contact aux = contactService.findById(method.getId());
			TypePQR axu = typePQRService.findById(method.getIdTypePQR());
			if(aux.isStatu()==true) {
				if (aux != null) {
					PastleyDate date = new PastleyDate();
					method.setDateRegister(aux.getDateRegister());
					method.setDateUpdate(date.currentToDateTime(null));
					aux = contactService.save(method);
					if (aux != null) {
						response.add("method", aux, HttpStatus.OK);
						
						response.add("message", "Se ha actualizado el Contacto con id " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha actualizado el Contacto con id " + method.getId() + ".",
								HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No existe ningun Contacto con el id " + method.getId() + ".",
							HttpStatus.NO_CONTENT);
				}
		}else {
			response.add("message", "El estado del contacto es false.", HttpStatus.NOT_FOUND);
		}
			}
		else {
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
			Contact method = contactService.findById(id);
			if(method != null) {
				method.setStatu(!method.isStatu());
				method = contactService.save(method);
				if(method != null) {
					response.add("method", method, HttpStatus.OK);
					response.add("message", "Se ha actualizado el estado del Contacto con id " + id + ".");
				}else {
					response.add("message", "No se ha actualizado el estado del Contacto con id " + id + ".",
							HttpStatus.NO_CONTENT);
				}
			}else {
				response.add("message", "No existe ningun Contacto con el id " + id + ".", HttpStatus.NOT_FOUND);
			}
		}else {
			response.add("message", "El id del Contacto no es valido.", HttpStatus.NOT_FOUND);
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
		
			Contact method = contactService.findById(id);
			if (method != null) {
				contactService.delete(id);
				response.add("message", "Se ha eliminado el Contacto con id " + id + ".", HttpStatus.OK);
				
			} else {
				response.add("message", "No existe ningun Contacto con el id " + id + ".", HttpStatus.NO_CONTENT);
			}
	return ResponseEntity.ok(response.getMap());
	}

}
