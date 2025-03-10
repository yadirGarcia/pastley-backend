package com.pastley.rest;

import java.io.Serializable;

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

import com.pastley.model.entity.User;
import com.pastley.model.service.UserService;
import com.pastley.util.PastleyVariable;


@RestController
@RequestMapping("user")
public class UserRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/find/id/{id}", "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}
	
	/**
	 * 
	 * @param nickname
	 * @return
	 */
	@GetMapping(value = { "/find/nickname/{nickname}"})
	public ResponseEntity<?> findByNickname(@PathVariable("nickname") String nickname) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByNickName(nickname));
	}
	
	/**
	 * 
	 * @param documentPerson
	 * @return
	 */
	@GetMapping(value = {"/find/person/document/{documentPerson}"})
	public ResponseEntity<?> findByDocumentPerson(@PathVariable("documentPerson") Long documentPerson) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByDocumentPerson(documentPerson));
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/find/cashier/id/{id}"})
	public ResponseEntity<?> findByIdAndIdRolCashier(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByIdAndIdRol(id, PastleyVariable.PASTLEY_USER_CASHIER_ID));
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/find/customer/id/{id}"})
	public ResponseEntity<?> findByIdAndIdRolCustomer(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByIdAndIdRol(id, PastleyVariable.PASTLEY_USER_CUSTOMER_ID));
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/find/administrator/id/{id}"})
	public ResponseEntity<?> findByIdAndIdRolAdministrator(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByIdAndIdRol(id, PastleyVariable.PASTLEY_USER_ADMINISTRATOR_ID));
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping(value = {"", "/all"})
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	/**
	 * 
	 * @param idRole
	 * @return
	 */
	@GetMapping(value = {"/all/find/role/{idRole}"})
	public ResponseEntity<?> findByIdRole(@PathVariable("idRole") Long idRole) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByIdRole(idRole));
	}
	
	@PostMapping(value = "/customer")
	public ResponseEntity<?> createCustomer(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(user, PastleyVariable.PASTLEY_USER_CUSTOMER_ID, (byte) 1));
	}
	
	@PostMapping(value = "/cashier")
	public ResponseEntity<?> createCashier(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(user, PastleyVariable.PASTLEY_USER_CASHIER_ID, (byte) 1));
	}
	
	@PostMapping(value = "/administrator")
	public ResponseEntity<?> createAdministrator(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(user, PastleyVariable.PASTLEY_USER_ADMINISTRATOR_ID, (byte) 1));
	}
	
	@PutMapping(value = "/customer")
	public ResponseEntity<?> updateCustomer(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(user, PastleyVariable.PASTLEY_USER_CUSTOMER_ID, (byte) 2));
	}
	
	@PutMapping(value = "/cashier")
	public ResponseEntity<?> updateCashier(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(user, PastleyVariable.PASTLEY_USER_CASHIER_ID, (byte) 2));
	}
	
	@PutMapping(value = "/administrator")
	public ResponseEntity<?> updateAdministrator(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(user, PastleyVariable.PASTLEY_USER_ADMINISTRATOR_ID, (byte) 2));
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/update/statu/{id}")
	public ResponseEntity<?> updateStatuCustomer(@PathVariable("id") Long id){
		User user = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(user, 0L, (byte) 3));
	}
}
