package com.springproject.service;




import com.springproject.vo.HouseVO;
import com.springproject.vo.MemberVO;

public interface MemberService {
	
	void join(MemberVO vo);
	
	MemberVO findId(String id);
	
	MemberVO getMemberInfo(String id);
	
	MemberVO findEmail(String email);
	
	boolean findAddress(String address);

	void updateMember(MemberVO vo);
	
	void updatePwd(MemberVO vo);
	
	void deleteMember(String id);
	
	void insertHouse(HouseVO vo); 
}
