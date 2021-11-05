package com.pastley.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pastley.entity.TypePQR;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @project Pastley-Contact.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */

@Repository
public interface TypePQRDAO  extends JpaRepository<TypePQR, Long>{
	
	/**
	 * Method that allows you to consult a payment method by name
	 * @param name, Represents the name.
	 * @return The payment method found.
	 */
	public TypePQR findByName(String name);
	
	/**
	 * Method that allows you to check all the payment methods for their status.
	 * @param statu, Represents the state.
	 * @return A list with the payment methods found.
	 */
	public List<TypePQR> findByStatu(boolean statu);
	
	/**
	 * Method that allows filtering the payment methods that are registered between a date range.
	 * @param start, Represents the start date.
	 * @param end, Represents the end date.
	 * @return A list with the payment methods found.
	 */
	@Query(nativeQuery = false, value = "SELECT ty FROM TypePQR ty WHERE ty.dateRegister BETWEEN :start AND :end ORDER BY ty.dateRegister")
	public List<TypePQR> findByRangeDateRegister(@Param("start") String start, @Param("end") String end);
	
	/**
	 * Method that allows to know the amount of sales made by a payment method.
	 * @return A list with the payment methods found.
	 */
	@Query(nativeQuery = false, value = "SELECT COUNT(s.id) FROM TypePQR s WHERE s.id = :id GROUP BY s.id")
	public Long countByTypePQR(Long id);

}
