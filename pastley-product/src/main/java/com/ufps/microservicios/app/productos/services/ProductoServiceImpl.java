package com.ufps.microservicios.app.productos.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufps.microservicios.app.productos.models.entity.Producto;
import com.ufps.microservicios.app.productos.models.repository.ProductoRepository;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Producto> findAll() {
		
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Long id) {
		
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		
		return repository.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {

		repository.deleteById(id);

	}

}
