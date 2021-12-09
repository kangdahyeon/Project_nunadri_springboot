package com.springproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.common.FileUtils;
import com.springproject.service.CustomerFileService;
import com.springproject.service.CustomerService;
import com.springproject.vo.Criteria;
import com.springproject.vo.CustomerServiceVO;
import com.springproject.vo.FileCustomerServiceVO;
import com.springproject.vo.FileMyhouseVO;
import com.springproject.vo.PageVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerServiceController {
	private final CustomerService customerService;
	private final CustomerFileService customerFileService;
	
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
	public String insertCustomerServiceBoard(CustomerServiceVO customerBoardInsert,@AuthenticationPrincipal SecurityUser user,
									HttpServletRequest request, MultipartHttpServletRequest mhsr) {
//		customerBoardInsert.setId(user.getId());
//		System.out.println(customerBoardInsert.getId());
		
		String id = user.getId();
	
		try {
			
			int qnaNo = customerService.getqnaNo(id);
			FileUtils fileUtils = new FileUtils();
			List<FileCustomerServiceVO> fileList = fileUtils.parseFileInfo(id, qnaNo, request, mhsr);
		
		if(!CollectionUtils.isEmpty(fileList)) {
			customerFileService.insertCustomerFileList(fileList);
		}
		customerService.insertCustomerServiceBoard(customerBoardInsert);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
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
		model.addAttribute("fileList", customerFileService.getCustomerFileList(customerBoardDetail));
		
		return "view/customerService/questionboard_detail";
	}
	
	//게시글 삭제 기능
	@GetMapping("/CustomerServiceBoardDelete/{qnaNo}/{id}")
	public String deleteCustomerServiceBoard(CustomerServiceVO customerBoardDelete) {
		
		customerService.deleteCustomerServiceBoard(customerBoardDelete);
		//삭제시 댓글도 같이 삭제
		customerService.deleteCustomerServiceComment(customerBoardDelete);
		//게시글 삭제 시 이미지 모두 삭제
		customerFileService.deleteCustomerFileAll(customerBoardDelete);
		
		return "redirect:/CustomerServiceBoardList";
	}
	
	//게시글 업데이트 기능
	@GetMapping("/CustomerServiceBoardUpdate/{qnaNo}/{id}")
	public String updateCustomerServiceBoard(CustomerServiceVO customerBoardUpdate, Model model) {
		
		model.addAttribute("customerBoardUpdate", customerService.getCustomerServiceBoard(customerBoardUpdate));
		model.addAttribute("fileList", customerFileService.getCustomerFileList(customerBoardUpdate));
		return "view/customerService/questionboard_update";
	}
	
	@PostMapping("/CustomerServiceBoardUpdate")
	public String updateCustomerService(CustomerServiceVO customerBoardUpdate, @AuthenticationPrincipal SecurityUser user,
			@RequestParam("arrNo") int[] arr,
			HttpServletRequest request, MultipartHttpServletRequest mhsr) {
	
		//수정
		customerService.updateCustomerServiceBoard(customerBoardUpdate);
		
//		System.out.println("업데이트 닉네임"+customerBoardUpdate.getNickname());

		  String id = user.getId();
		//파일 삭제를 위한 객체
				FileCustomerServiceVO vo = new FileCustomerServiceVO();
				if(arr != null) {
					vo.setId(id);
					vo.setQnaNo(customerBoardUpdate.getQnaNo());
					for(int x : arr) {
						vo.setFileNo(x);
						customerFileService.deleteCustomerFileList(vo);
					}
				}
		//파일 업로드
				int qnaNo = customerBoardUpdate.getQnaNo();
				try {
					
					FileUtils fileUtils = new FileUtils();
					List<FileCustomerServiceVO> fileList = fileUtils.parseFileInfo(id, qnaNo, request, mhsr);
				
				if(!CollectionUtils.isEmpty(fileList)) {
					customerFileService.insertCustomerFileList(fileList);
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
		
		return "redirect:/CustomerServiceBoardDetail/"+customerBoardUpdate.getQnaNo()+"/"+customerBoardUpdate.getId();
	}
	
}
