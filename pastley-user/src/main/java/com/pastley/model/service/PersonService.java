package com.pastley.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.model.entity.Person;
import com.pastley.model.repository.PersonRespository;
import com.pastley.util.PastleyInterface;
/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class PersonService implements PastleyInterface<Long, Person>{
	@Autowired
	private PersonRespository personDAO;
	
	@Override
	public Person findById(Long id) {
		try {
			return personDAO.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Person> findAll() {
		try {
			return personDAO.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	public Person findByPhone(String phone) {
		try {
			return personDAO.findByPhone(phone);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Person findByEmail(String email) {
		try {
			return personDAO.findByPhone(email);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Person findByDocument(Long document) {
		try {
			return personDAO.findByDocument(document);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Person> findByIdTypeDocument(Long idTypeDocument){
		try {
			return personDAO.findByIdTypeDocument(idTypeDocument);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public Person save(Person entity) {
		try {
			return personDAO.save(entity);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Person save(Person entity, byte type) {
		return null;
	}
	
	@Override
	public boolean delete(Long id) {
		try {
			personDAO.deleteById(id);
			return findById(id)==null;
		} catch (Exception e) {
			return false;
		}
	}
}
