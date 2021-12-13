package com.springproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.service.CustomerServiceCommentService;
import com.springproject.vo.CustomerServiceCommentVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerServiceCommentController {

//	private final CustomerService customerService;
	private final CustomerServiceCommentService customerServiceCommentService;

	//고객센터 댓글달 게시물 가져오기
	@GetMapping("/getCustomerServiceComment")
	@ResponseBody
	public List<CustomerServiceCommentVO> getCustomerServiceComment(CustomerServiceCommentVO customerCommentList){
		
		return customerServiceCommentService.getCustomerServiceComment(customerCommentList);
	}
	
	//댓글 등록
	@PostMapping("/insertCustomerServiceComment")
	public String insertCustomerServiceComment(CustomerServiceCommentVO customerCommentInsert) {
		
		customerServiceCommentService.insertCustomerServiceComment(customerCommentInsert);
		
		return "redirect:/CustomerServiceBoardDetail/"+customerCommentInsert.getQnaNo()+"/"+customerCommentInsert.getId();
	}
	
	//댓글 삭제
	@PostMapping("/deleteCustomerServiceComment")
	@ResponseBody
	public int deleteCustomerServiceComment(CustomerServiceCommentVO customerCommentDelete) {
		System.out.println(customerCommentDelete.getQnaCommentNo());
		customerServiceCommentService.deleteCustomerServiceComment(customerCommentDelete);
//		customerServiceCommentService.deleteCustomerServiceCommentList(customerCommentDelete);
		return 1;
	}
//	
//	@PostMapping("/CustomerServiceBoardDelete/{qnaNo}/{id}")
//	public String deleteCustomerServiceCommentList(CustomerServiceCommentVO customerCommentDeleteList) {
//		customerServiceCommentService.deleteCustomerServiceCommentList(customerCommentDeleteList);
//		return "redirect:/CustomerServiceBoardList";
//	}
}
