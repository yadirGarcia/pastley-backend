package com.pastley.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pastley.model.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Product findByName(String name);

    @Query(value = "SELECT * FROM product WHERE id_category = ?1", nativeQuery = true)
    List<Product> findByCategory(Long id);

}
