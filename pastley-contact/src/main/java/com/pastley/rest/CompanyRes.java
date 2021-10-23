package com.pastley.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.entity.Company;
import com.pastley.entity.Contact;
import com.pastley.service.CompanyService;
import com.pastley.service.ContactService;
import com.pastley.util.PastleyResponse;

@RestController
@RequestMapping("company")
public class CompanyRes {
	

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "id")
	public  ResponseEntity<?> findById(@PathVariable("id") Long id) {
		PastleyResponse response = new PastleyResponse();
		
		Company company = companyService.findById(id);
		if(company!= null) {
			response.add("company", company, HttpStatus.OK);
		}
		else {
			response.add("message", "No hay ninguna Compañia Registrado con ese ID "+ id+".", HttpStatus.NOT_FOUND);
		}
		return  ResponseEntity.ok(response.getMap());
	}

	@GetMapping
	public  ResponseEntity<?> findAll() {
		PastleyResponse response = new  PastleyResponse();
		List <Company> list= companyService.findAll();
		if(list.isEmpty()) {
			response.add("message","No hay ninguna compañia registrado",HttpStatus.NOT_FOUND);
		}
		else {
			response.add("company",list,HttpStatus.OK);
		}
		return  ResponseEntity.ok(response.getMap());

	}

}
