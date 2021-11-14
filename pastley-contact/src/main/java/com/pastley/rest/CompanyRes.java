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

import com.pastley.models.entity.Company;
import com.pastley.models.service.CompanyService;
import com.pastley.util.PastleyResponse;

@RestController
@RequestMapping("company")
public class CompanyRes {

	@Autowired
	private CompanyService companyService;

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
		Company company = companyService.findById(id);
		if (company != null) {
			response.add("company", company, HttpStatus.OK);
		} else {
			response.add("message", "No hay ninguna Compañia Registrado con ese ID " + id + ".", HttpStatus.NOT_FOUND);
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
		List<Company> list = companyService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ninguna compañia registrado", HttpStatus.NOT_FOUND);
		} else {
			response.add("company", list, HttpStatus.OK);
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
	public ResponseEntity<?> create(@RequestBody Company method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {

			if (method.getMission() != null) {
				if (method.getName() != null) {
					if (method.getPassword() != null) {
						if (method.getVision() != null) {
							if (method.getAlertMinStock() != null) {
								if (method.getAddress() != null) {
									if (method.getButdget() != null) {
										if (method.getEmail() != null) {
											if (method.getAboutUs() != null) {
												if (method.getSize() != null) {
													companyService.save(method);
												} else {
													response.add("message", "Se Requiere ese campo",
															HttpStatus.NO_CONTENT);
												}
											} else {
												response.add("message", "Se Requiere ese campo", HttpStatus.NO_CONTENT);
											}
										} else {
											response.add("message", "Se Requiere ese campo", HttpStatus.NO_CONTENT);

										}
									} else {
										response.add("message", "Se Requiere ese campo", HttpStatus.NO_CONTENT);
									}

								} else {
									response.add("message", "Se Requiere ese campo", HttpStatus.NO_CONTENT);
								}
							} else {
								response.add("message", "Se Requiere ese campo", HttpStatus.NO_CONTENT);
							}
						} else {
							response.add("message", "Se Requiere ese campo", HttpStatus.NO_CONTENT);
						}
					} else {
						response.add("message", "Se Requiere ese campo", HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "Se Requiere ese campo", HttpStatus.NO_CONTENT);

				}
			} else {
				response.add("message", "Se Requiere ese campo'", HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido la Compañia.", HttpStatus.NOT_FOUND);
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
	public ResponseEntity<?> update(@RequestBody Company method) {
		PastleyResponse response = new PastleyResponse();
		if (method != null) {
			Company aux = companyService.findById(method.getId());

			if (aux != null) {

				aux = companyService.save(method);
				if (aux != null) {
					response.add("method", aux, HttpStatus.OK);

					response.add("message", "Se ha actualizado la Compañia con id " + aux.getId() + ".");
				} else {
					response.add("message", "No se ha actualizado la compañia con id " + method.getId() + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No existe una compañia con ese id '" + method.getId() + "'.",
						HttpStatus.NO_CONTENT);
			}

		} else {
			response.add("message", "No se ha recibido la Compañia.", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(response.getMap());

	}

}
