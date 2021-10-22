package com.ufps.microservicios.app.productos.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.ufps.microservicios.app.productos.models.entity.Producto;
import com.ufps.microservicios.app.productos.services.ProductoService;

@RestController
@RequestMapping("/products")
public class ProductoController {

	@Autowired
	private ProductoService service;

	
	
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("productos", service.findAll());
		return ResponseEntity.ok(response);
	}
	
	//@GetMapping
	//public ResponseEntity<?> listar() {
		//return ResponseEntity.ok().body(service.findAll());
	//}
	
	

	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {
		Optional<Producto> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());

	}
	
	

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Producto producto) {
		Producto productoDb = service.save(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
	}
	
	
	

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Producto producto, @PathVariable Long id) {
		Optional<Producto> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Producto productoDb = o.get();
		productoDb.setName(producto.getName());
		// productoDb.setFlavor(producto.getFlavor());
		// productoDb.setIva(producto.getIva());
		productoDb.setStock(producto.getStock());
		// productoDb.setStockMin(producto.getStockMin());
		// productoDb.setDimension(producto.getDimension());
		productoDb.setStatus(producto.getStatus());
		// productoDb.setDescripcion(producto.getDescripcion());
		// productoDb.setIngredients(producto.getIngredients());
		// productoDb.setDesc(producto.getDesc());
		productoDb.setPrice(producto.getPrice());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoDb));

	}
	
	
	

	@DeleteMapping
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
