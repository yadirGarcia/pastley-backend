package com.pastley.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.pastley.security.enums.RoleEnum;
import com.pastley.security.jwt.JwtProvider;
import com.pastley.security.model.JwtDto;
import com.pastley.security.model.UserModel;
import com.pastley.security.service.RoleService;
import com.pastley.security.service.UserService;
import com.pastley.util.PastleyMensaje;

import lombok.Data;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Data
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private JwtProvider jwtProvider;


	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody UserModel loginUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new PastleyMensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getNickname(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}

}
