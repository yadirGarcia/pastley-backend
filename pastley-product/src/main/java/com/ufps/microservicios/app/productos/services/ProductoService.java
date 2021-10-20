package com.ufps.microservicios.app.productos.services;

import com.ufps.microservicios.app.productos.models.entity.Producto;

public interface ProductoService {

	public Iterable<Producto> findAll();

	public java.util.Optional<Producto> findById(Long id);

	public Producto save(Producto producto);

	public void deleteById(Long id);

}
