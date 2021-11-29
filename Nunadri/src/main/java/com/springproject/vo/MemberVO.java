package com.springproject.vo;

import com.springproject.role.Role;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {
	
	private String id;
	
	private String pwd;
	
	private String email;
	
	private String address;
	
	private String addressDetail;
	
	private String name;
	
	private String nickname;
	
	private String phone;
	
	private Role role;
	
	private String profile;
	
	private String provider;
//	private String providerId;

	@Builder
	public MemberVO(String id, String pwd, String email, Role role, String name, String nickname, String provider) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.role = role;
		this.name = name;
		this.nickname = nickname;
		this.provider = provider;
	}

}
