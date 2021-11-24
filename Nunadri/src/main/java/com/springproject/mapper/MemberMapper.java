package com.springproject.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.springproject.vo.MemberVO;



@Mapper
public interface MemberMapper {
	
	MemberVO findId(String id);
	
	void join(MemberVO vo);
	
	MemberVO findNickname(String nickname);
	
	MemberVO findEmail(String email);
	
	

}
