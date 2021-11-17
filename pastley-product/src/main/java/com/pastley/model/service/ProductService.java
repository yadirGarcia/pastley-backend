package com.pastley.model.service;

import java.util.List;
import java.util.Optional;

import com.pastley.model.entity.Product;
import com.pastley.model.repository.CategoryRepository;
import com.pastley.model.repository.ProductRepository;
import com.pastley.util.PastleyInterface;
import com.pastley.util.exception.PastleyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements PastleyInterface<Long, Product>{
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Product findById(Long id) {
		if (id <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del producto no es valido.");
		Optional<Product> product = productRepository.findById(id);
		if (!product.isPresent())
			throw new PastleyException(HttpStatus.NOT_FOUND, "No existe ningun producto con el id " + id + ".");
		return product.orElse(null);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public List<Product> findByIdCategory(Long idCategory){
		if(idCategory == null || idCategory <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id de la categoria no es valido.");
		return productRepository.findByCategory(idCategory);
	}

	@Override
	public Product save(Product entity) {
		return null;
	}
	
	public Product save(Product entity, byte type) {
		return null;
	}
	
	public Product saveToSave(Product entity, byte type) {
		return entity;
	}
	
	public Product saveToUpdate(Product entity, byte type) {
		return entity;
	}

	@Override
	public boolean delete(Long id) {
		Product product = findById(id);
		if(!product.isStatu())
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha eliminado el producto con el id " + id + ", tiene el estado activado.");
		if(product.getStock() > 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha eliminado el producto con el id " + id + ", tiene el stock disponible.");
		productRepository.deleteById(id);
		try {
			if (findById(id) == null) {
				return true;
			}
		} catch (PastleyException e) {
			return true;
		}
		throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha eliminado el producto con el id " + id + ".");
	}
}
