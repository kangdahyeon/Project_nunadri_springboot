package com.springproject.configuration.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.springproject.vo.MemberVO;

import lombok.Data;

//Authentication 객체에 저장할 수 있는 유일한 타입
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
	private MemberVO member;
	// 소셜로그인 정보를 담기위한 맵
	private Map<String, Object> attributes;

	// 일반 시큐리티 로그인시 사용, 일반 생성자(소셜로그인 리턴값을 편하게 받게하기 위한것) 
	public PrincipalDetails(MemberVO member) {
		this.member = member;
	}

	// OAuth2.0 로그인시 사용
	public PrincipalDetails(MemberVO member, Map<String, Object> attributes) {
		this.member = member;
		this.attributes = attributes;
	}

	public MemberVO getUser() {
		return member;
	}

	@Override
	public String getPassword() {
		return member.getPwd();
	}

	@Override
	public String getUsername() {
		return member.getName();
	}

	// 닉네임 값으로 받아오기 위해 넣어준 메소드(princpal)
	public String getNickname() {
		return member.getNickname();
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
		collet.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {

//				return member.getRole();
				return "USER";
			}
		});
		return collet;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
