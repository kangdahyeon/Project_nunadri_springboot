package com.springproject.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.MemberMapper;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.SecurityUser;

@Transactional
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void join(MemberVO vo) {
//		validateDuplicateEmail(vo);
//		validateDuplicateMember(vo);
		memberMapper.join(vo);
	}
	
	 private void validateDuplicateEmail(MemberVO member) {
	        MemberVO findMember = memberMapper.findEmail(member.getEmail());
	        if (findMember != null) {
	            throw new IllegalStateException("이미 가입된 회원입니다.");
	        }
	    }
	 
	 private void validateDuplicateMember(MemberVO member) {
	        MemberVO findMember = memberMapper.findNickname(member.getNickname());
	        if (findMember != null) {
	            throw new IllegalStateException("이미 가입된 회원입니다.");
	        }
	    }

	@Override
	public MemberVO findId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO getMemberInfo(String id) {
		MemberVO member = memberMapper.getMemberInfo(id);
		return member;
	}

	@Override
	public void deleteMember(String id) {
		memberMapper.deleteMember(id);
		
	}

	@Override
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
	
		
	}



}
