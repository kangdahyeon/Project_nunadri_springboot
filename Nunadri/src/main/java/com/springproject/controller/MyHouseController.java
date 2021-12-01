package com.springproject.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproject.service.MemberService;
import com.springproject.service.MyhouseService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.NoticeMyhouseVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyHouseController {


	private final MemberService memberService;
	private final MyhouseService myhouseService;



	@ModelAttribute("nickname")
	public String userNickname(@AuthenticationPrincipal SecurityUser user) {
		return user.getNickname();
	}


	@GetMapping("/myhome")
	public String myHomeMain(@AuthenticationPrincipal SecurityUser user, Model model) {

		MemberVO member = memberService.getMemberInfo(user.getId());
		model.addAttribute("memberInfo", member);
		return "view/myhome/myhome_main";
	}

	@GetMapping("/fleamarket")
	public String fleamarket() {
		return "view/myhome/fleamarket/fleamarket_list";
	}


	@GetMapping("/freeInsert")
	public String freeInsert() {
		return "view/myhome/myhome_boarder_insert";
	}


	@RequestMapping("/board/{category}")
	public String noticeBoard(@PathVariable("category")String category,NoticeMyhouseVO vo, Model model) {
		
		if(vo.getSearchCondition() == null) {
	         vo.setSearchCondition("MYHOUSE_TITLE");
	      }
	      if(vo.getSearchKeyword() == null) {
	         vo.setSearchKeyword("");
	      }
	      
		model.addAttribute("category", category);
		model.addAttribute("boardList", myhouseService.getMyhouseBoardList(category));
		return "view/myhome/boarder/boarder_list";
	}



	@PostMapping("/insertMyhouseBoard")
	public String insertMyhouseBoard(NoticeMyhouseVO noticeInsert) {
		myhouseService.insertMyhouseBoard(noticeInsert);

		return "redirect:/board/" + noticeInsert.getMyhouseCategory();
	}


	@GetMapping("/myhouseBoardDetail/{myhouseCategory}/{myhouseNo}")
	public String myhouseBoardDetail(@PathVariable("myhouseCategory")String category, @PathVariable("myhouseNo") int myhouseNo,  Model model) {
		
		NoticeMyhouseVO vo = new NoticeMyhouseVO();

		vo.setMyhouseCategory(category);
		vo.setMyhouseNo(myhouseNo); 
		
		model.addAttribute("getBoard",myhouseService.getMyhouseBoard(vo));

		return "view/myhome/myhome_boarder_detail";
	}

	@GetMapping("/deleteMyhouseBoard/{myhouseCategory}/{myhouseNo}")
	public String deleteMyhouseBoard(@PathVariable("myhouseNo") int myhouseNo, @PathVariable("myhouseCategory") String category) {

		NoticeMyhouseVO vo = new NoticeMyhouseVO();

		vo.setMyhouseNo(myhouseNo);
		vo.setMyhouseCategory(category);

		myhouseService.deleteBoardSeq(vo);


		return "redirect:/board/" + category;
	}
}