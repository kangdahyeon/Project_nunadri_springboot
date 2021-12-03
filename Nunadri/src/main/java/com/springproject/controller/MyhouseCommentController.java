package com.springproject.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.service.MyhouseCommentService;
import com.springproject.service.MyhouseService;
import com.springproject.vo.MyhouseCommentVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyhouseCommentController {


   private final MyhouseService myhouseService;
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
}