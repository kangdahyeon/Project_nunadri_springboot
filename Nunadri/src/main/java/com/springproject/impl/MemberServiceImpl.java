package com.springproject.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.MemberMapper;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {


	private final MemberMapper memberMapper;
	
	
	@Override
	public void join(MemberVO vo) {
//		validateDuplicateEmail(vo);
//		validateDuplicateNickname(vo);
		memberMapper.join(vo);
		
	}
	
	
	//쿼리문 작성하기
	 private void validateDuplicateEmail(MemberVO member) {
	        MemberVO findMember = memberMapper.findEmail(member.getEmail());
	        if (findMember != null) {
	            throw new IllegalStateException("이미 가입된 회원입니다.");
	        }
	    }
	 
	 private void validateDuplicateNickname(MemberVO member) {
	        MemberVO findMember = memberMapper.findNickname(member.getName());
	        if (findMember != null) {
	            throw new IllegalStateException("이미 가입된 회원입니다.");
	        }
	    }
	
	
}
