package com.springproject.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.dto.CommunityCommentDto;
import com.springproject.service.CommunityCommentService;
import com.springproject.vo.CommunityCommentVO;
import com.springproject.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommunityCommentController {

   private final CommunityCommentService communityCommentService;

  
   
   //게시판(공지사항, 도와주세요, 자유게시판) 목록
   @ResponseBody
   @GetMapping("/getCommunityComment")
   public List<CommunityCommentDto> getCommunityComment(CommunityCommentVO ccv) {
	   MemberVO vo = new MemberVO();
	   vo.setId(ccv.getId());
	   
	   Map<String, Object> paramMap = new HashMap<String, Object>();
	   
	   paramMap.put("member", vo);
	   paramMap.put("comment", ccv);

	   List<CommunityCommentDto> list = communityCommentService.getProfile(paramMap);

      return list;
   }
   
   
   
   
   @ResponseBody
   @PostMapping("/insertCommunityComment")
   public boolean insertCommunityComment(CommunityCommentVO commentInsert) {
      communityCommentService.insertCommunityComment(commentInsert);
      
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
