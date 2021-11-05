package com.pastley.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pastley.entity.Contact;

/**
 * @project Pastley-Contact.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */
@Repository
public interface ContactDAO extends JpaRepository<Contact, Long> {
	
	/**
	 * 
	 * @param statu
	 * @return
	 */
	public List<Contact> findByStatu(boolean statu);

}
