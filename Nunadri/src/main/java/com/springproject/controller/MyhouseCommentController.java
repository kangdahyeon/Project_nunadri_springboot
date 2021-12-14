package com.springproject.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.dto.MyhouseCommentDto;
import com.springproject.service.MyhouseCommentService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.MyhouseCommentVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyhouseCommentController {


   private final MyhouseCommentService myhouseCommentService;


   

   //게시판(공지사항, 도와주세요, 자유게시판) 목록
   @GetMapping("/getMyhouseComment")
   @ResponseBody
   public List<MyhouseCommentVO> getMyhouseComment(MyhouseCommentVO commentList) {
      return myhouseCommentService.getMyhouseComment(commentList);
   }

   
   
   
   
   @PostMapping("/insertMyhouseComment")
   @ResponseBody
   public boolean insertMyhouseComment(MyhouseCommentVO commentInsert) {
      myhouseCommentService.insertMyhouseComment(commentInsert);
      
      return true;

   }
   
   //게시글 삭제 기능
   @PostMapping("/deleteMyhouseComment")
   @ResponseBody
   public int deleteMyhouseBoard(MyhouseCommentVO deleteComment) {
	   System.out.println(deleteComment.getMyhouseCommentNo());
      myhouseCommentService.deleteMyhouseComment(deleteComment);


      return 1 ;
   }
   
   //소모임 댓글
   @PostMapping("/smallGroupComment")
   @ResponseBody
   public boolean insertsmallGroupComment(MyhouseCommentVO commentInsert) {

      myhouseCommentService.insertMyhouseComment(commentInsert);
      
      return true;

   }
   
   //소모임 참여 인원 댓글 삭제 기능
   @PostMapping("/smallGroupCommentDelete")
   public String deletesmallGroupComment(MyhouseCommentVO deleteSmallGroupComment) {
	   System.out.println(deleteSmallGroupComment.getMyhouseCommentNo());
      myhouseCommentService.deleteMyhouseComment(deleteSmallGroupComment);


      return "redirect:/smallGroupDetail/"+ deleteSmallGroupComment.getHouseNo() + "/s/" +deleteSmallGroupComment.getMyhouseNo(); 
   }
   
}