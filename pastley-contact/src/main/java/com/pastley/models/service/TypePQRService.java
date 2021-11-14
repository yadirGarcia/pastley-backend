package com.pastley.models.service;

import com.pastley.util.PastleyInterface;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.models.dao.TypePQRDAO;
import com.pastley.models.entity.TypePQR;
import com.pastley.models.model.StatisticModel;
import com.pastley.util.exception.PastleyException;

@Service
public class TypePQRService implements PastleyInterface<Long, TypePQR> {

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

			return typePQRDao.findById(id) == null;
		} catch (Exception e) {
			return false;
		}
	}

	///////////////////////////////////////////////////////
	// Method - Find - Statistic
	///////////////////////////////////////////////////////
	private Long findByStatisticTypePrivate(Long id) {
		if (id > 0) {
			Long count = typePQRDao.countByTypePQR(id);
			return count == null ? 0L : count;
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del Typo de PQR no es valido.");
		}
	}

	public StatisticModel<TypePQR> findByStatisticType(Long id) {
		return new StatisticModel<>(findById(id), findByStatisticTypePrivate(id));
	}

	///////////////////////////////////////////////////////
	// Method - Find - Statistic - List
	///////////////////////////////////////////////////////
	public List<StatisticModel<TypePQR>> findByStatisticTypeAll() {
		try {
			List<TypePQR> methods =  typePQRDao.findByStatu(true);
			List<StatisticModel<TypePQR>> list = new ArrayList<>();
			for (TypePQR mp : methods) {
				list.add(new StatisticModel<TypePQR>(mp, findByStatisticTypePrivate(mp.getId())));
			}
			return list;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

}
