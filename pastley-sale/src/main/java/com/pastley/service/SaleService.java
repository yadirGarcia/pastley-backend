package com.pastley.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

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
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyInterface;
import com.pastley.util.PastleyValidate;
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
	/**
	 * Method that allows you to consult a sale by its id.
	 * 
	 * @param id, Represents the identifier of the sale.
	 * @return Sale.
	 */
	@Override
	public Sale findById(Long id) {
		if(id > 0) {
			Optional<Sale> sale = saleRepository.findById(id);
			if(sale != null) {
				return sale.orElse(null);
			}else {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"No se ha encontrado ninguna venta con el id " + id + ".");
			}
		}else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id de la venta no es valido.");
		}
	}

	///////////////////////////////////////////////////////
	// Method - Find - List
	///////////////////////////////////////////////////////
	/**
	 * Method that allows all sales to be consulted.
	 */
	@Override
	public List<Sale> findAll() {
		return saleRepository.findAll();
	}

	/**
	 * Method that allows you to check sales by their status.
	 * @param statu, Represents the state.
	 * @return List of Sale.
	 */
	@Override
	public List<Sale> findByStatuAll(boolean statu) {
		return saleRepository.findByStatu(statu);
	}
	
	///////////////////////////////////////////////////////
	// Method - Find - List - Range
	///////////////////////////////////////////////////////
	/**
	 * Method that allows consulting the sales that are in a date range.
	 * @param start, Represents the start date.
	 * @param end,   Represents the end date.
	 * @return List of Sale.
	 */
	public List<Sale> findByRangeDateRegister(String start, String end) {
		String array_date[] = findByRangeDateRegisterValidateDate(start, end);
		return saleRepository.findByRangeDateRegister(array_date[0], array_date[1]);
	}
	
	/**
	 * Method that allows to validate the two dates.
	 * 
	 * @param start, Represents the start date.
	 * @param end,   Represents the end date.
	 * @return Array.
	 */
	private String[] findByRangeDateRegisterValidateDate(String start, String end) {
		if (PastleyValidate.isChain(start) && PastleyValidate.isChain(end)) {
			PastleyDate date = new PastleyDate();
			try {
				String array_date[] = { date.formatToDateTime(date.convertToDate(start.replaceAll("-", "/")), null),
						date.formatToDateTime(date.convertToDate(end.replaceAll("-", "/")), null) };
				return array_date;
			} catch (ParseException e) {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"El formato permitido para las fechas es: 'Año-Mes-Dia'.");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido la fecha inicio o la fecha fin.");
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
