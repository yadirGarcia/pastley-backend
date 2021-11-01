package com.pastley.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	 * Method that allows you to check sales by their status.
	 * @param statu, Represents the state.
	 * @return List with sales found.
	 */
	public List<Sale> findByStatu(boolean statu);
	
	/**
	 * Method that allows you to filter the sales that are registered between a range of dates.
	 * @param start, Represents the start date.
	 * @param end, Represents the end date.
	 * @return List with sales found.
	 */
	@Query(nativeQuery = false, value = "SELECT s FROM Sale s WHERE s.dateRegister BETWEEN :start AND :end ORDER BY s.dateRegister")
	public List<Sale> findByRangeDateRegister(@Param("start") String start, @Param("end") String end);
}
