package com.pastley.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pastley.dao.ContactDAO;
import com.pastley.entity.Contact;
import com.pastley.util.PastleyInterface;

/**
 * @project Pastley-Sale.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */

@Service
public class ContactService implements PastleyInterface<Long,Contact> {
	
	@Autowired
	private ContactDAO contactDao;
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////

	@Override
	public Contact findById(Long id) {
		try {
			
			return contactDao.findById(id).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Contact> findAll() {
		try {
			return contactDao.findAll();
		}
		catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	 

	 
	@Override
	public Contact save(Contact entity) {
		try {
			return contactDao.save(entity);
		}
		catch (Exception e) {
			 return null;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			contactDao.deleteById(id);
			
			return contactDao.findById(id)==null;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Contact> findByStatuAll(boolean statu) {
		
			try {
				return contactDao.findByStatu(statu);
			} catch (Exception e) {
				return new ArrayList<>();
			}
		
	}

	
}
