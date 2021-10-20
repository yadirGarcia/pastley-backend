package com.ufps.microservicios.app.productos.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.ufps.microservicios.app.productos.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
	
	

}
 