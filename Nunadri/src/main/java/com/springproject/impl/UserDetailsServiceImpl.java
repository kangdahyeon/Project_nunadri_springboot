package com.springproject.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springproject.mapper.MemberMapper;
import com.springproject.vo.MemberVO;
import com.springproject.vo.SecurityUser;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVO member = memberMapper.findId(id);
		
		if(member == null) {
			throw new UsernameNotFoundException(id + "찾을수 없습니다.");
		} else {
//			return User.builder()
//					.username(member.getId())
//					.password(member.getPwd())
//					.roles(member.getRole().toString())
//					.build();
			
			SecurityUser user = new SecurityUser(member.getId(),
												 member.getPwd(),
												 member.getNickname(),
												 AuthorityUtils.createAuthorityList(member.getRole().toString()));
			System.out.println(user.getPassword() + "1");
			return user;
			
					
		}
	}

}
