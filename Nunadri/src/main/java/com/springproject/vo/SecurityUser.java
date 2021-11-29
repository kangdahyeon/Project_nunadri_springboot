package com.springproject.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class SecurityUser extends User{
	
	private String id;
	
	private String nickname;
	
	public  SecurityUser(
			
		String username,
		String password,
		String nickname,
		Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, authorities);
		this.id = username;
		this.nickname = nickname;
	}
	
	
	

		
	
}
