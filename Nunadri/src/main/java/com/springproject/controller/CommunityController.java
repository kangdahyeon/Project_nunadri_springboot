//package com.springproject.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.springproject.domain.CommunityDTO;
//import com.springproject.service.CommunityService;
//
//
//@Controller
//public class CommunityController {
//	
//	@Autowired
//	private CommunityService communityService;
//	
//	@GetMapping(value="/insert")
//	public String insertCommunity(@RequestParam(value = "noticeNo", required = false)int noticeNo, Model model) {
//		
//		
//		return "view/community/community_boarder_commu_insert";
//	}
//	
//	@PostMapping(value="/register")
//	public String registerCommunity(final CommunityDTO cDto) {
//		return "";
//	}
//
//}