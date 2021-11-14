package com.pastley.rest;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.models.entity.Company;
import com.pastley.models.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyRes  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping(value = "/find/id/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(companyService.findById(id));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Company company) {
		return ResponseEntity.status(HttpStatus.OK).body(companyService.save(company, (byte) 2));
	}
	
	@PutMapping(value = "/update/{id}/butdget/{butdget}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @PathVariable("butdget") BigInteger butdget) {
		return ResponseEntity.status(HttpStatus.OK).body(companyService.updateButdget(id, butdget));
	}
}
