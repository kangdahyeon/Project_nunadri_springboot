package com.springproject.service;

import com.springproject.vo.MemberVO;

public interface MemberService {
	void join(MemberVO vo);

	MemberVO findId(String id);
	
	MemberVO getMemberInfo(String id);
	
	void deleteMember(String id);
	
	void updateMember(MemberVO member);

}