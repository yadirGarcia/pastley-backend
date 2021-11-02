package com.pastley.service;

import com.pastley.entity.Category;


import java.util.Optional;

public interface CategoryService {

    public Iterable<Category> findAll();

    public Optional<Category> findById(Long id);

    public Category save(Category category);

    public void deleteById(long id);

    public Category update(Category category, Long id);

    public Category updateState(Long id);

}
