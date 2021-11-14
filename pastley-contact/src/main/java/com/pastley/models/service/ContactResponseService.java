package com.pastley.models.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pastley.models.dao.ContactResponseRepository;
import com.pastley.models.entity.ContactResponse;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyInterface;
import com.pastley.util.PastleyValidate;
import com.pastley.util.exception.PastleyException;

@Service
public class ContactResponseService implements PastleyInterface<Long, ContactResponse> {

	@Autowired
	private ContactResponseRepository contactResponseRepository;
	
	@Autowired
	private ContactService contactService;

	@Override
	public ContactResponse findById(Long id) {
		if (id <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id de la respuesta del contacto no es valido.");
		Optional<ContactResponse> type = contactResponseRepository.findById(id);
		if (!type.isPresent())
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha encontrado ninguna respuesta del contacto con el id " + id + ".");
		return type.orElse(null);
	}

	@Override
	public List<ContactResponse> findAll() {
		return contactResponseRepository.findAll();
	}

	public List<ContactResponse> findByContactAll(Long idContact) {
		if (idContact <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del contacto no es valido.");
		return contactResponseRepository.findByIdContact(idContact);
	}

	public List<ContactResponse> findByRangeDateRegister(String start, String end) {
		if (PastleyValidate.isChain(start) && PastleyValidate.isChain(end)) {
			PastleyDate date = new PastleyDate();
			try {
				String array_date[] = { date.formatToDateTime(date.convertToDate(start.replaceAll("-", "/")), null),
						date.formatToDateTime(date.convertToDate(end.replaceAll("-", "/")), null) };
				return contactResponseRepository.findByRangeDateRegister(array_date[0], array_date[1]);
			} catch (ParseException e) {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"El formato permitido para las fechas es: 'Año-Mes-Dia'.");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido la fecha inicio o la fecha fin.");
		}
	}

	@Override
	public List<ContactResponse> findByStatuAll(boolean statu) {
		return new ArrayList<>();
	}

	@Override
	public ContactResponse save(ContactResponse entity) {
		if (entity != null) {
			String message = entity.validate(false);
			if (message == null) {
				String messageType = (entity.getId() != null && entity.getId() > 0) ? "actualizar" : "registrar";
				entity.setContact(contactService.findById(entity.getContact().getId()));
				ContactResponse contactResponse = (entity.getId() != null && entity.getId() > 0) ? saveToUpdate(entity)
						: saveToSave(entity);
				if (contactResponse == null)
					throw new PastleyException(HttpStatus.NOT_FOUND,
							"No se ha " + messageType + " la respuesta del contacto.");
				return contactResponse;
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"Se ha presentado un error en la respuesta de contacto, " + message + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido la respuesta del contacto.");
		}
	}

	private ContactResponse saveToSave(ContactResponse entity) {
		PastleyDate date = new PastleyDate();
		entity.setId(0L);
		entity.setDateRegister(date.currentToDateTime(null));
		entity.setDateUpdate(null);
		return entity;
	}

	private ContactResponse saveToUpdate(ContactResponse entity) {
		ContactResponse contactResponse = findById(entity.getId());
		PastleyDate date = new PastleyDate();
		entity.setDateRegister(contactResponse.getDateRegister());
		entity.setDateUpdate(date.currentToDateTime(null));
		return entity;
	}

	@Override
	public boolean delete(Long id) {
		findById(id);
		contactResponseRepository.deleteById(id);
		try {
			if (findById(id) == null) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		throw new PastleyException(HttpStatus.NOT_FOUND,
				"No se ha eliminado la respuesta del contacto con el id " + id + ".");
	}
}
