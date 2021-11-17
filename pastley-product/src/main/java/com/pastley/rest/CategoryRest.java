package com.pastley.rest;

import com.pastley.model.entity.Category;
import com.pastley.model.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryRest {
	
    @Autowired
    private CategoryService categoryService;
    
	@GetMapping(value = { "/find/id/{id}", "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
	}
	
	@GetMapping(value = { "/find/name/{name}" })
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findByName(name));
	}

	@GetMapping(value = { "", "/all" })
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
	}
	
	@GetMapping(value = "/range/all/find/date/register/{start}/{end}")
	public ResponseEntity<?> findByRangeDateRegister(@PathVariable("start") String start, @PathVariable("end") String end) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findByRangeDateRegister(start, end));
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody Category role) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(role, (byte) 1));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Category role) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(role, (byte) 2));
	}
	
	@PutMapping(value = "/update/statu/{id}")
	public ResponseEntity<?> updateRoleStatu(@PathVariable("id") Long id) {
		Category category = categoryService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category, (byte) 3));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));
	}
}
