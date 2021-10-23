package com.pastley.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	RoleService roleService;
	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////

	///////////////////////////////////////////////////////
	// Method - Post
	///////////////////////////////////////////////////////
	/**
	 * Method that allows you to register a role.
	 * 
	 * @param method, Represents the payment method to register.
	 * @return The generated response.
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
	
	
	

}
