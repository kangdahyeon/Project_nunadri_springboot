package com.springproject.service;




import com.springproject.vo.MemberVO;

public interface MemberService {
	
	void join(MemberVO vo);
	
	MemberVO findId(String id);
	
	MemberVO getMemberInfo(String id);
	
	MemberVO findNickname(String nickname);
	
	MemberVO findEmail(String email);
	
	MemberVO findNickname(String nickname);

	void updateMember(MemberVO vo);
	
	void updatePwd(MemberVO vo);
	
	void deleteMember(String id);
}
