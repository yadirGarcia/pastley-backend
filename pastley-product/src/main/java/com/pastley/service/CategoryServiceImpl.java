package com.pastley.service;

import com.pastley.entity.Category;
import com.pastley.exception.GenericException;
import com.pastley.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"La Categoria no existe en la BD");
        }
        return Optional.of(category);
    }

    @Override
    public Category save(Category category) {
        Category categoryFound= categoryRepository.findByName(category.getName());
        if(categoryFound!=null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"La categoria ya existe");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"La Categoria no existe en la BD");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category, Long id) {
        Category categoryFound= categoryRepository.findById(id).orElse(null);
        Category categoryName= categoryRepository.findByName(category.getName());
        if(categoryFound==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"La Categoria que quiere actulizar no existe en la BD");
        }
        if(categoryName!=null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El nombre de la categoria ya existe");
        }
        categoryFound.setName(category.getName());
        categoryRepository.save(categoryFound);
        return categoryFound;
    }

    @Override
    public Category updateState(Long id) {
        Category categoryFound= categoryRepository.findById(id).orElse(null);
        if(categoryFound==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"La Categoria que quiere actulizar el estado no existe en la BD");
        }
        if(categoryFound.getStatu().equals("Activo")){
            categoryFound.setStatu("Desactivado");
        }else{
            categoryFound.setStatu("Activo");
        }
        categoryRepository.save(categoryFound);
        return categoryFound;
    }
}
