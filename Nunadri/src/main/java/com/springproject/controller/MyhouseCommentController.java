package com.springproject.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.service.MyhouseCommentService;
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
   public String insertMyhouseComment(MyhouseCommentVO commentInsert) {

      myhouseCommentService.insertMyhouseComment(commentInsert);
      
      return "redirect:/myhouseBoardDetail/"+ commentInsert.getHouseNo() + "/" + commentInsert.getMyhouseCategory() + "/" +commentInsert.getMyhouseNo();

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
   public String insertsmallGroupComment(MyhouseCommentVO commentInsert) {

      myhouseCommentService.insertMyhouseComment(commentInsert);
      
      return "redirect:/smallGroupDetail/"+ commentInsert.getHouseNo() + "/s/" +commentInsert.getMyhouseNo();

   }
   
   //소모임 삭제 기능
   @PostMapping("/smallGroupCommentDelete")
   public String deletesmallGroupComment(MyhouseCommentVO deleteSmallGroupComment) {
	   System.out.println(deleteSmallGroupComment.getMyhouseCommentNo());
      myhouseCommentService.deleteMyhouseComment(deleteSmallGroupComment);


      return "redirect:/smallGroupDetail/"+ deleteSmallGroupComment.getHouseNo() + "/s/" +deleteSmallGroupComment.getMyhouseNo(); 
   }
   
}