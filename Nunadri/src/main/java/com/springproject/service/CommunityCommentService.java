package com.springproject.service;

import java.util.List;

import com.springproject.vo.CommunityCommentVO;



public interface CommunityCommentService {
   
	void insertCommunityComment(CommunityCommentVO ccv);
   
   List<CommunityCommentVO> getCommunityComment(CommunityCommentVO ccv);
   
   void deleteCommunityComment(CommunityCommentVO ccv);
   

}