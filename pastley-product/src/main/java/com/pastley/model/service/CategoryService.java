package com.pastley.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pastley.model.entity.Category;
import com.pastley.model.entity.Product;
import com.pastley.model.repository.CategoryRepository;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyInterface;
import com.pastley.util.PastleyValidate;
import com.pastley.util.exception.PastleyException;

@Service
public class CategoryService implements PastleyInterface<Long, Category>{

    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    ProductService productService;

	@Override
	public Category findById(Long id) {
		if (id <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id de la categoria no es valido.");
		Optional<Category> category = categoryRepository.findById(id);
		if (!category.isPresent())
			throw new PastleyException(HttpStatus.NOT_FOUND, "No existe ninguna categoria con el id " + id + ".");
		return category.orElse(null);
	}
	
	public Category findByName(String name) {
		if (!PastleyValidate.isChain(name))
			throw new PastleyException(HttpStatus.NOT_FOUND, "El nombre de la categoria no es valido.");
		Category category = categoryRepository.findByName(name);
		if (category == null)
			throw new PastleyException(HttpStatus.NOT_FOUND, "No existe ninguna categoria con el nombre " + name + ".");
		return category;
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	/**
	 * 
	 */
	@Override
	public Category save(Category entity) {
		return null;
	}

	public Category save(Category entity, byte type) {
		if (entity != null) {
			String message = entity.validate(false);
			String messageType = (type == 1) ? "registrar"
					: ((type == 2) ? "actualizar" : ((type == 3) ? "actualizar estado" : "n/a"));
			if (message == null) {
				Category category = (entity.getId() != null && entity.getId() > 0) ? saveToUpdate(entity, type) : saveToSave(entity, type);
				category = categoryRepository.save(category);
				if (category != null) {
					return category;
				} else {
					throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha " + messageType + " el rol.");
				}
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha " + messageType + " el rol, " + message + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido la categoria.");
		}
	}

	private Category saveToSave(Category entity, byte type) {
		if (validateName(entity.getName())) {
			PastleyDate date = new PastleyDate();
			entity.uppercase();
			entity.setId(0L);
			entity.setDateRegister(date.currentToDateTime(null));
			entity.setDateUpdate(null);
			entity.setStatu(true);
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"Ya existe una categoria con el nombre " + entity.getName() + ".");
		}
		return entity;
	}

	private Category saveToUpdate(Category entity, byte type) {
		Category category = findById(entity.getId());
		if (category != null) {
			boolean isName = (!category.getName().equalsIgnoreCase(entity.getName())) ? validateName(entity.getName())
					: true;
			if (isName) {
				PastleyDate date = new PastleyDate();
				entity.uppercase();
				entity.setDateRegister(category.getDateRegister());
				entity.setDateUpdate(date.currentToDateTime(null));
				entity.setStatu((type == 3) ? !entity.isStatu() : entity.isStatu());
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"Ya existe un rol con el nombre " + entity.getName() + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha encontrado rol con el id " + entity.getId() + ".");
		}
		return entity;
	}
	
	@Override
	public boolean delete(Long id) {
		findById(id);
		List<Product> list  = productService.findByIdCategory(id);
		if(!list.isEmpty())
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha eliminado la categoria con el id  "+id +  ", tiene asociado "+list.size()+" productos.");
		categoryRepository.deleteById(id);
		try {
			if (findById(id) == null) {
				return true;
			}
		} catch (PastleyException e) {
			return true;
		}
		throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha eliminado la categoria con el id " + id + ".");
	}
	
	private boolean validateName(String name) {
		Category category = null;
		try {
			category = findByName(name);
		} catch (PastleyException e) {
		}
		return (category == null) ? true : false;
	}
}
