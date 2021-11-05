package com.pastley.security.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pastley.security.entity.Role;
import com.pastley.security.enums.RoleName;
import com.pastley.security.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRolNombre(roleName);
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}
