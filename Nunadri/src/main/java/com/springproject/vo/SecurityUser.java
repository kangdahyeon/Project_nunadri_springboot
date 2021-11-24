package com.springproject.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SecurityUser extends User {
	
	
	private String id;
	private String nickname;
	
	public SecurityUser(
			String username,
			String password,
			String nickname,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = username;
		this.nickname = nickname;
		
	}
	
	
	
			
			
			
	

}
