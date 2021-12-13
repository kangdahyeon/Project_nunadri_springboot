package com.springproject.service;

import java.util.List;
import java.util.Map;

import com.springproject.dto.CommunityCommentDto;
import com.springproject.vo.CommunityCommentVO;



public interface CommunityCommentService {
   
	void insertCommunityComment(CommunityCommentVO commentInsert);
   
   List<CommunityCommentVO> getCommunityComment(CommunityCommentVO ccv);
   
   void deleteCommunityComment(CommunityCommentVO ccv);
   
   List<CommunityCommentDto> getProfile(Map<String, Object> paramMap);
}