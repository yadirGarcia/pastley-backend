package com.pastley.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.pastley.security.entity.User;

@Data
@AllArgsConstructor
public class UserModel implements UserDetails {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private Long id;

	@NotBlank
	private String nickname;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	///////////////////////////////////////////////////////
	// Method - Build
	///////////////////////////////////////////////////////
	public static UserModel build(User user) {
		List<GrantedAuthority> authorities = user.getRoles() != null ? user.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getName().name())).collect(Collectors.toList())
				: new ArrayList<>();
		return new UserModel(user.getId(), user.getNickname(), user.getPerson().getEmail(), user.getPassword(),
				authorities);
	}

	@Override
	public String getUsername() {
		return this.nickname;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
