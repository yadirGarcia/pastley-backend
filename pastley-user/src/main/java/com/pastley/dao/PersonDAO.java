package com.pastley.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pastley.entity.Person;

/**
 * @project Pastley-Sale.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Repository
public interface PersonDAO extends JpaRepository<Person,Long>{
	
	

}
