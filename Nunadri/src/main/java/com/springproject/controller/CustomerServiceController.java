package com.springproject.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.service.CustomerService;
import com.springproject.vo.Criteria;
import com.springproject.vo.CustomerServiceVO;
import com.springproject.vo.PageVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerServiceController {
	private final CustomerService customerService;
	
	static String condition ="";
	static String keyword="";
	
	@ModelAttribute("nickname")
	public String userNickname(@AuthenticationPrincipal SecurityUser user) {
		return user.getNickname();
	}
	
	@ModelAttribute("id")
	public String userId(@AuthenticationPrincipal SecurityUser user) {
		return user.getId();
	}
	
	//고객센터 보드 인서트
	@PostMapping("/CustomerServiceBoard")
	public String insertCustomerServiceBoard(CustomerServiceVO customerBoardInsert,@AuthenticationPrincipal SecurityUser user) {
		//customerBoardInsert.setId(user.getId());
		System.out.println(customerBoardInsert.getId());
		customerService.insertCustomerServiceBoard(customerBoardInsert);
		return "redirect:/CustomerServiceBoardList";
	}
	
	//고객센터 보드 
	@GetMapping("/CustomerServiceBoard")
	public String CustomerServiceBoardView(CustomerServiceVO customerBoardInsert) {
		return "view/customerService/questionboard";
	}

	//고객센터 리스트
	@RequestMapping("/CustomerServiceBoardList")
	public String CustomerServiceBoardList(CustomerServiceVO customerBoardList,
			@AuthenticationPrincipal SecurityUser user, Model model, Criteria cri) {
		 //검색값 없을때 기본 값 설정 
        if(customerBoardList.getSearchCondition() == null) {
        	customerBoardList.setSearchCondition("QNA_TITLE");
           }
           if(customerBoardList.getSearchKeyword() == null) {
        	   customerBoardList.setSearchKeyword("");
           }
           //드디어 타네
           if(customerBoardList.getNickname() == null) {
        	   customerBoardList.setNickname(user.getNickname());
           }
		
		 //검색, 키워드 값(페이징 처리시 필요)
        condition = customerBoardList.getSearchCondition();
        keyword = customerBoardList.getSearchKeyword();
        
        int total = customerService.selectCustomerServiceBoardCount(customerBoardList);
        
		model.addAttribute("customerBoardList", customerService.getCustomerServiceBoardList(customerBoardList, cri));
		model.addAttribute("pageMaker", new PageVO(cri, total));
        model.addAttribute("condition", customerBoardList.getSearchCondition());
        model.addAttribute("keyword", customerBoardList.getSearchKeyword());
		
        return "view/customerService/question_list";
	}
	
	@GetMapping("/FAQ")
	public String FAQ() {
		return "view/customerService/FAQ";
	}
	
	//게시글 상세페이지
	@RequestMapping("/CustomerServiceBoardDetail/{qnaNo}/{id}")
	public String CustomerServiceBoardDetail(CustomerServiceVO customerBoardDetail, Model model) {
		model.addAttribute("getCustomerService", customerService.getCustomerServiceBoard(customerBoardDetail));
		return "view/customerService/questionboard_detail";
	}
	
	//게시글 삭제 기능
	@GetMapping("/CustomerServiceBoardDelete/{qnaNo}/{id}")
	public String deleteCustomerServiceBoard(CustomerServiceVO customerBoardDelete) {
		
		customerService.deleteCustomerServiceBoard(customerBoardDelete);
		//삭제시 댓글도 같이 삭제
		customerService.deleteCustomerServiceComment(customerBoardDelete);
		
		return "redirect:/CustomerServiceBoardList";
	}
	
	//게시글 업데이트 기능
	@GetMapping("/CustomerServiceBoardUpdate/{qnaNo}/{id}")
	public String updateCustomerServiceBoard(CustomerServiceVO customerBoardUpdate, Model model) {
		
		model.addAttribute("customerBoardUpdate", customerService.getCustomerServiceBoard(customerBoardUpdate));
		return "view/customerService/questionboard_update";
	}
	
	@PostMapping("/CustomerServiceBoardUpdate")
	public String updateCustomerService(CustomerServiceVO customerBoardUpdate) {
		customerService.updateCustomerServiceBoard(customerBoardUpdate);
		System.out.println("업데이트 닉네임"+customerBoardUpdate.getNickname());
		return "redirect:/CustomerServiceBoardDetail/"+customerBoardUpdate.getQnaNo()+"/"+customerBoardUpdate.getId();
	}
	
}
