package com.pastley.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pastley.entity.Product;

public interface ProductService {
	
	public Iterable<Product> findAll();
	
	public Page<Product> findAll(Pageable pageable);
	
	public Optional<Product> findById(Long id);
	
	public Product save(Product product);
	
	public void deleteById(long id);

	public Product update(Product product, Long id);

	public List<Product> filter(Long idCategory);

}
