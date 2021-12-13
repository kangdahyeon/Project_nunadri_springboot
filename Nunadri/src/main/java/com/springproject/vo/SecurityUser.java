package com.springproject.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SecurityUser extends User {
	
	
	private String id;
	private String nickname;
	private String address;
	
	public SecurityUser(
			String username,
			String password,
			String nickname,
//			String address,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = username;
		this.nickname = nickname;
//		this.address = address;
		
	}
	
	
	
			
			
			
	

}
