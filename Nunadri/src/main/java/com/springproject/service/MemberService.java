package com.springproject.service;



import java.util.List;

import com.springproject.vo.Criteria;
import com.springproject.vo.MemberVO;

public interface MemberService {

	void join(MemberVO vo);

	MemberVO findId(String id);

	MemberVO getMemberInfo(String id);

	List<MemberVO> getAdminInfo(MemberVO vo, Criteria cri);

	int selectMyHouseMemberCount(MemberVO paging);

	MemberVO findNickname(String nickname);

	MemberVO findEmail(String email);

	boolean findAddress(String address);

	void updateMember(MemberVO vo);

	void updatePwd(MemberVO vo);

	void deleteMember(String id);

	void insertHouse(MemberVO vo);

	void updateProfile(List<MemberVO> list);

}
