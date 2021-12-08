package com.springproject.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.service.CommunityCommentService;
import com.springproject.service.CommunityService;
import com.springproject.vo.CommunityCommentVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommunityCommentController {


   private final CommunityService communityService;
   private final CommunityCommentService communityCommentService;


   

   //게시판(공지사항, 도와주세요, 자유게시판) 목록
   @GetMapping("/getCommunityComment")
   @ResponseBody
   public List<CommunityCommentVO> getMyhouseComment(CommunityCommentVO ccv) {
     
      return communityCommentService.getCommunityComment(ccv);
   }
   
   
   
   

   @PostMapping("/insertCommunityComment")
   public String insertMyhouseComment(CommunityCommentVO ccv) {

	   communityCommentService.insertCommunityComment(ccv);
      
      return "redirect:/communityDetail/"+ ccv.getNoticeCategory() + "/" + ccv.getNoticeNo();

   }
   
   

   @PostMapping("/deleteMyhouseComment")
   @ResponseBody
   public int deleteMyhouseBoard(CommunityCommentVO ccv) {
	   communityCommentService.deleteCommunityComment(ccv);


      return 1 ;
   }
}