package com.pastley.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pastley.entity.Sale;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{
	
	/**
	 * 
	 * @param statu
	 * @return
	 */
	public List<Sale> findByStatu(boolean statu);
}
