package com.pastley.repository;

import com.pastley.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.pastley.entity.Product;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Product findByName(String name);

    @Query(value = "SELECT * FROM product WHERE id_category = ?1", nativeQuery = true)
    List<Product> findByCategory(Long id);

}
