package com.pastley.service;

import com.pastley.util.PastleyInterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.dao.TypePQRDAO;
import com.pastley.entity.*;

@Service
public class TypePQRService  implements PastleyInterface<Long, TypePQR> {
	
	@Autowired
	TypePQRDAO typePQRDao;
	
	
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	
	@Override
	public TypePQR findById(Long id) {
		try {

			return typePQRDao.findById(id).orElse(null);

		} catch (Exception e) {
			return null;
		}
	}

	public TypePQR findByName(String name) {
		try {
			return typePQRDao.findByName(name);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<TypePQR> findAll() {
		try {
			return typePQRDao.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<TypePQR> findByStatuAll(boolean statu) {
		try {
			return typePQRDao.findByStatu(statu);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public TypePQR save(TypePQR entity) {
		try {
			return typePQRDao.save(entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			typePQRDao.deleteById(id);
			
			return typePQRDao.findById(id)==null;
		}
		catch (Exception e) {
			return false;
		}
	}

}
