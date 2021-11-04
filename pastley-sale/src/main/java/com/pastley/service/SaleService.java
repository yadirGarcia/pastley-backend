package com.pastley.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pastley.entity.Sale;
import com.pastley.feignclients.PersonFeignClient;
import com.pastley.feignclients.ProductFeignClient;
import com.pastley.model.PersonModel;
import com.pastley.model.ProductModel;
import com.pastley.model.UserModel;
import com.pastley.repository.SaleRepository;
import com.pastley.util.PastleyInterface;
import com.pastley.util.exception.PastleyException;

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
	
	@Autowired
	private ProductFeignClient productFeignClient;

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
		if(documentPerson > 0) {
			PersonModel person = personFeignClient.findByDocument(documentPerson);
			if(person == null) {
				throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha encontrado ninguna persona con el documento "+documentPerson+".");
			}
			return person;
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El documento de la persona no es valido.");
		}
	}
	
	public UserModel findUserById(Long idUser) {
		if(idUser > 0) {
			return new UserModel();
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del usuario no es valido.");
		}
	}
	
	public ProductModel findProductById(Long idProduct) {
		if(idProduct > 0) {
			ProductModel product = productFeignClient.findById(idProduct);
			if(product == null) {
				throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha encontrado ningun producto con el id "+idProduct+".");
			}
			return product;
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del producto no es valido.");
		}
	}
}
