package com.pastley.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.dao.TypeDocumentDAO;
import com.pastley.entity.TypeDocument;
import com.pastley.util.PastleyInterface;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class TypeDocumentService implements PastleyInterface<Long, TypeDocument>{

	@Autowired
	private TypeDocumentDAO typeDocumentDAO;
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	
	@Override
	public TypeDocument findById(Long id) {
		try {
			return typeDocumentDAO.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	public TypeDocument findByName(String name) {
		try {
			return typeDocumentDAO.findByName(name);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<TypeDocument> findAll() {
		try {
			return typeDocumentDAO.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	@Override
	public TypeDocument save(TypeDocument entity) {
		try {
			return typeDocumentDAO.save(entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			typeDocumentDAO.deleteById(id);
			return findById(id)==null;
		} catch (Exception e) {
			return false;
		}
	}

}
