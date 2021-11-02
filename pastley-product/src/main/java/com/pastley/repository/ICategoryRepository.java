package com.pastley.repository;

import com.pastley.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category,Long> {

    Category findByName(String name);

}
