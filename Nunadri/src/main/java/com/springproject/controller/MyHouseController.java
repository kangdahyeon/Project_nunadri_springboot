package com.springproject.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyHouseController {
	
	private final MemberService memberService;
	
	   @GetMapping("/myhome")
	   public String myHomeMain(@AuthenticationPrincipal SecurityUser user, Model model) {
	        
	        MemberVO member = memberService.getMemberInfo(user.getId());
	        model.addAttribute("memberInfo", member);
	        System.out.println(member.getAddress());
	        return "view/myhome/myhome_main";
	     }
	   
	   @GetMapping("/fleamarket")
		public String fleamarket() {
			    return "view/myhome/fleamarket/fleamarket_list";
			}
	   
	   @GetMapping("/free")
			public String free() {
				    return "view/myhome/free/boarder_free";
				}
	   
	   @GetMapping("/help")
			public String help() {
				    return "view/myhome/help/boarder_help";
				}
	   
	   @GetMapping("/notice")
			public String notice() {
				    return "view/myhome/notice/boarder_inform";
				}
	   
	   @GetMapping("/smallGroup")
			public String smallGroup() {
				    return "view/myhome/smallGroup/boarder_smallGroup";
				}

}
