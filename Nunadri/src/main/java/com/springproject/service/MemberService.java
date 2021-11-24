package com.springproject.service;



import com.springproject.vo.MemberVO;

public interface MemberService {
	
	void join(MemberVO vo);
	
	MemberVO findId(String id);
	
	

}
