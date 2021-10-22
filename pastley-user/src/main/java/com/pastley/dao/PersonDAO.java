package com.pastley.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pastley.entity.Person;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {

	public Person findByPhone(String phone);
	public Person findByEmail(String email);
	public Person findByDocument(Long document);
}
