package com.pastley.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pastley.entity.TypePQR;

/**
 * @project Pastley-Sale.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */

@Repository
public interface TypePQRDAO  extends JpaRepository<TypePQR, Long>{
	
	public TypePQR findByName(String name);

}
