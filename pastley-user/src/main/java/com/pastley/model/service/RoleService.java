package com.pastley.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastley.model.entity.Role;
import com.pastley.model.repository.RoleRepository;
import com.pastley.util.PastleyInterface;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Service
public class RoleService implements PastleyInterface<Long, Role> {
	
	@Autowired
	private RoleRepository roleDAO;

	@Override
	public Role findById(Long id) {
		try {
			return roleDAO.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Role> findAll() {
		try {
			return roleDAO.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	public Role findByName(String name) {
		try {
			return roleDAO.findByName(name);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Role save(Role entity) {
		try {
			return roleDAO.save(entity);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean delete(Long id) {
		try {
			roleDAO.deleteById(id);
			return findById(id) == null;
		} catch (Exception e) {
			return false;
		}
	}
}
