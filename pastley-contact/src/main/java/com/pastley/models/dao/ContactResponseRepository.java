package com.pastley.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pastley.models.entity.ContactResponse;

/**
 * @project Pastley-Contact.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */
@Repository
public interface ContactResponseRepository extends JpaRepository<ContactResponse, Long>{
	
	/**
	 * 
	 * @param idContact
	 * @return
	 */
	@Query(nativeQuery = false, value = "SELECT cr FROM ContactResponser cr WHERE cr.contact.id = :idContact")
	public List<ContactResponse> findByIdContact(Long idContact);
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	@Query(nativeQuery = false, value = "SELECT cr FROM ContactResponser cr WHERE cr.dateRegister BETWEEN :start AND :end ORDER BY cr.dateRegister")
	public List<ContactResponse> findByRangeDateRegister(@Param("start") String start, @Param("end") String end);
}
