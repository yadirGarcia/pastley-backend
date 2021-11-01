package com.pastley.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pastley.entity.TypePQR;
import java.util.List;

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

}
