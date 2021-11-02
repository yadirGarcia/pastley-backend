package com.pastley.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.entity.Sale;
import com.pastley.feignclients.PersonFeignClient;
import com.pastley.model.PersonModel;
import com.pastley.repository.SaleRepository;
import com.pastley.util.PastleyInterface;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class SaleService implements PastleyInterface<Long, Sale> {

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private PersonFeignClient personFeignClient;

	///////////////////////////////////////////////////////
	// Method - Find
	///////////////////////////////////////////////////////
	@Override
	public Sale findById(Long id) {
		try {
			return saleRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	///////////////////////////////////////////////////////
	// Method - Find - List
	///////////////////////////////////////////////////////
	@Override
	public List<Sale> findAll() {
		try {
			return saleRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Sale> findByStatuAll(boolean statu) {
		try {
			return saleRepository.findByStatu(statu);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<Sale> findByRangeDateRegister(String start, String end) {
		try {
			return saleRepository.findByRangeDateRegister(start, end);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	///////////////////////////////////////////////////////
	// Method - Save and Update
	///////////////////////////////////////////////////////
	@Override
	public Sale save(Sale entity) {
		try {
			return saleRepository.save(entity);
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
			saleRepository.deleteById(id);
			return findById(id) == null;
		} catch (Exception e) {
			return false;
		}
	}

	///////////////////////////////////////////////////////
	// Method - Other
	///////////////////////////////////////////////////////
	public PersonModel findPersonByDocument(Long documentPerson) {
		try {
			return personFeignClient.findByDocument(documentPerson);
		} catch (Exception e) {
			return null;
		}
	}
}
