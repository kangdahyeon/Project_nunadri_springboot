package com.springproject.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.dto.CommunityCommentDto;
import com.springproject.service.CommunityCommentService;
import com.springproject.vo.CommunityCommentVO;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class CommunityCommentController {

   private final CommunityCommentService communityCommentService;

  
   
   //게시판(공지사항, 도와주세요, 자유게시판) 목록
   @ResponseBody
   @GetMapping("/getCommunityComment")
   public List<CommunityCommentDto> getCommunityComment(CommunityCommentVO ccv) {
	   MemberVO vo = new MemberVO();
	   vo.setNickname(ccv.getNickname());
	   List<CommunityCommentDto> list = communityCommentService.getProfile(vo);
   
      return list;
   }
   
   
   
   
   @ResponseBody
   @PostMapping("/insertCommunityComment")
   public boolean insertCommunityComment(CommunityCommentVO ccv) {
      communityCommentService.insertCommunityComment(ccv);
      
      return true;

   }
   
   
   @PostMapping("/deleteCommunityComment")
   @ResponseBody
   public int deleteCommunityComment(CommunityCommentVO ccv) {
      System.out.println(ccv.getCommunityCommentNo());
      communityCommentService.deleteCommunityComment(ccv);

      return 1 ;
   }
}
