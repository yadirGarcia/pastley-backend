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

import com.pastley.security.dto.JwtDto;
import com.pastley.security.dto.LoginUser;
import com.pastley.security.dto.NewUser;
import com.pastley.security.entity.Role;
import com.pastley.security.entity.User;
import com.pastley.security.enums.RoleName;
import com.pastley.security.jwt.JwtProvider;
import com.pastley.security.service.RoleService;
import com.pastley.security.service.UserService;
import com.pastley.util.PastleyMensaje;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new PastleyMensaje("campos mal puestos o email inválido"),
					HttpStatus.BAD_REQUEST);
		if (userService.existsByUserName(newUser.getUserName()))
			return new ResponseEntity(new PastleyMensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (userService.existsByMail(newUser.getMail()))
			return new ResponseEntity(new PastleyMensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
		User user = new User(newUser.getName(), newUser.getUserName(), newUser.getMail(),
				passwordEncoder.encode(newUser.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getByRoleName(RoleName.CASHIER).get());
		if (newUser.getRoles().contains("admin"))
			roles.add(roleService.getByRoleName(RoleName.ADMINISTRATOR).get());
		user.setRoles(roles);
		userService.save(user);
		return new ResponseEntity(new PastleyMensaje("usuario guardado"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new PastleyMensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

}
