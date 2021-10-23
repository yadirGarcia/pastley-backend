package com.pastley.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.util.PastleyInterface;
import com.pastley.entity.Cart;
import com.pastley.repository.CartRepository;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class CartService implements PastleyInterface<Long, Cart>{
	
	@Autowired
	private CartRepository cartRepository;

	///////////////////////////////////////////////////////
	// Method - Find
	///////////////////////////////////////////////////////
	@Override
	public Cart findById(Long id) {
		try {
			return cartRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	///////////////////////////////////////////////////////
	// Method - Find - List
	///////////////////////////////////////////////////////
	@Override
	public List<Cart> findAll() {
		try {
			return cartRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Cart> findByStatuAll(boolean statu) {
		return new ArrayList<>();
	}
	
	///////////////////////////////////////////////////////
	// Method - Save and Update
	///////////////////////////////////////////////////////
	@Override
	public Cart save(Cart entity) {
		try {
			return cartRepository.save(entity);
		} catch (Exception e) {
			return null;
		}
	}
	
	///////////////////////////////////////////////////////
	// Method - Save and Update
	///////////////////////////////////////////////////////
	@Override
	public boolean delete(Long id) {
		try {
			cartRepository.deleteById(id);
			return findById(id) == null;
		} catch (Exception e) {
			return false;
		}
	}

}
