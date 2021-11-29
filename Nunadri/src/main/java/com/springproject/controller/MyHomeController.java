package com.springproject.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springproject.service.MemberService;
import com.springproject.service.MyHomeService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.NoticeMyhouseVO;
import com.springproject.vo.SecurityUser;

@Controller
public class MyHomeController {
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private MyHomeService myHomeService;
	
//	@GetMapping("/myhome")
//	public String myHomeMain() {
//		return "view/myhome/myhome_main";
//	}
	
	@GetMapping("/myhome")
	public String myHomeMain(@AuthenticationPrincipal SecurityUser user, Model model) {
		  
		  MemberVO member = memberservice.getMemberInfo(user.getId());
		  model.addAttribute("memberInfo", member);
		  System.out.println(member.getAddress());
		  return "view/myhome/myhome_main";
	  }
	
//	@GetMapping("/myhome")
//	public String myHomeMain(@AuthenticationPrincipal SecurityUser user, Model model) {
//		  
//		NoticeMyhouseVO myhouse = myHomeService.getMyHouseInfo(user.getNickname());
//		  model.addAttribute("getMyHouseInfo", myhouse);
//		
//		MemberVO member = memberservice.getMemberInfo(user.getId());
//		  model.addAttribute("memberInfo", member);
//		  
//		  return "view/myhome/myhome_main";
//	  }
//	 
	@GetMapping("/fleamarket")
	public String fleamarket() {
		    return "view/myhome/fleamarket/fleamarket_list";
		}
	
	@GetMapping("/help")
	public String help(NoticeMyhouseVO vo, Model model) {
		System.out.println("help() 탐");
		
//		List<NoticeMyhouseVO> myhouse = myHomeService.getMyHomeBoardList(vo);
		List<HashMap<String,Object>> myhouse = myHomeService.getMyHomeBoardList(vo);
		model.addAttribute("helpBoardList", myhouse);
		System.out.println(myhouse+"11");
		
		    return "view/myhome/help/boarder_help";
		}
	
//	@RequestMapping("/board/getMyHomeBoardList")
//	public String getMyHomeBoardList(NoticeMyhouseVO vo, Model model){
//		
//		System.out.println("getFreeBoardList() 탐");
//		
////		MemberVO member = memberservice.getMemberInfo(user.getId());
//		NoticeMyhouseVO myhouse = myHomeService.getMyHomeBoardList(vo);
////		int total = boardService.selectBoardCount(vo);
//		
//		model.addAttribute("freeBoardList", myHomeService.getFreeBoardList(vo));
//		
//		return "board-free_결";
//				
//	}

}
