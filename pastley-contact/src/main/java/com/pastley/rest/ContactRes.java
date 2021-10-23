package com.pastley.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.entity.Contact;
import com.pastley.service.ContactService;
import com.pastley.util.PastleyResponse;

@RestController
@RequestMapping("contact")
public class ContactRes {

	@Autowired
	private ContactService contactService;

	
	@RequestMapping(value = "id")
	public  ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		
		Contact contact = contactService.findById(id);
		if(contact!= null) {
			response.add("contact", contact, HttpStatus.OK);
		}
		else {
			response.add("message", "No hay ningun Contacto Registrado con ese ID "+ id+".", HttpStatus.NOT_FOUND);
		}
		return  ResponseEntity.ok(response.getMap());
	}

	@GetMapping
	public  ResponseEntity<?> findAll() {
		PastleyResponse response = new  PastleyResponse();
		List <Contact> list= contactService.findAll();
		if(list.isEmpty()) {
			response.add("message","No hay ningun contacto registrado",HttpStatus.NOT_FOUND);
		}
		else {
			response.add("contact",list,HttpStatus.OK);
		}
		return  ResponseEntity.ok(response.getMap());

	}
}
