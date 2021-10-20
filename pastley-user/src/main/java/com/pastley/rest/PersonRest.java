package com.pastley.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.entity.Person;
import com.pastley.service.PersonService;
import com.pastley.util.Response;

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

	@RequestMapping(value="/findById/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Response response = new Response();
		
		Person person = personService.findById(id);
		if(person != null) {
			response.add("person", person, HttpStatus.OK);
		}else {
			response.add("message", "No hay ninguna persona registrada con ese id "+id+".", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response.getMap());
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(){
		Response response = new Response();
		List <Person> list = personService.findAll();
		if(list.isEmpty()) {
			response.add("message", "No hay ninguna persona registrada.", HttpStatus.NOT_FOUND);
		}else {
			response.add("persons", list, HttpStatus.OK);
		}
		
		return ResponseEntity.ok(response.getMap());
	}
	

}
