package com.pastley.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.pastley.entity.Product;
import com.pastley.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	// Crear un nuevo producto
	@PostMapping("/save")
	public ResponseEntity<?> create(@RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	// Mostrar un producto
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {
		Optional<Product> oProduct = productService.findById(id);

		if (!oProduct.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(oProduct);

	}

	@GetMapping("/category/{id}")
	public ResponseEntity<?> categoryFilter(@PathVariable Long id){
		return ResponseEntity.ok(productService.filter(id));
	}
	
	//Mostrar todos los productos
	@GetMapping
	public List<Product> readAll(){
		
		List<Product> products =StreamSupport
				//recorrer el iterable
				.stream(productService.findAll().spliterator(), false)
				//pasarlo a una lista
				.collect(Collectors.toList());
		return products;
	}
	

	
	
	//Editar un  producto
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Product productDetails){
		return ResponseEntity.ok(productService.update(productDetails,id));
	}
	
	//Borrar un producto
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id){
		
		if(!productService.findById(id).isPresent()) {
			ResponseEntity.notFound().build();
		}
		
		productService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}


