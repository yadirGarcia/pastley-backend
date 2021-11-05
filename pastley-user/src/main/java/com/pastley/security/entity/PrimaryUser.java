package com.pastley.security.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import java.util.stream.Collectors;


public class PrimaryUser implements UserDetails {

	private String name;
	private String userName;
	private String mail;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	
	public PrimaryUser(String name, String userName, String mail, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.userName = userName;
		this.mail = mail;
		this.password = password;
		this.authorities = authorities;
	}
	
	 public static PrimaryUser build(User usuario){
	        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
	        return new PrimaryUser(usuario.getName(), usuario.getUserName(), usuario.getMail(), usuario.getPassword(), authorities);
	    }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}
}
