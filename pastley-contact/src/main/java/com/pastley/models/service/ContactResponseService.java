package com.pastley.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.models.dao.ContactResponseRepository;
import com.pastley.models.entity.ContactResponse;
import com.pastley.util.PastleyInterface;

@Service
public class ContactResponseService implements PastleyInterface<Long,ContactResponse>{
	@Autowired
	private ContactResponseRepository contactResponseDao;
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	

	@Override
	public ContactResponse findById(Long id) {
		try {

			return contactResponseDao.findById(id).orElse(null);

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ContactResponse> findAll() {
		try {
			return contactResponseDao.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<ContactResponse> findByStatuAll(boolean statu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactResponse save(ContactResponse entity) {
		try {

			return contactResponseDao.save(entity);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public boolean delete(Long id) {
		try {
			contactResponseDao.deleteById(id);
			
			return contactResponseDao.findById(id)==null;
		}
		catch (Exception e) {
			return false;
		}
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	
	
	
	 
}
