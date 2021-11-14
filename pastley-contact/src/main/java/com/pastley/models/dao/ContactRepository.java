package com.pastley.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pastley.models.entity.Contact;

/**
 * @project Pastley-Contact.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	/**
	 * 
	 * @param statu
	 * @return
	 */
	public List<Contact> findByStatu(boolean statu);
	
	/**
	 * 
	 * @param idUser
	 * @return
	 */
	public List<Contact> findByIdUser(Long idUser);
	
	/**
	 * 
	 * @param idTypePqr
	 * @return
	 */
	@Query(nativeQuery = false, value = "SELECT c FROM Contact c WHERE c.typePqr.id = :idTypePqr")
	public List<Contact> findByIdTypePqr(Long idTypePqr);
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	@Query(nativeQuery = false, value = "SELECT c FROM Contact c WHERE c.dateRegister BETWEEN :start AND :end ORDER BY c.dateRegister")
	public List<Contact> findByRangeDateRegister(@Param("start") String start, @Param("end") String end);

}
