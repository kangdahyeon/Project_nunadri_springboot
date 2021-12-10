package com.springproject.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
   public List<CommunityCommentVO> getCommunityComment(CommunityCommentVO ccv) {
	   MemberVO vo = new MemberVO();
	   vo.setNickname(ccv.getNickname());
	   communityCommentService.getProfile(vo);
     log.info("프로파일확인하기{}",  communityCommentService.getProfile(vo));
      return communityCommentService.getCommunityComment(ccv);
   }
   
   
   
   

   @PostMapping("/insertCommunityComment")
   public String insertCommunityComment(CommunityCommentVO ccv) {
	   communityCommentService.insertCommunityComment(ccv);
      
      return "redirect:/communityDetail/"+ ccv.getNoticeCategory() + "/" + ccv.getNoticeNo();

   }
   
   
   @PostMapping("/deleteCommunityComment")
   @ResponseBody
   public int deleteCommunityComment(CommunityCommentVO ccv) {
	   System.out.println(ccv.getCommunityCommentNo());
	   communityCommentService.deleteCommunityComment(ccv);

      return 1 ;
   }
}

