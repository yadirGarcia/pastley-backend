package com.pastley.service;

import java.util.List;
import java.util.Optional;

import com.pastley.entity.Category;
import com.pastley.exception.GenericException;
import com.pastley.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pastley.entity.Product;
import com.pastley.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ICategoryRepository categoryRepository;
	

	@Override
	@Transactional(readOnly = true)
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(Long id) {
		Product product= productRepository.findById(id).orElse(null);
		if(product==null){
			throw new GenericException(HttpStatus.NOT_FOUND,"El producto no existe en la BD");
		}
		return productRepository.findById(id);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
		Product productFound= productRepository.findByName(product.getName());
		if(productFound!=null){
			throw new GenericException(HttpStatus.BAD_REQUEST,"El producto ya existe en la BD");
		}
		if(category==null){
			throw new GenericException(HttpStatus.BAD_REQUEST,"No se encontro la categoria");
		}
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		Product product= productRepository.findById(id).orElse(null);
		if(product==null){
			throw new GenericException(HttpStatus.NOT_FOUND,"El producto no existe en la BD");
		}
		productRepository.deleteById(id);
		
	}

	@Override
	public Product update(Product product, Long id) {
		Product productFound=  productRepository.findById(id).orElse(null);
		Product productFoundName= productRepository.findByName(product.getName());
		Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
		if(productFound==null){
			throw new GenericException(HttpStatus.NOT_FOUND,"El producto no existe en la BD");
		}
		if(category==null){
			throw new GenericException(HttpStatus.BAD_REQUEST,"No se encontro la categoria");
		}
		if(productFoundName.getId()==id){
			productFound.setName(product.getName());
		}else{
			throw new GenericException(HttpStatus.BAD_REQUEST,"El producto ya existe en la BD " + category.getId());
		}

		productFound.setCategory(category);
		productFound.setName(product.getName());
		productFound.setFlavor(product.getFlavor());
		productFound.setVat(product.getVat());
		productFound.setStock(product.getStock());
		productFound.setStockMin(product.getStockMin());
		productFound.setDimension(product.getDimension());
		productFound.setImage(product.getImage());
		productFound.setStatu(product.getStatu());
		productFound.setDescription(product.getDescription());
		productFound.setIngredients(product.getIngredients());
		productFound.setDiscount(product.getDiscount());
		productFound.setPrice(product.getPrice());
		productRepository.save(productFound);

		return productFound;
	}

	@Override
	public List<Product> filter(Long idCategory) {
		Category category = categoryRepository.findById(idCategory).orElse(null);
		if(category==null){
			throw new GenericException(HttpStatus.NOT_FOUND,"La categoria no existe");
		}
		return productRepository.findByCategory(idCategory);
	}

}
