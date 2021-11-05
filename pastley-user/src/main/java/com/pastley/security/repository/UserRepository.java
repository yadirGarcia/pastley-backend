package com.pastley.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.pastley.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByMail(String mail);
}
