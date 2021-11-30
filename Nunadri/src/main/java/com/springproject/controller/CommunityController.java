package com.springproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springproject.service.CommunityService;
import com.springproject.vo.CommunityVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes("community")
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	
//	@ModelAttribute("conditionMap")
//	public Map<String, String> searchConditionMap() {
//		Map<String, String> conditionMap = new HashMap<String, String>();
//		conditionMap.put("제목", "TITLE");
//		conditionMap.put("내용", "CONTENT");
//		
//		return conditionMap;
	
	@GetMapping("/insertCommunity")
	public String insertCommunityView() {
		return "view/community/community_boarder_commu_insert";
	}
	
	@PostMapping(value="/insertCommunity")
	public String insertCommunity(CommunityVO cvo, HttpServletRequest request) throws IOException {
		
		int no = communityService.getCoummunityNo();
		
		communityService.insertCommunity(cvo);
		
		return "redirect:communityList";
	}
	
	@RequestMapping(value="/updateCommunity")
	public String updateCommunity(@ModelAttribute("community") CommunityVO cvo, 
			HttpServletRequest request) throws IOException {
		log.info("게시물 수정 : {}, {}, {}, {}, {}, {}"
					,cvo.getNoticeCategory(),cvo.getNoticeNo(), cvo.getNickname()
					,cvo.getNoticeTitle(), cvo.getNoticeContent(), cvo.getNoticeRegDate());
		
		int no = cvo.getNoticeNo();
		
		communityService.updateCommunity(cvo);
		return "redirect:communityList";
	}
	
	@RequestMapping(value="/deleteCommunity")
	public String deleteCommunity(CommunityVO cvo) {
		log.info("게시물 삭제 : {}, {}", cvo.getNoticeCategory(), cvo.getNoticeNo());
		communityService.deleteCommunity(cvo);
		return "redirect:communityList";
	}
	
	@RequestMapping(value="/communityDetail")
	public String getCommunity(CommunityVO cvo, Model model) {
		log.info("게시물 한개 조회 : {}, {}", cvo.getNoticeCategory(),cvo.getNoticeNo());
		model.addAttribute("community", communityService.getCommunityDetail(cvo));
		return "CommunityDetail";
	}
	
	@RequestMapping(value="/communityList")
	public String getCommunityList(CommunityVO cvo, Model model) {
		List<CommunityVO> communityList = communityService.getCommunityList(cvo);
	
		if(cvo.getSearchCondition() == null) {
			cvo.setSearchCondition("TITLE");
		}
		if(cvo.getSearchKeyword() == null) {
			cvo.setSearchKeyword("");
		}
		
		model.addAttribute("communityList", communityList);
		return "view/community/honey/boarder_honey";
	}
}