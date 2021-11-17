package com.pastley.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pastley.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Category findByName(String name);
    
	@Query(nativeQuery = false, value = "SELECT c FROM Category c WHERE c.dateRegister BETWEEN :start AND :end ORDER BY c.dateRegister")
	public List<Category> findByRangeDateRegister(@Param("start") String start, @Param("end") String end);

}
