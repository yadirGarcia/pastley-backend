package com.pastley.security.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import com.pastley.security.entity.User;
import com.pastley.security.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Optional<User> getByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	public boolean existsByMail(String mail) {
		return userRepository.existsByMail(mail);
	}
	
	public void save(User user){
        userRepository.save(user);
    }
}
