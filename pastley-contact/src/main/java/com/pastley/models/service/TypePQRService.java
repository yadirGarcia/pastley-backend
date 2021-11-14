package com.pastley.models.service;

import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyInterface;
import com.pastley.util.PastleyValidate;

import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.models.dao.TypePQRRepository;
import com.pastley.models.entity.TypePQR;
import com.pastley.models.model.StatisticModel;
import com.pastley.util.exception.PastleyException;

@Service
public class TypePQRService implements PastleyInterface<Long, TypePQR> {

	@Autowired
	TypePQRRepository typePQRRepository;

	@Override
	public TypePQR findById(Long id) {
		if (id <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del tipo de pqr no es valido.");
		Optional<TypePQR> type = typePQRRepository.findById(id);
		if (!type.isPresent())
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha encontrado ningun tipo de pqr con el id " + id + ".");
		return type.orElse(null);
	}

	public TypePQR findByName(String name) {
		if (!PastleyValidate.isChain(name))
			throw new PastleyException(HttpStatus.NOT_FOUND, "El nombre del tipo de pqr no es valido.");
		TypePQR type = typePQRRepository.findByName(name);
		if (type == null)
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha encontrado ningun tipo de pqr con el nombre " + name + ".");
		return type;
	}

	@Override
	public List<TypePQR> findAll() {
		return typePQRRepository.findAll();
	}

	@Override
	public List<TypePQR> findByStatuAll(boolean statu) {
		return typePQRRepository.findByStatu(statu);
	}

	public List<TypePQR> findByRangeDateRegister(String start, String end) {
		if (PastleyValidate.isChain(start) && PastleyValidate.isChain(end)) {
			PastleyDate date = new PastleyDate();
			try {
				String array_date[] = { date.formatToDateTime(date.convertToDate(start.replaceAll("-", "/")), null),
						date.formatToDateTime(date.convertToDate(end.replaceAll("-", "/")), null) };
				return typePQRRepository.findByRangeDateRegister(array_date[0], array_date[1]);
			} catch (ParseException e) {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"El formato permitido para las fechas es: 'Año-Mes-Dia'.");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido la fecha inicio o la fecha fin.");
		}
	}

	private Long findByStatisticTypePrivate(Long id) {
		if (id > 0) {
			Long count = typePQRRepository.countByTypePQR(id);
			return count == null ? 0L : count;
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del Typo de PQR no es valido.");
		}
	}

	public StatisticModel<TypePQR> findByStatisticType(Long id) {
		return new StatisticModel<>(findById(id), findByStatisticTypePrivate(id));
	}

	public List<StatisticModel<TypePQR>> findByStatisticTypeAll() {
		try {
			List<TypePQR> methods = typePQRRepository.findByStatu(true);
			List<StatisticModel<TypePQR>> list = new ArrayList<>();
			for (TypePQR mp : methods) {
				list.add(new StatisticModel<TypePQR>(mp, findByStatisticTypePrivate(mp.getId())));
			}
			return list;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public TypePQR save(TypePQR entity) {
		return null;
	}

	public TypePQR save(TypePQR entity, byte type) {
		if (entity != null) {
			String message = entity.validate(false);
			String messageType = (type == 1) ? "registrar"
					: ((type == 2) ? "actualizar" : ((type == 3) ? "actualizar estado" : "n/a"));
			if (message == null) {
				TypePQR typePqr = (entity.getId() != null && entity.getId() > 0) ? saveToUpdate(entity, type) : saveToSave(entity, type);
				typePqr = typePQRRepository.save(typePqr);
				if (typePqr == null)
					throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha " + messageType + " el tipo pqr.");
				return typePqr;
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha " + messageType + " el tipo pqr, " + message + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido el tipo de pqr.");
		}
	}

	private TypePQR saveToSave(TypePQR entity, byte type) {
		if (validateName(entity.getName())) {
			PastleyDate date = new PastleyDate();
			entity.uppercase();
			entity.setId(0L);
			entity.setDateRegister(date.currentToDateTime(null));
			entity.setDateUpdate(null);
			entity.setStatu(true);
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"Ya existe un tipo de pqr con el nombre " + entity.getName() + ".");
		}
		return entity;
	}

	private TypePQR saveToUpdate(TypePQR entity, byte type) {
		TypePQR typePqr = findById(entity.getId());
		if (typePqr != null) {
			boolean isName = (!typePqr.getName().equalsIgnoreCase(entity.getName())) ? validateName(entity.getName())
					: true;
			if (isName) {
				PastleyDate date = new PastleyDate();
				entity.uppercase();
				entity.setDateRegister(typePqr.getDateRegister());
				entity.setDateUpdate(date.currentToDateTime(null));
				entity.setStatu((type == 3) ? !entity.isStatu() : entity.isStatu());
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"Ya existe un tipo de pqr con el nombre " + entity.getName() + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha encontrado ningun tipo de pqr con el id " + entity.getId() + ".");
		}
		return entity;
	}

	private boolean validateName(String name) {
		TypePQR type = null;
		try {
			type = findByName(name);
		} catch (PastleyException e) {
			type = null;
		}
		return (type == null) ? true : false;
	}

	@Override
	public boolean delete(Long id) {
		findById(id);
		Long count = findByStatisticTypePrivate(id);
		if (count == 0L) {
			typePQRRepository.deleteById(id);
			try {
				if (findById(id) == null) {
					return true;
				}
			} catch (PastleyException e) {
				return true;
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha eliminado el tipo de pqr con el id " + id + ", tiene asociado " + count + " contactos.");
		}
		throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha eliminado el tipo de pqr con el id " + id + ".");
	}
}
