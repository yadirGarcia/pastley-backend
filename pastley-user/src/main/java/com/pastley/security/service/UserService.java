package com.pastley.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.security.entity.User;
import com.pastley.security.repository.UserRepository;
import com.pastley.util.PastleyInterface;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class UserService implements PastleyInterface<Long, User> {
	
	@Autowired
	private UserRepository userDAO;

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	@Override
	public User findById(Long id) {
		try {
			return userDAO.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	public User findByIdAndIdRol(Long id, Long idRol) {
		try {
			return userDAO.findByIdAndIdRol(id, idRol);
		} catch (Exception e) {
			return null;
		}
	}
	
	public User findByPersonAndIdRol(Long idPerson, Long idRol) {
		try {
			return userDAO.findByPersonAndIdRol(idPerson, idRol);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		try {
			return userDAO.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<User> findByIdRole(Long idRole) {
		try {
			return userDAO.findByIdRole(idRole);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<User> findByIdPerson(Long idPerson) {
		try {
			return userDAO.findByIdPerson(idPerson);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public User save(User entity) {
		try {
			return userDAO.save(entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			userDAO.deleteById(id);
			return findById(id) == null;
		} catch (Exception e) {
			return false;
		}
	}
	
	public User findByNickname(String nickname) {
		try {
			return userDAO.findByNickname(nickname);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean existsByNickname(String userName) {
		return userDAO.existsByNickname(userName);
	}
	
	public boolean existsByEmail(String email) {
		return userDAO.existsByEmail(email) != null;
	}

}
