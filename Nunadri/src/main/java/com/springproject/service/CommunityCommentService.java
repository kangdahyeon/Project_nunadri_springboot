package com.springproject.service;

import java.util.List;

import com.springproject.dto.CommunityCommentDto;
import com.springproject.vo.CommunityCommentVO;
import com.springproject.vo.MemberVO;



public interface CommunityCommentService {
   
	void insertCommunityComment(CommunityCommentVO ccv);
   
   List<CommunityCommentVO> getCommunityComment(CommunityCommentVO ccv);
   
   void deleteCommunityComment(CommunityCommentVO ccv);
   
   List<CommunityCommentDto> getProfile(MemberVO profile);
}