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

import com.pastley.models.entity.ContactResponse;
import com.pastley.models.service.ContactResponseService;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyResponse;

@RestController
@RequestMapping("contactResponse")
public class ContactResponseRes {

	@Autowired
	public ContactResponseService contactResponseService;

	@Autowired
	public ContactResponseService contactService;

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

		ContactResponse contactResponse = contactResponseService.findById(id);
		if (contactResponse != null) {
			response.add("contactResponse", contactResponse, HttpStatus.OK);
		} else {
			response.add("message", "No hay ninguna Respuesta Registrado con ese ID " + id + ".", HttpStatus.NOT_FOUND);
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
		List<ContactResponse> list = contactResponseService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ninguna Resgpuesta registrado", HttpStatus.NOT_FOUND);
		} else {
			response.add("contactResponse", list, HttpStatus.OK);
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
	public ResponseEntity<?> create(@RequestBody ContactResponse method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {
			ContactResponse aux = contactResponseService.findById(method.getId());
			if (aux != null) {
				if (method.getResponse() == null) {
					// validar si el pqr existe; que los campos no esten vacios , que el usuario
					// exista ----- contactResponse debe tener contacto
					if (aux == null) {
						PastleyDate date = new PastleyDate();
						method.setDateUpdate(null);
						method.setDateRegister(date.currentToDateTime(null));
						aux = contactService.save(method);
						// Email email = new Email("",aux.getIdUsuario(),"","","","");//de,
						// usuario,clave, para
						try {
							// email.sendMail();

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
				response.add("message", "El tipo del Contacto no Existe'", HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido la respuesta del Contacto.", HttpStatus.NOT_FOUND);
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
	public ResponseEntity<?> update(@RequestBody ContactResponse method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {

			ContactResponse aux = contactResponseService.findById(method.getId());

			if (aux != null) {
				PastleyDate date = new PastleyDate();
				method.setDateRegister(aux.getDateRegister());
				method.setDateUpdate(date.currentToDateTime(null));
				aux = contactResponseService.save(method);
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

		} else {
			response.add("message", "No se ha recibido el PQR.", HttpStatus.NOT_FOUND);
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

		ContactResponse method = contactResponseService.findById(id);
		if (method != null) {
			contactResponseService.delete(id);
			response.add("message", "Se ha eliminado la respuesta del contacto con id " + id + ".", HttpStatus.OK);

		} else {
			response.add("message", "No existe ninguna respuesta del Contacto con el id " + id + ".",
					HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
}
