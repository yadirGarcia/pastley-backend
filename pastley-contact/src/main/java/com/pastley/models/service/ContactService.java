package com.pastley.models.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pastley.models.entity.Contact;
import com.pastley.models.entity.TypePQR;
import com.pastley.models.repository.ContactRepository;
import com.pastley.util.PastleyDate;
import com.pastley.util.PastleyInterface;
import com.pastley.util.PastleyValidate;
import com.pastley.util.exception.PastleyException;

/**
 * @project Pastley-Sale.
 * @author Soleimy Daniela Gomez Baron.
 * @Github https://github.com/Soleimygomez.
 * @contributors soleimygomez, leynerjoseoa, SerBuitragp jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class ContactService implements PastleyInterface<Long, Contact> {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private TypePQRService typePQRService;

	@Override
	public Contact findById(Long id) {
		if (id <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del contacto no es valido.");
		Optional<Contact> type = contactRepository.findById(id);
		if (!type.isPresent())
			throw new PastleyException(HttpStatus.NOT_FOUND,
					"No se ha encontrado ningun contacto con el id " + id + ".");
		return type.orElse(null);
	}

	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}
	
	public List<Contact> findByUserAll(Long idUser) {
		if(idUser <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del usuario no es valido.");
		return contactRepository.findByIdUser(idUser);
	}
	
	public List<Contact> findByTypePqrAll(Long idTypePqr) {
		if(idTypePqr <= 0)
			throw new PastleyException(HttpStatus.NOT_FOUND, "El id del tipo de pqr no es valido.");
		return contactRepository.findByIdTypePqr(idTypePqr);
	}

	@Override
	public List<Contact> findByStatuAll(boolean statu) {
		return contactRepository.findByStatu(statu);
	}

	public List<Contact> findByRangeDateRegister(String start, String end) {
		if (PastleyValidate.isChain(start) && PastleyValidate.isChain(end)) {
			PastleyDate date = new PastleyDate();
			try {
				String array_date[] = { date.formatToDateTime(date.convertToDate(start.replaceAll("-", "/")), null),
						date.formatToDateTime(date.convertToDate(end.replaceAll("-", "/")), null) };
				return contactRepository.findByRangeDateRegister(array_date[0], array_date[1]);
			} catch (ParseException e) {
				throw new PastleyException(HttpStatus.NOT_FOUND,
						"El formato permitido para las fechas es: 'Año-Mes-Dia'.");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido la fecha inicio o la fecha fin.");
		}
	}

	@Override
	public Contact save(Contact entity) {
		return null;
	}
	
	public Contact save(Contact entity, byte type) {
		if (entity != null) {
			String message = entity.validate(false);
			String messageType = (type == 1) ? "registrar"
					: ((type == 2) ? "actualizar" : ((type == 3) ? "actualizar estado" : "n/a"));
			if (message == null) {
				TypePQR typePQR = typePQRService.findById(entity.getTypePqr().getId());
				entity.setTypePqr(typePQR);
				Contact contact = (entity.getId() != null && entity.getId() > 0) ? saveToUpdate(entity, type) : saveToSave(entity, type);
				contact = contactRepository.save(contact);
				if (contact == null)
					throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha " + messageType + " el contacto.");
				return contact;
			} else {
				throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha " + type + " el contacto, " + message + ".");
			}
		} else {
			throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha recibido el contacto.");
		}
	}
	
	public Contact saveToSave(Contact entity, byte type) {
		PastleyDate date = new PastleyDate();
		entity.setId(0L);
		entity.setDateRegister(date.currentToDateTime(null));
		entity.setDateUpdate(null);
		entity.setStatu(true);
		return entity;
	}
	
	public Contact saveToUpdate(Contact entity, byte type) {
		Contact contact = findById(entity.getId());
		PastleyDate date = new PastleyDate();
		entity.setDateRegister(contact.getDateRegister());
		entity.setDateUpdate(date.currentToDateTime(null));
		entity.setStatu((type == 3) ? !entity.isStatu() : entity.isStatu());
		return entity;
	}

	@Override
	public boolean delete(Long id) {
		findById(id);
		try {
			contactRepository.deleteById(id);
			if (findById(id) == null) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		throw new PastleyException(HttpStatus.NOT_FOUND, "No se ha eliminado el contacto con el id " + id + ".");
	}
}
