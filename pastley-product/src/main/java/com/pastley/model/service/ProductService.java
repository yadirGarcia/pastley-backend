package com.pastley.model.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.pastley.model.entity.Product;
import com.pastley.model.repository.ProductRepository;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyInterface;
import com.pastley.util.PastleyValidate;
import com.pastley.util.exception.PastleyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements PastleyInterface<Long, Product>{
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;

	@Override
	public Product findById(Long id) {
		if (id <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del producto no es valido.");
		Optional<Product> product = productRepository.findById(id);
		if (!product.isPresent())
			throw new PastleyException(HttpStatus.NOT_FOUND, "No existe ningun producto con el id " + id + ".");
		return product.orElse(null);
	}
	
	public Product findByName(String name) {
		if (!PastleyValidate.isChain(name))
			throw new PastleyException(HttpStatus.NOT_FOUND, "El nombre producto no es valido.");
		Product product = productRepository.findByName(name);
		if (product == null)
			throw new PastleyException(HttpStatus.NOT_FOUND, "No existe ningun producto con el nombre " + name + ".");
		return product;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public List<Product> findProductByPromotionAll() {
		return productRepository.findProductByPromotion();
	}
	
	public List<Product> findByStatuAll(boolean statu) {
		return productRepository.findByStatu(statu);
	}
	
	public List<Product> findByIdCategory(Long idCategory){
		if(idCategory == null || idCategory <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id de la categoria no es valido.");
		return productRepository.findByIdCategory(idCategory);
	}
	
	public List<Product> findByRangeDateRegister(String start, String end) {
		if (PastleyValidate.isChain(start) && PastleyValidate.isChain(end)) {
			PastleyDate date = new PastleyDate();
			try {
				String array_date[] = { date.formatToDateTime(date.convertToDate(start.replaceAll("-", "/")), null),
						date.formatToDateTime(date.convertToDate(end.replaceAll("-", "/")), null) };
				return productRepository.findByRangeDateRegister(array_date[0], array_date[1]);
			} catch (ParseException e) {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"El formato permitido para las fechas es: 'Año-Mes-Dia'.");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido la fecha inicio o la fecha fin.");
		}
	}

	@Override
	public Product save(Product entity) {
		return null;
	}
	
	public Product save(Product entity, byte type) {
		if(entity != null) {
			String message = entity.validate(false);
			String messageType = (type == 1) ? "registrar"
					: ((type == 2) ? "actualizar" : ((type == 3) ? "actualizar estado" : "n/a"));
			if (message == null) {
				Product product = (entity.getId() != null && entity.getId() > 0) ? saveToUpdate(entity, type) : saveToSave(entity, type);
				if(type == 1 || type == 2)
					product.setCategory(categoryService.findById(entity.getCategory().getId()));
				product = productRepository.save(product);
				if (product != null) {
					return product;
				} else {
					throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha " + messageType + " el producto.");
				}
			}else {
				throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha " + messageType + " el producto, " + message);
			}
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido el producto.");
		}
	}
	
	public Product saveToSave(Product entity, byte type) {
		if (validateName(entity.getName())) {
			PastleyDate date = new PastleyDate();
			entity.uppercase();
			entity.setId(0L);
			entity.setDateRegister(date.currentToDateTime(null));
			entity.setDateUpdate(null);
			entity.setStatu(true);
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"Ya existe un producto con el nombre " + entity.getName() + ".");
		}
		return entity;
	}
	
	public Product saveToUpdate(Product entity, byte type) {
		Product product = findById(entity.getId());
		if (product != null) {
			boolean isName = (!product.getName().equalsIgnoreCase(entity.getName())) ? validateName(entity.getName())
					: true;
			if (isName) {
				PastleyDate date = new PastleyDate();
				entity.uppercase();
				entity.setDateRegister(product.getDateRegister());
				entity.setDateUpdate(date.currentToDateTime(null));
				entity.setStatu((type == 3) ? !entity.isStatu() : entity.isStatu());
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"Ya existe un producto con el nombre " + entity.getName() + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha encontrado el producto con el id " + entity.getId() + ".");
		}
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
	
	private boolean validateName(String name) {
		Product product = null;
		try {
			product = findByName(name);
		} catch (PastleyException e) {
		}
		return (product == null) ? true : false;
	}
}
