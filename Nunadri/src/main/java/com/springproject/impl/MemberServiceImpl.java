package com.springproject.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.MemberMapper;
import com.springproject.service.MemberService;
import com.springproject.vo.Criteria;
import com.springproject.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

   
   private final MemberMapper memberMapper;
   

   //회원가입
   @Override
   public void join(MemberVO vo) {
      validateDuplicateEmail(vo);
      validateDuplicateNickname(vo);
      validateDuplicateId(vo);
      memberMapper.join(vo);
   }

   //회원정보 수정
   @Override
   public void updateMember(MemberVO vo) {
      memberMapper.updateMember(vo);

   }
   
   @Override
   public void deleteMember(String id) {
      memberMapper.deleteMember(id);
   }      

   @Override
   public MemberVO findId(String id) {
      MemberVO m = memberMapper.findId(id);
      return m;
   }

   @Override
   public MemberVO findEmail(String email) {
      MemberVO m = memberMapper.findEmail(email);
      return m;
   }
   
   @Override
   public MemberVO findNickname(String nickname) {
	   MemberVO m = memberMapper.findNickname(nickname);
	      return m;
   }

   
   
      @Override
      public void updatePwd(MemberVO vo) {
         memberMapper.updatePwd(vo);
      }



   //중복된 회원 검사(이메일)
   public void validateDuplicateEmail(MemberVO member) {
      MemberVO findMember = memberMapper.findEmail(member.getEmail());
      if (findMember != null) {
         throw new IllegalStateException("이미 가입된 이메일입니다.");
      }
   }

   //중복된 회원 검사(닉네임)
   public void validateDuplicateNickname(MemberVO member) {
      MemberVO findMember = memberMapper.findNickname(member.getNickname());
      if (findMember != null) {
         throw new IllegalStateException("이미 사용 중인 닉네임입니다.");
      }
   }
   
   //중복된 회원 검사(아이디)
      public void validateDuplicateId(MemberVO member) {
         MemberVO findMember = memberMapper.findId(member.getId());
         if (findMember != null) {
            throw new IllegalStateException("이미 사용 중인 아이디입니다.");
         }
      }


   //유저 정보 조회
   @Override
   public MemberVO getMemberInfo(String id) {
      MemberVO member = memberMapper.getMemberInfo(id); 
      return member;
   }
   
	@Override
	public void insertHouse(MemberVO vo) {
		memberMapper.insertHouse(vo);
	}
	
   	//주소찾기(HOUSE DB 필요)
 	@Override
 	public boolean findAddress(String address) {
 		String checkAddress = memberMapper.findAddress(address);
 		if(checkAddress == null || checkAddress.equals("")) {
 			return false;
 		} else {
 			return true;
 		}
 	}
 	
 	
    @Override
    public List<MemberVO> getAdminInfo(MemberVO vo, Criteria cri) {

       Map<String, Object> paramMap = new HashMap<String, Object>();
       paramMap.put("adminInfo", vo);
       cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
       paramMap.put("criteria", cri);

       return memberMapper.getAdminInfo(paramMap);
    }

    @Override public int selectMyHouseMemberCount(MemberVO paging) { 
    	return
    memberMapper.selectMyHouseMemberCount(paging); }
    


   
 	
 	
 	




}