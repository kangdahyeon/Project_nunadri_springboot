package com.springproject.service;




import com.springproject.vo.MemberVO;

public interface MemberService {
	
	void join(MemberVO vo);
	
	MemberVO findId(String id);
	
	MemberVO getMemberInfo(String id);
	
	MemberVO findEmail(String email);

	void updateMember(MemberVO vo);
	
	void updatePwd(MemberVO vo);
	
	void deleteMember(String id);
}
