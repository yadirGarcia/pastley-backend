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

import com.pastley.security.entity.Role;
import com.pastley.security.entity.User;
import com.pastley.security.service.RoleService;
import com.pastley.security.service.UserService;
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
@RequestMapping("role")
public class RoleRest {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////

	/**
	 * Method that allows you to search for role by ID
	 */
	@GetMapping(value = { "/findById/{id}", "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();

		Role role = roleService.findById(id);
		if (role != null) {
			response.add("role", role, HttpStatus.OK);
		} else {
			response.add("message", "No hay ningun rol registratdo con ese ID " + id + ".", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

	/**
	 * Method that allows you to list all role
	 */
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		PastleyResponse response = new PastleyResponse();
		List<Role> list = roleService.findAll();
		if (list.isEmpty()) {
			response.add("message", "No hay ningun rol registrado.", HttpStatus.NO_CONTENT);
		} else {
			response.add("roles", list, HttpStatus.OK);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows you to search for role by Name
	 */
	@GetMapping(value = { "/findByName/{name}"})
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		PastleyResponse response = new PastleyResponse();
		Role role = roleService.findByName(name);
		if (role == null) {
			response.add("message", "No hay ningun rol registrado con ese nombre "+name+".", HttpStatus.NO_CONTENT);
		} else {
			response.add("role", role, HttpStatus.OK);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to register a role.
	 */
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody Role role) {
		PastleyResponse response = new PastleyResponse();
		if (role != null) {
			if (role.getId() <= 0) {
				String message = role.validate(false);
				if(message != null) {
					Role aux = roleService.findByName(role.getName().name());
					if (aux == null) {
						PastleyDate date = new PastleyDate();
						role.setDateUpdate(null);
						role.setDateRegister(date.currentToDateTime(null));
						role.setStatu(true);
						aux = roleService.save(role);
						if (aux != null) {
							response.add("role", aux, HttpStatus.OK);
							response.add("message", "Se ha registrado el rol con el id " + aux.getId() + ".");
						} else {
							response.add("message", "No se ha registrado el rol.", HttpStatus.NO_CONTENT);
						}
					} else {
						response.add("message",
								"Ya existe una un rol registrado con ese nombre '" + role.getName() + "'.",
								HttpStatus.NO_CONTENT);
					}
				}else {
					response.add("message",
							"No se ha registrado el rol, "+message+".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", "No se ha registrado el rol, el ID debe ser menor o igual a 0.",
						HttpStatus.NO_CONTENT);
			}
		} else {
			response.add("message", "No se ha recibido los datos del rol a registrar.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Put
	///////////////////////////////////////////////////////
	/**
	 * Method that allows updating a role.
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody Role role) {
		PastleyResponse response = new PastleyResponse();
		if (role != null) {
			String message = role.validate(true);
			if (message == null) {
				Role aux = roleService.findById(role.getId());
				if (aux != null) {
					PastleyDate date = new PastleyDate();
					role.setDateRegister(aux.getDateRegister());
					role.setDateUpdate(date.currentToDateTime(null));
					aux = roleService.save(role);
					if (aux != null) {
						response.add("role", aux, HttpStatus.OK);
						response.add("message", "Se ha actualizado el rol con ID " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha actualizado el rol.", HttpStatus.NO_CONTENT);
					}
				} else {
					response.add("message", "No existe ningun rol con el id " + role.getId() + ".",
							HttpStatus.NO_CONTENT);
				}
			} else {
				response.add("message", message, HttpStatus.NO_CONTENT);
			}

		} else {
			response.add("message", "No se ha recibido el rol.", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	/**
	 * Method that allows updating a role statu.
	 */
	@PutMapping(value = "/update/{id}/statu")
	public ResponseEntity<?> update(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		if(id > 0) {
			Role role = roleService.findById(id); 
			if(role != null) {
				PastleyDate date = new PastleyDate();
				role.setDateUpdate(date.currentToDateTime(null));
				role.setStatu(!role.isStatu());
				role = roleService.save(role);
				if(role != null) {
					response.add("role", role, HttpStatus.OK);
					response.add("message", "Se ha actualizado el estado a "+role.isStatu()+" del rol con id "+id+".");
				}else {
					response.add("message", "No se ha actualizado el estado del rol con id "+id+".", HttpStatus.NO_CONTENT);
				}
			}else {
				response.add("message", "No existe ningun rol con el id " + id + ".",
						HttpStatus.NO_CONTENT);
			}
		}else {
			response.add("message", "El id rol no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}

	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	/**
	 * Method to delete a role.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		PastleyResponse response = new PastleyResponse();
		if(id > 0) {
			Role role = roleService.findById(id);
			if(role != null) {
				List<User> list= userService.findByIdRole(id);
				if(list.isEmpty()) {
					if(roleService.delete(id)) {
						response.add("role", role);
						response.add("message", "Se ha eliminado el rol con id " + id + ".", HttpStatus.OK);
					}else {
						response.add("message", "No se ha eliminado el rol con id " + id + ".",
								HttpStatus.NO_CONTENT);
					}
				}else {
					response.add("message", "No se ha eliminado el rol con id " + id + ",porque existen "+list.size()+" usuarios registrados con ese rol.",
							HttpStatus.NO_CONTENT);
				}
			}else {
				response.add("message", "No existe ningun rol con el id " + id + ".", HttpStatus.NO_CONTENT);
			}
		}else {
			response.add("message", "El id del rol no es valido.", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(response.getMap());
	}
}
