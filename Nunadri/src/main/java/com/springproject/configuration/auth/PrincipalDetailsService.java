package com.springproject.configuration.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springproject.mapper.MemberMapper;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	//소셜로그인 리턴값을 편하게 하기 위한 클래스
//	@Autowired
	private final MemberService memberservice;

	//@Autowired
	private final MemberMapper memberMapper;

	// 소셜로그인 정보를 담기위한 맵
	//@Autowired
	private final Map<String, Object> attributes;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVO member = memberMapper.findId(id);
		if (member == null) {
			return null;
		} else {
			return new PrincipalDetails(member);
		}
	}
}
