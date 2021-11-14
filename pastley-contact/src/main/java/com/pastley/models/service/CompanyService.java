package com.pastley.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.models.dao.CompanyDAO;
import com.pastley.models.entity.Company;
import com.pastley.util.PastleyInterface;

@Service
public class CompanyService implements PastleyInterface<Long, Company> {

	@Autowired
	CompanyDAO companyDao;

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////

	@Override
	public Company findById(Long id) {
		try {

			return companyDao.findById(id).orElse(null);

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Company> findAll() {
		try {
			return companyDao.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Company> findByStatuAll(boolean statu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company save(Company entity) {
		try {

			return companyDao.save(entity);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public boolean delete(Long id) {
		try {
			companyDao.deleteById(id);
			
			return companyDao.findById(id)==null;
		}
		catch (Exception e) {
			return false;
		}
	}

}
