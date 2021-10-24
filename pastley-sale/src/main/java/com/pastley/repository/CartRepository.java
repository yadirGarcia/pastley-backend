package com.pastley.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pastley.entity.Cart;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	@Query(nativeQuery = false, value = "SELECT c FROM Cart c WHERE c.statu = :statu AND c.idProduct = :id")
	public Cart findByProductAndStatus(Long id, boolean statu);
}
