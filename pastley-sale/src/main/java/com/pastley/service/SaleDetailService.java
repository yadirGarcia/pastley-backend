package com.pastley.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pastley.entity.SaleDetail;
import com.pastley.repository.SaleDetailRepository;
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
public class SaleDetailService implements PastleyInterface<Long, SaleDetail> {

	@Autowired
	private SaleDetailRepository saleDetailRepository;

	@Autowired
	private SaleService saleService;

	///////////////////////////////////////////////////////
	// Method - Find
	///////////////////////////////////////////////////////
	/**
	 * Method that allows to know a sale detail by its id.
	 * 
	 * @param id, Represents the identifier of the detail sale.
	 * @return SaleDetail.
	 */
	@Override
	public SaleDetail findById(Long id) {
		if (id > 0) {
			Optional<SaleDetail> saleDetail = saleDetailRepository.findById(id);
			if (saleDetail.isPresent()) {
				return saleDetail.orElse(null);
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"No se ha encontrado ningun detalle de venta con el id " + id + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del del detalle de venta no es valido.");
		}
	}

	///////////////////////////////////////////////////////
	// Method - Find - List
	///////////////////////////////////////////////////////
	/**
	 * Method that allows to know all the details of registered sales.
	 * 
	 * @return List of SaleDetail.
	 */
	@Override
	public List<SaleDetail> findAll() {
		return saleDetailRepository.findAll();
	}

	/**
	 * N/A.
	 */
	@Override
	public List<SaleDetail> findByStatuAll(boolean statu) {
		return new ArrayList<>();
	}

	/**
	 * Method that allows knowing all the sale details by the id of a sale.
	 * 
	 * @param idSale, Represents the id of the sale detail.
	 * @return List of SaleDetail.
	 */
	public List<SaleDetail> findBySale(Long idSale) {
		saleService.findById(idSale);
		return saleDetailRepository.findByIdSale(idSale);
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
	/**
	 * Method that allows you to delete a sale detail.
	 * 
	 * @param id, Represents the identifier of the detail sale.
	 */
	@Override
	public boolean delete(Long id) {
		findById(id);
		saleDetailRepository.deleteById(id);
		try {
			if (findById(id) == null) {
				return true;
			}
		} catch (PastleyException e) {
			return true;
		}
		throw new PastleyException(HttpStatus.NOT_FOUND,
				"No se ha eliminado el detalle de venta con el id " + id + ".");
	}
}
