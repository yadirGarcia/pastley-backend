package com.pastley.rest;

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

import com.pastley.model.entity.TypeDocument;
import com.pastley.model.service.TypeDocumentService;

@RestController
@RequestMapping("typeDocument")
public class TypeDocuementRest {
	
	@Autowired
	TypeDocumentService typeDocumentService;

	///////////////////////////////////////////////////////
	// Method - Get
	///////////////////////////////////////////////////////

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/find/id/{id}", "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(typeDocumentService.findById(id));
	}
	
	@GetMapping(value = { "/find/name/{name}" })
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(typeDocumentService.findByName(name));
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(value = { "", "/all" })
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(typeDocumentService.findAll());
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody TypeDocument typeDocument) {
		return ResponseEntity.status(HttpStatus.OK).body(typeDocumentService.save(typeDocument, (byte) 1));
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody TypeDocument typeDocument) {
		return ResponseEntity.status(HttpStatus.OK).body(typeDocumentService.save(typeDocument, (byte) 2));
	}
	
	@PutMapping(value = "/update/statu/{id}")
	public ResponseEntity<?> updateStatu(@PathVariable("id") Long id) {
		TypeDocument typeDocument = typeDocumentService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(typeDocumentService.save(typeDocument, (byte) 3));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(typeDocumentService.delete(id));
	}
}
