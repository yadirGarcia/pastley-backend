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

import com.pastley.entity.Role;
import com.pastley.service.RoleService;
import com.pastley.util.PastleyResponse;
import com.pastley.util.PastleyValidate;

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
			response.add("message", "No hay ningun rol registratdo con ese ID " + id + ".", HttpStatus.NOT_FOUND);
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
			response.add("message", "No hay ningun rol registrado.", HttpStatus.NOT_FOUND);
		} else {
			response.add("roles", list, HttpStatus.OK);
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
				if (PastleyValidate.isChain(role.getName())) {
					Role aux = roleService.findByName(role.getName());
					if (aux == null) {
						role.setName(role.getName().toLowerCase());
						role.setDescription(role.getDescription().toLowerCase());
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
				} else {
					response.add("message", "No se ha registrado el rol, debe darle un nombre al rol.",
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
				role.uppercase();
				Role aux = roleService.findById(role.getId());
				if (aux != null) {
					role.setName(role.getName().toLowerCase());
					role.setDescription(role.getDescription().toLowerCase());
					aux = roleService.save(role);
					if (aux != null) {
						response.add("role", aux, HttpStatus.OK);
						response.add("message", "Se ha actualizado el rol con ID " + aux.getId() + ".");
					} else {
						response.add("message", "No se ha registrado el rol.", HttpStatus.NO_CONTENT);
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

}
