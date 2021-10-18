package com.pastley.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pastley.entity.SaleDetail;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>{
	
	public List<SaleDetail> findBySale(Long sale);
}
