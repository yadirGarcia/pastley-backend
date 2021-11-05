package com.pastley.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pastley.security.entity.Role;
import com.pastley.security.enums.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRolNombre(RoleName roleName);
}
