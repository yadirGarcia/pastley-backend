package com.pastley.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.entity.SaleDetail;
import com.pastley.repository.SaleDetailRepository;
import com.pastley.util.PastleyInterface;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class SaleDetailService implements PastleyInterface<Long, SaleDetail> {
	
	@Autowired
	private SaleDetailRepository saleDetailRepository;
	
	///////////////////////////////////////////////////////
	// Method - Find
	///////////////////////////////////////////////////////
	@Override
	public SaleDetail findById(Long id) {
		try {
			return saleDetailRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	///////////////////////////////////////////////////////
	// Method - Find - List
	///////////////////////////////////////////////////////
	@Override
	public List<SaleDetail> findAll() {
		try {
			return saleDetailRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<SaleDetail> findByStatuAll(boolean statu) {
		return new ArrayList<>();
	}
	
	public List<SaleDetail> findBySale(Long sale){
		try {
			return saleDetailRepository.findBySale(sale);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	///////////////////////////////////////////////////////
	// Method - Save and Update
	///////////////////////////////////////////////////////
	@Override
	public SaleDetail save(SaleDetail entity) {
		try {
			return saleDetailRepository.save(entity);
		} catch (Exception e) {
			return null;
		}
	}

	///////////////////////////////////////////////////////
	// Method - Delete
	///////////////////////////////////////////////////////
	@Override
	public boolean delete(Long id) {
		try {
			saleDetailRepository.deleteById(id);
			return findById(id) == null;
		} catch (Exception e) {
			return false;
		}
	}
}
