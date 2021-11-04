package com.pastley.repository;

import java.util.List;

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

	/**
	 * Method that allows you to check the products in the cart by product and status.
	 * @param idProduct, Represents the product id.
	 * @param statu, Represents the status of the product.
	 * @return List of carts.
	 */
	@Query(nativeQuery = false, value = "SELECT c FROM Cart c WHERE c.statu = :statu AND c.idProduct = :idProduct")
	public List<Cart> findByProductAndStatus(Long idProduct, boolean statu);
	
	/**
	 * Method that allows consulting all the products of a client.
	 * @param idCustomer, Represents the customer id.
	 * @return List of carts.
	 */
	public List<Cart> findByIdCustomer(Long idCustomer);
	
	/**
	 * Method that allows to consult all the products of a client and by their status.
	 * @param id, Represents the customer id.
	 * @param statu, Represents the status of the product.
	 * @return List of carts.
	 */
	@Query(nativeQuery = false, value = "SELECT c FROM Cart c WHERE c.statu = :statu AND c.idCustomer = :customer")
	public List<Cart> findByCustomerAndStatus(Long customer, boolean statu);
	
}
