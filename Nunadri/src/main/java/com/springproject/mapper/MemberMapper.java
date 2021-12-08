package com.springproject.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.MemberVO;
import com.springproject.vo.NoticeMyhouseVO;



@Mapper
public interface MemberMapper {
	
	MemberVO findId(String id);
	
	void join(MemberVO vo);
	
	MemberVO findNickname(String nickname);
	
	MemberVO findEmail(String email);
	
	MemberVO getMemberInfo(String id);
	
	List<MemberVO> getAdminInfo(Map<String,Object> map);
	
	int selectMyHouseMemberCount(MemberVO paging);
	
	String findAddress(String address);
	
	void updateMember(MemberVO vo);
	
	void updatePwd(MemberVO vo);
	
	void deleteMember(String id);
	
	void insertHouse(MemberVO vo); 
	

}
